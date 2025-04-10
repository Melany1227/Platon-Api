# Platon-Api

## Variante implementada
Variante A: Gestión de Cursos

## Endpoints disponibles

### POST /cursos
Registra un nuevo curso. Requiere JSON en el cuerpo de la petición.


### GET /curso
Retorna todos los cursos registrados.

### GET /curso/facultad?nombre=X
Retorna los cursos filtrados por la facultad indicada.

## Cómo probar
Puedes usar Postman o URL con los siguientes ejemplos:

- `GET [http://localhost:8080/PlatonApi/cursos](http://localhost:8081/eventosDeportivos_war_exploded/equipos)`
```json
[
    {
        "cupoMaximo": 15,
        "codigo": "JAVA002",
        "profesor": "Oscar Mesa",
        "id": 1,
        "estudiantesInscritos": 5,
        "nombre": "CES3",
        "facultad": "Ingeniería"
    }
]
```
- `POST [http://localhost:8080/PlatonApi/cursos](http://localhost:8081/eventosDeportivos_war_exploded/equipos)`
```json
{
  "nombre": "CES3",
  "codigo": "JAVA001",
  "profesor": "Oscar Mesa",
  "cupoMaximo": 15,
  "estudiantesInscritos": 5,
  "facultad": "Ingeniería"
}
```

- `GET  http://localhost:8081/ProyectoCES_war_exploded/curso/facultad?nombre=Ingeniería`
```json
[
    {
        "cupoMaximo": 15,
        "codigo": "JAVA002",
        "profesor": "Oscar Mesa",
        "id": 1,
        "estudiantesInscritos": 5,
        "nombre": "CES3",
        "facultad": "Ingeniería"
    }
]
```
