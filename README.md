# CRUD sencillo de estudiantes #

# Registrar nuevo estudiante
# Eliminar estudiante
# Actualizar estudiante

# Desde Postman usar los siguientes enlaces


# GET --> http://localhost:8080/api/v1/student visualizar los estudiantes (hay dos estudiantes Marian, Alex)
# POST --> http://localhost:8080/api/v1/student enviar el siguiente JSON en postman para añadir otro nuevo estudiante a nuestra lista de estudiantes.
# DELETE --> http://localhost:8080/api/v1/student/1 eliminamos el id del estudiante 3
# PUT  --> http://localhost:8080/api/v1/student/1?name=Carlos Actualizamos el id 1 de nuestro estudiante, se llamara carlos
# PUT NAME Y EMAIL --> http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com Aquí es donde está el error no podemos actualizar el email del estudiante
{
"name": "Carlos",
"email": "Carlos@gmail.com",
"dob": "1995-10-11"
}

# En el método updateStudent hay un error status code 500 internal server error
# No se puede modificar el email del usuario usando su nombre hay que investigar que pasa todo lo demás funciona a la perfección!