# Proyecto Integrador 3

API REST para gestión de incidencias y formularios con autenticación JWT.

**Stack:** Spring Boot 3.2.5 | Java 17 | PostgreSQL (Neon) | Swagger | Gradle

---

## Requisitos

- JDK 17
- Gradle (incluye wrapper, no necesita instalación)
- Conexión a internet (dependencias y BD en Neon)

---

## Configuración

### 1. Variables de entorno (`.env`)

Crea un archivo `.env` en la raíz del proyecto:

```env
DB_URL=jdbc:postgresql://<host>/<database>?sslmode=require
DB_USER=<usuario>
DB_PASS=<contraseña>
JWT_SECRET=<base64_secret>
```

> ⚠️ El `.env` está en `.gitignore` y no se sube al repositorio.

### 2. Puerto

El servidor corre en **`http://localhost:8081`**. Para cambiarlo, edita `application.properties`:

```properties
server.port=8080
```

---

## Ejecución

```bash
# Windows
gradlew.bat bootRun

# Linux / Mac
./gradlew bootRun
```

---

## Endpoints

### 🔐 Auth — Público

#### `POST /auth/register`

Registra un nuevo usuario y devuelve un token JWT.

**Request:**
```json
{
  "username": "juan",
  "email": "juan@mail.com",
  "password": "123456",
  "phone": "123456789"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

#### `POST /auth/login`

Inicia sesión y devuelve un token JWT.

**Request:**
```json
{
  "email": "juan@mail.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 📋 Incidencias — Requiere JWT

Todas las rutas requieren header:
```
Authorization: Bearer <token>
```

#### `POST /incidents`

Crea una nueva incidencia asociada al usuario autenticado.

**Request:**
```json
{
  "title": "Bache en la calle",
  "description": "Bache grande en Av. Siempre Viva",
  "categoryId": "HOLE_IN_THE_STREED",
  "stateId": "1",
  "addressId": "123",
  "priorityId": "HIGH"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Bache en la calle",
  "description": "Bache grande en Av. Siempre Viva",
  "date": "2026-05-26",
  "deadline": "2026-06-09"
}
```

> El `deadline` se calcula automáticamente como fecha actual + 2 semanas.

#### `GET /incidents`

Lista todas las incidencias registradas.

**Response:**
```json
[
  {
    "id": 1,
    "title": "Bache en la calle",
    "description": "Bache grande en Av. Siempre Viva",
    "date": "2026-05-26",
    "deadline": "2026-06-09"
  }
]
```

---

### 📝 Formularios — Requiere JWT

#### `GET /api/form`

Lista todos los formularios.

#### `POST /api/form`

Crea un nuevo formulario.

**Request:**
```json
{
  "typeRequest": "REPAIR",
  "section": "Parque central",
  "description": "Banco roto",
  "priority": "MEDIUM",
  "category": "MUNICIPAL"
}
```

**Response:**
```json
{
  "type": "REPAIR",
  "priority": "MEDIUM",
  "description": "Banco roto"
}
```

---

## Categorías de Incidentes

| Categoría | Descripción |
|-----------|-------------|
| `HOLE_IN_THE_STREED` | Bache en la calle |
| `DAMAGED_LIGTH_FIXTURE` | Luminaria dañada |
| `ACUMULATE_GARBAGE` | Basura acumulada |
| `OTHERS` | Otros |

---

## Swagger UI

Disponible en: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

Incluye botón **Authorize** para ingresar el token JWT y probar endpoints protegidos.

---

## Docker

```bash
docker build -t proyecto-integrador-3 .
docker run -p 8080:8080 --env-file .env proyecto-integrador-3
```

---

## Arquitectura

El proyecto sigue una arquitectura hexagonal (puertos y adaptadores):

```
┌─────────────────────────────────────────────────────────┐
│                    Controllers (REST)                    │
│   IncidentController / AuthController / FormController   │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                  Application Services                    │
│       IncidentService / AuthService / FormService        │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                Domain (Modelos y Puertos)                │
│      Incident / User / Category / UseCases / Ports       │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│            Infrastructure (JPA / Adaptadores)             │
│   Repositories / Entities / Mappers / Security / JWT     │
└─────────────────────────────────────────────────────────┘
```

---

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/example/proyecto/integrador3/
│   │   ├── ProyectoIntegrador3Application.java
│   │   ├── adapter/
│   │   │   ├── input/web/
│   │   │   │   ├── controllers/    (AuthController, FormControllerRequest)
│   │   │   │   └── dto/            (AuthRequestDTO, AuthResponseDTO, LoginRequestDTO, FormRequestDTO, FormResponseDTO)
│   │   │   └── output/persistence/
│   │   │       ├── entity/         (User, Form)
│   │   │       ├── mapper/         (FormMapper)
│   │   │       └── repository/     (UserRepository, FormRepository)
│   │   ├── application/service/    (AuthService, FormService)
│   │   ├── domain/
│   │   │   └── port/
│   │   │       ├── input/          (AuthUseCase, CreateFormUseCase)
│   │   │       └── output/         (RequestRepository)
│   │   ├── incidents/
│   │   │   ├── Category.java       (enum)
│   │   │   ├── Presentation/
│   │   │   │   ├── controller/     (IncidentController)
│   │   │   │   └── dto/            (IncidentRequest, IncidentResponse)
│   │   │   ├── application/service/(IncidentService)
│   │   │   ├── domain/
│   │   │   │   ├── model/          (Incident)
│   │   │   │   └── ports/          (IncidentUseCase, IncidentRepositoryPort)
│   │   │   └── infrastructure/
│   │   │       ├── adapter/        (IncidentRepositoryAdapter)
│   │   │       ├── entity/         (IncidentEntity)
│   │   │       └── repository/     (JpaIncidentRepository)
│   │   └── security/               (SecurityConfig, JwtService, JwtAuthFilter, UserDetailsServiceImpl, SwaggerConfig)
│   └── resources/
│       └── application.properties
└── test/
    └── java/.../ProyectoIntegrador3ApplicationTests.java
```

---

## Seguridad

- Autenticación mediante **JWT** (JSON Web Token)
- Las rutas `/auth/**` y `/swagger-ui/**` son públicas
- El resto de rutas requieren header `Authorization: Bearer <token>`
- Passwords encriptados con **BCrypt**
- Sesión **stateless** (no se usa sesión HTTP)
- **CORS** permitido desde `localhost:8080` y `localhost:5173`
- CSRF deshabilitado (API REST)

---

## Base de Datos

| Tabla | Descripción |
|-------|-------------|
| `tbl_users` | Usuarios registrados |
| `tbl_incident` | Incidencias reportadas |
| `tbl_form_data` | Datos de formularios |

Las tablas se crean automáticamente gracias a `spring.jpa.hibernate.ddl-auto=update`.
