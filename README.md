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

- `GET http://localhost:8081/eventosDeportivos_war_exploded/equipos`
```json
[
    {
        "cupoMaximo": 15,
        "codigo": "JAVA001",
        "profesor": "Oscar Mesa",
        "id": 1,
        "estudiantesInscritos": 5,
        "nombre": "CES3",
        "facultad": "Ingeniería"
    },
    {
        "cupoMaximo": 30,
        "codigo": "JAVA002",
        "profesor": "John Faber",
        "id": 2,
        "estudiantesInscritos": 5,
        "nombre": "DPML",
        "facultad": "Ingeniería"
    },
    {
        "cupoMaximo": 10,
        "codigo": "ART1",
        "profesor": "Melany Suarez",
        "id": 3,
        "estudiantesInscritos": 3,
        "nombre": "Salsa básico",
        "facultad": "Artes"
    }
]
```
- `POST http://localhost:8081/eventosDeportivos_war_exploded/equipos`
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

- `POST http://localhost:8081/eventosDeportivos_war_exploded/equipos`
```json
{
  "nombre": "CES3",
  "profesor": "",
  "cupoMaximo": 15,
  "estudiantesInscritos": 5,
}
```
```json
{"error": "JSON mal formado"}
```

- `GET  http://localhost:8081/ProyectoCES_war_exploded/curso/facultad?nombre=Ingeniería`
```json
[
    {
        "cupoMaximo": 15,
        "codigo": "JAVA001",
        "profesor": "Oscar Mesa",
        "id": 1,
        "estudiantesInscritos": 5,
        "nombre": "CES3",
        "facultad": "Ingeniería"
    },
    {
        "cupoMaximo": 30,
        "codigo": "JAVA002",
        "profesor": "John Faber",
        "id": 2,
        "estudiantesInscritos": 5,
        "nombre": "DPML",
        "facultad": "Ingeniería"
    }
]
```

- `GET http://localhost:8081/ProyectoCES_war_exploded/curso/facultad?nombre=Ingeniería`
```json
{
    "mensaje": "No se encontraron cursos para la facultad: Artes"
}
```


- `GET http://localhost:8081/ProyectoCES_war_exploded/curso/facultad`
```json
{
    "error": "Debe proporcionar el parámetro 'nombre'"
}
```
