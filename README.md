# 📈 Sistema de Gestión de Transacciones y Parámetros📈

## Descripción del Proyecto 📝
Este proyecto implementa un sistema de gestión de transacciones y parámetros, desarrollado como parte de un desafío técnico. El sistema está compuesto por dos microservicios independientes en Java, cada uno diseñado para manejar distintas funcionalidades:

Microservicio de Transacciones: Gestiona la creación, actualización y consulta de transacciones.
Microservicio de Parámetros: Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre distintos parámetros del sistema.
Cada microservicio se conecta a su propia base de datos MySQL y está diseñado siguiendo los principios de Clean Architecture, asegurando una separación clara entre la lógica de negocio y la infraestructura.

## Arquitectura del Sistema 🏗️
La arquitectura del sistema está organizada de la siguiente forma:

- **API Gateway:** Centraliza y gestiona las solicitudes que llegan a los **microservicios**. Permite la escalabilidad y el control del tráfico entre el cliente y los servicios internos.
  - **Microservicios** con **Spring Boot:** Cada microservicio sigue una estructura en capas:
  - Controller: Gestiona las solicitudes HTTP entrantes.
  - Service: Contiene la lógica de negocio y realiza operaciones de procesamiento de datos.
  - Repository: Interactúa con la base de datos MySQL para realizar operaciones CRUD.
  - Entities: Define las entidades de negocio.
- **Logback:** Configurado para el registro detallado de eventos y trazabilidad.
- **Bases de Datos MySQL:** Cada microservicio está conectado a una base de datos específica:
  - transaction_db para el microservicio de transacciones.
  - parameter_db para el microservicio de parámetros.

## Consideraciones del Desafío 🚀
Requerimientos Funcionales

**Microservicio de Transacciones:**

- Conexión a una base de datos para gestionar las transacciones.
- Registro de trazabilidad de cada transacción.
- Implementación de operaciones de consulta y registro.
  
**Microservicio de Parámetros:**

- Conexión a una base de datos independiente para gestionar parámetros.
- Implementación de operaciones CRUD completas.
- Requerimientos No Funcionales
- Conexiones de Base de Datos: Configuradas para utilizar Spring Data JPA, permitiendo una interacción eficiente con ambas bases de datos.
- Diseño de Arquitectura: Utilización de Clean Architecture, asegurando una clara separación entre la lógica de negocio y los detalles de implementación.
- Logging: Configuración de Logback para registrar eventos importantes y gestionar la trazabilidad.
- Lombok: Utilizado para reducir la escritura de código repetitivo y mejorar la claridad del código.
- Pruebas Unitarias: Implementación de pruebas unitarias para garantizar la calidad del código y el correcto funcionamiento de los microservicios.
- Streams y Lambdas de Java: Utilizados para optimizar y simplificar el procesamiento de datos en el microservicio de transacciones.

## Tecnologías Utilizadas 💻
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Logback
- Lombok
- JUnit 5 para pruebas unitarias
- Git para control de versiones

## Instalación y Ejecución ⚙️
**Requisitos Previos**
- Tener instalado Java 17 o superior.
- Tener una instancia de MySQL configurada con dos bases de datos:
- transaction_db para el microservicio de transacciones.
- parameter_db para el microservicio de parámetros.
- Configurar las credenciales de conexión a MySQL en los archivos de configuración (application.properties o application.yml) para cada microservicio.
- Pasos para la Ejecución
  
**Clonar este repositorio:**

**bash**
- Copiar código
- git clone https://github.com/Alaincp89/Desafio-Plan-Carrera-Lucasian.git
- cd Desafio-Plan-Carrera-Lucasian
- Configurar las bases de datos en MySQL:

**sql**
- Copiar código
- CREATE DATABASE transaction_db;
- CREATE DATABASE parameter_db;
-- Puedes ejecutar los scripts de generación de tablas que se encuentran en `scripts/`.
- Configurar los archivos application.properties en cada microservicio con las credenciales de acceso a MySQL.

**Compilar y ejecutar cada microservicio:**

**bash**
- Copiar código
- ./mvnw clean install
- ./mvnw spring-boot:run
  
## Probar los endpoints a través de Postman o cualquier otra herramienta de prueba de API. 

**Ejemplos de endpoints disponibles:**
- GET /api/transactions/completed - Obtiene las transacciones completadas.
- GET /api/parameters - Lista todos los parámetros.
  
## Estructura de los Endpoints 🌐
  
**Microservicio de Transacciones**
- GET /api/transactions - Lista todas las transacciones.
- GET /api/transactions/completed - Obtiene las transacciones completadas.
- GET /api/transactions/{id} - Consulta una transacción específica por ID.
- POST /api/transactions - Crea una nueva transacción.
  
**Microservicio de Parámetros**
- GET /api/parameters - Lista todos los parámetros.
- GET /api/parameters/{id} - Consulta un parámetro específico por ID.
- POST /api/parameters - Crea un nuevo parámetro.
- PUT /api/parameters/{id} - Actualiza un parámetro existente.
- DELETE /api/parameters/{id} - Elimina un parámetro.

## Pruebas Unitarias 🧪
Se han implementado pruebas unitarias para verificar el funcionamiento de los métodos principales en cada microservicio. Las pruebas están ubicadas en el directorio src/test y se ejecutan con JUnit 5. Para ejecutar las pruebas:

**bash**
- Copiar código
- ./mvnw test
  
## Colaborador 🤝
**Alain Cervantes - Developer Semi Senior**

## Contribuciones 🛠️
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir cualquier cambio que desees realizar.


