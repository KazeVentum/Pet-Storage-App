# Dockerización Completa - Sistema de Inventario

Este proyecto está completamente dockerizado incluyendo:
- **MySQL 8.0**: Base de datos
- **Spring Boot Backend**: API REST
- **Angular Frontend**: Aplicación web con Nginx

## 🚀 Inicio Rápido

### Requisitos Previos

- Docker Desktop (Windows/Mac) o Docker Engine + Docker Compose (Linux)
- Al menos 4GB de RAM disponible
- Puertos 8080, 4200 y 3307 libres

### Iniciar Todo el Sistema

Desde la raíz del proyecto (donde está el `docker-compose.yml`):

```bash
docker-compose up -d --build
```

Este comando:
- Construye las imágenes del backend y frontend
- Inicia MySQL 8.0 con la base de datos inicializada
- Inicia el backend Spring Boot
- Inicia el frontend Angular con Nginx
- Crea volúmenes para persistir datos

### Ver Logs

```bash
# Ver todos los logs
docker-compose logs -f

# Ver logs de un servicio específico
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql
```

### Detener Todo

```bash
docker-compose down
```

### Detener y Eliminar Datos (⚠️ Elimina la base de datos)

```bash
docker-compose down -v
```

## 📍 Acceso a los Servicios

### Frontend (Aplicación Web)
- **URL**: http://localhost:4200
- **Descripción**: Interfaz de usuario Angular servida por Nginx

### Backend (API REST)
- **URL**: http://localhost:8080
- **Contexto**: `/sistema-inventario/v1`
- **Ejemplo**: http://localhost:8080/sistema-inventario/v1/marca/listar
- **También accesible desde el frontend**: El frontend puede acceder al backend a través de `/sistema-inventario/` (proxy configurado en Nginx)

### MySQL (Base de Datos)
- **Host**: localhost
- **Puerto**: 3307 (externo)
- **Usuario root**: root
- **Password**: campus2024
- **Base de datos**: sistema_inventario

### Conexión desde Cliente MySQL

```bash
mysql -h localhost -P 3307 -u root -pcampus2024 sistema_inventario
```

## 🏗️ Arquitectura

```
┌─────────────────┐
│   Frontend      │  http://localhost:4200
│   (Nginx)       │
│   Angular       │
└────────┬────────┘
         │
         │ /sistema-inventario/ (proxy)
         │
┌────────▼────────┐
│   Backend       │  http://localhost:8080
│   Spring Boot   │
└────────┬────────┘
         │
         │ JDBC
         │
┌────────▼────────┐
│   MySQL 8.0     │  localhost:3307
│   Database      │
└─────────────────┘
```

## 🔧 Configuración

### Variables de Entorno

Las variables de entorno están configuradas en `docker-compose.yml`. Puedes modificarlas según necesites:

**MySQL:**
- `MYSQL_ROOT_PASSWORD`: Contraseña del usuario root
- `MYSQL_DATABASE`: Nombre de la base de datos
- `MYSQL_USER`: Usuario adicional (opcional)
- `MYSQL_PASSWORD`: Contraseña del usuario adicional

**Backend:**
- `SPRING_DATASOURCE_URL`: URL de conexión JDBC
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: Modo de Hibernate (validate en producción)
- `SPRING_JPA_SHOW_SQL`: Mostrar SQL en logs (false en producción)

**Frontend:**
- `API_URL`: URL del backend (configurado como build arg)

### Cambiar la URL del Backend en el Frontend

Si necesitas cambiar la URL del backend que usa el frontend, edita el `docker-compose.yml`:

```yaml
frontend:
  build:
    args:
      - API_URL=http://tu-backend-url:8080/sistema-inventario/v1
```

Luego reconstruye:

```bash
docker-compose build frontend
docker-compose up -d frontend
```

## 🔄 Desarrollo

### Reconstruir un Servicio Específico

```bash
# Reconstruir backend
docker-compose build --no-cache backend
docker-compose up -d backend

# Reconstruir frontend
docker-compose build --no-cache frontend
docker-compose up -d frontend
```

