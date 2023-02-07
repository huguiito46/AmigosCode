package com.example.CRUDProyect.services;


import com.example.CRUDProyect.entitis.Student;
import com.example.CRUDProyect.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");

        }

        studentRepository.save(student);

    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {

            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }

        studentRepository.deleteById(studentId);


    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        // Verificamos si el estudiante existe con el id del estudiante si no existe lanzamos una excepcion
        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() -> new IllegalStateException(
                                "student with id" + studentId + "does no exists"
                        ));

        /*
        Si el nombre no es nulo y la longitud del nombre es mayor que 0 y el nombre que tenemos no es el mismo
        que el actual entonces establecemos ese nombre!
         */
        if (name != null &&

                name.length() > 0 && !Objects.equals(student.getName(), name)) {

            student.setName(name);
        }

        /*
        Si el email no es nulo y la longitud del email es mayor que 0 y el email que tenemos no es el mismo
        que el actual entonces establecemos ese nombre
         */

        if (email != null &&

                email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            // Si el email que modificamos es el mismo que el actual lanzamos una excepción
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);

        }
    }
}
