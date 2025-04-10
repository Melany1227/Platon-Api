# Platon-Api

## Variante implementada
Variante A: Gestión de Cursos

## Endpoints disponibles

### POST /cursos
Registra un nuevo curso. Requiere JSON en el cuerpo de la petición.


### GET /cursos
Retorna todos los cursos registrados.

### GET /cursos/facultad?nombre=X
Retorna los cursos filtrados por la facultad indicada.

## Cómo probar
Puedes usar Postman o cURL con los siguientes ejemplos:

- `GET http://localhost:8080/PlatonApi/cursos`
- `POST http://localhost:8080/PlatonApi/cursos`
