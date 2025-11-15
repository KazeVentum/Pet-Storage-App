# Dockerización del Sistema de Inventario

Este proyecto está dockerizado para facilitar el despliegue y desarrollo sin necesidad de instalar MySQL localmente.

## Requisitos Previos

- Docker Desktop (Windows/Mac) o Docker Engine + Docker Compose (Linux)
- Al menos 2GB de RAM disponible
- Puertos 8080 y 3307 libres

## Estructura Docker

El proyecto incluye:
- **MySQL 8.0**: Base de datos en contenedor
- **Spring Boot App**: Aplicación backend en contenedor
- **Volúmenes persistentes**: Los datos de MySQL se mantienen entre reinicios

## Comandos Principales

### Iniciar los servicios

```bash
docker-compose up -d
```

Este comando:
- Construye la imagen de la aplicación Spring Boot
- Inicia MySQL 8.0 con la base de datos inicializada
- Inicia la aplicación Spring Boot
- Crea volúmenes para persistir datos

### Ver logs

```bash
# Ver todos los logs
docker-compose logs -f

# Ver logs solo de la aplicación
docker-compose logs -f app

# Ver logs solo de MySQL
docker-compose logs -f mysql
```

### Detener los servicios

```bash
docker-compose down
```

### Detener y eliminar volúmenes (⚠️ Elimina los datos)

```bash
docker-compose down -v
```

### Reconstruir la aplicación

```bash
# Reconstruir sin cache
docker-compose build --no-cache app

# Reiniciar después de reconstruir
docker-compose up -d app
```

## Acceso a los Servicios

### Aplicación Spring Boot
- **URL**: http://localhost:8080
- **Contexto**: `/sistema-inventario/v1`
- **Ejemplo**: http://localhost:8080/sistema-inventario/v1/marca/listar

### MySQL
- **Host**: localhost
- **Puerto**: 3307 (externo)
- **Usuario root**: root
- **Password**: campus2024
- **Base de datos**: sistema_inventario

### Conexión desde cliente MySQL

```bash
mysql -h localhost -P 3307 -u root -pcampus2024 sistema_inventario
```

O desde herramientas gráficas:
- Host: localhost
- Port: 3307
- User: root
- Password: campus2024
- Database: sistema_inventario

## Inicialización de la Base de Datos

La base de datos se inicializa automáticamente al crear el contenedor MySQL por primera vez. El script `db.sql` se ejecuta automáticamente y crea:

- Todas las tablas del sistema
- Triggers para integridad de datos
- Datos iniciales (tipos de movimiento, usuario administrador)
- Vistas útiles
- Índices optimizados

**Usuario administrador inicial:**
- Correo: admin@sistema.com
- Password: admin123

## Desarrollo

### Modificar código y reiniciar

1. Realiza cambios en el código
2. Reconstruye la imagen:
   ```bash
   docker-compose build app
   docker-compose up -d app
   ```

### Acceder al contenedor MySQL

```bash
docker exec -it sistema-inventario-mysql mysql -u root -pcampus2024 sistema_inventario
```

### Acceder al contenedor de la aplicación

```bash
docker exec -it sistema-inventario-app /bin/bash
```

## Solución de Problemas

### El contenedor MySQL no inicia

1. Verifica que el puerto 3307 esté libre
2. Revisa los logs: `docker-compose logs mysql`
3. Elimina volúmenes y reinicia: `docker-compose down -v && docker-compose up -d`

### La aplicación no se conecta a MySQL

1. Verifica que MySQL esté saludable: `docker-compose ps`
2. Espera unos segundos para que MySQL termine de inicializar
3. Revisa los logs de la app: `docker-compose logs app`

### Cambios en db.sql no se aplican

El script `db.sql` solo se ejecuta la primera vez que se crea el contenedor. Para aplicar cambios:

1. Elimina el volumen: `docker-compose down -v`
2. Reinicia: `docker-compose up -d`

⚠️ **Advertencia**: Esto eliminará todos los datos existentes.

## Variables de Entorno

Puedes personalizar la configuración modificando las variables en `docker-compose.yml`:

- `MYSQL_ROOT_PASSWORD`: Contraseña del usuario root de MySQL
- `MYSQL_DATABASE`: Nombre de la base de datos
- `SPRING_DATASOURCE_URL`: URL de conexión JDBC
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos

## Producción

Para producción, considera:

1. Cambiar las contraseñas por defecto
2. Usar secrets de Docker o variables de entorno seguras
3. Configurar backups automáticos del volumen MySQL
4. Usar `spring.jpa.hibernate.ddl-auto=validate` (ya configurado en docker-compose)
5. Deshabilitar `spring.jpa.show-sql` en producción

## Volúmenes

Los datos de MySQL se almacenan en el volumen `mysql_data`. Para hacer backup:

```bash
# Backup
docker exec sistema-inventario-mysql mysqldump -u root -pcampus2024 sistema_inventario > backup.sql

# Restore
docker exec -i sistema-inventario-mysql mysql -u root -pcampus2024 sistema_inventario < backup.sql
```