### Ver Estado de los Servicios

```bash
docker-compose ps
```

### Acceder a los Contenedores

```bash
# Backend
docker exec -it sistema-inventario-backend /bin/bash

# Frontend
docker exec -it sistema-inventario-frontend /bin/sh

# MySQL
docker exec -it sistema-inventario-mysql mysql -u root -pcampus2024 sistema_inventario
```

## 🐛 Solución de Problemas

### El frontend no se conecta al backend

1. Verifica que ambos servicios estén corriendo: `docker-compose ps`
2. Verifica los logs del frontend: `docker-compose logs frontend`
3. Verifica los logs del backend: `docker-compose logs backend`
4. Asegúrate de que el proxy en `nginx.conf` esté configurado correctamente

### El backend no se conecta a MySQL

1. Espera a que MySQL termine de inicializar (puede tardar 30-60 segundos)
2. Verifica el healthcheck: `docker-compose ps`
3. Revisa los logs: `docker-compose logs mysql backend`

### Cambios en el código no se reflejan

1. **Backend**: Reconstruye la imagen
   ```bash
   docker-compose build backend
   docker-compose up -d backend
   ```

2. **Frontend**: Reconstruye la imagen
   ```bash
   docker-compose build frontend
   docker-compose up -d frontend
   ```

### Puerto ya en uso

Si algún puerto está en uso, puedes cambiarlo en `docker-compose.yml`:

```yaml
ports:
  - "NUEVO_PUERTO:PUERTO_INTERNO"
```

## 📦 Volúmenes y Persistencia

### Datos de MySQL

Los datos de MySQL se almacenan en el volumen `mysql_data` y persisten entre reinicios.

### Backup de la Base de Datos

```bash
# Crear backup
docker exec sistema-inventario-mysql mysqldump -u root -pcampus2024 sistema_inventario > backup.sql

# Restaurar backup
docker exec -i sistema-inventario-mysql mysql -u root -pcampus2024 sistema_inventario < backup.sql
```

## 🔒 Seguridad para Producción

Antes de desplegar en producción:

1. **Cambiar contraseñas por defecto** en `docker-compose.yml`
2. **Usar variables de entorno** o secrets de Docker para credenciales
3. **Configurar HTTPS** en Nginx (certificados SSL)
4. **Deshabilitar `show-sql`** en Hibernate (ya configurado)
5. **Usar `validate`** en lugar de `update` para DDL (ya configurado)
6. **Configurar firewall** para limitar acceso a los puertos
7. **Implementar autenticación** y autorización adecuadas

## 📝 Notas Importantes

1. **Primera ejecución**: Puede tardar varios minutos mientras se construyen las imágenes y se inicializa MySQL
2. **Datos persistentes**: Los datos de MySQL se mantienen entre reinicios gracias a los volúmenes
3. **Reiniciar desde cero**: `docker-compose down -v` elimina todos los datos
4. **Script de inicialización**: `db.sql` se ejecuta solo la primera vez que se crea el contenedor MySQL
5. **Usuario administrador inicial**:
   - Correo: admin@sistema.com
   - Password: admin123

## 🎯 Comandos Útiles

```bash
# Ver logs en tiempo real
docker-compose logs -f

# Reiniciar un servicio específico
docker-compose restart backend
docker-compose restart frontend

# Ver uso de recursos
docker stats

# Limpiar imágenes no usadas
docker system prune -a

# Ver volúmenes
docker volume ls

# Inspeccionar un volumen
docker volume inspect sistema-inventario_mysql_data
```

## 📚 Estructura del Proyecto

```
.
├── docker-compose.yml          # Orquestación de todos los servicios
├── 72151/                      # Backend Spring Boot
│   ├── Dockerfile
│   ├── db.sql                  # Script de inicialización de BD
│   └── src/
└── 72151-front/                # Frontend Angular
    ├── Dockerfile
    ├── nginx.conf              # Configuración de Nginx con proxy
    └── src/
```

¡Todo listo para ejecutar el sistema completo con un solo comando!

