# Dockerfile para la aplicación Spring Boot
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .

# Descargar dependencias (se cachea si no cambia el pom.xml)
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Imagen final con Java runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Instalar curl para healthcheck
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Crear usuario no root para seguridad
RUN groupadd -r spring && useradd -r -g spring spring

# Copiar el JAR compilado
COPY --from=build /app/target/*.jar app.jar

# Cambiar propietario
RUN chown spring:spring app.jar

# Cambiar a usuario no root
USER spring:spring

# Exponer el puerto
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/sistema-inventario/v1/marca/listar || exit 1

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

