# 🌟 Springboot Ejercicio

## Descripción 📝
API RESTful para la creación y gestión de usuarios. Esta aplicación permite registrar nuevos usuarios y gestionarlos de manera eficiente y segura.

## Tecnologías y Dependencias 🛠️
- **Spring Boot** 3.2.0: Framework para crear aplicaciones Spring basadas en microservicios.
- **Java** 17: Lenguaje de programación.
- **Hibernate**: Herramienta de mapeo objeto-relacional para Java.
- **JPA**: Java Persistence API para el acceso a datos.
- **H2 Database**: Base de datos en memoria.

## Instalación y Configuración 🚀
1. Instalar **Gradle**.
2. Clonar el repositorio: `git clone https://github.com/brunoaguilerasilva/EjercicioSpringUsuarios.git`.
3. Ejecutar en la terminal:
   ```
   gradle build
   gradle bootRun
   ```
   Esto iniciará la aplicación en `localhost:8084`.

- **Nota**: El script de Base de datos se encuentra en : src/main/resources/schema.sql

## Uso 📊
Para usar la API, puedes realizar solicitudes HTTP a los endpoints definidos. Por ejemplo, para registrar un nuevo usuario:

- **URL**: `/v1/signup`
- **Método**: POST
- **Puerto**: 8084
- **Body**:
  ```json
  {
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Jr1231",
    "phones": [
      {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
      }
    ]
  }
  ```
- **Nota**: La contraseña debe seguir el patrón definido en el properties: `^([A-Z]{1})+(.*[a-z])+([0-9]{4})$`.

### Respuestas:
- **200 OK**: Usuario registrado exitosamente.
- **400 Bad Request**: Solicitud inválida. Ejemplo: `{"message": "La contraseña no cumple con el formato requerido."}`
- **500 Internal Server Error**: Se ha producido un error en el servidor.

Para más detalles sobre los endpoints y sus respuestas, consulta la documentación de la API.

## Documentación de la API 📚
Accede a la documentación completa de la API en [URL-de-documentación-Swagger], o inicia la aplicación localmente y navega a `localhost:8084/swagger-ui.html` o `localhost:8084/swagger-ui/index.html`

## Pruebas 🧪
Ejecutar pruebas con:
```
./gradlew test
```

## Contacto o Autores 📫
Bruno Aguilera Silva - aguilera.bruno@gmail.com

---
