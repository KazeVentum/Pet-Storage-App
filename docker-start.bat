@echo off
REM Script para iniciar el sistema completo de inventario con Docker (Windows)

echo Iniciando Sistema de Inventario Completo con Docker...
echo.

REM Verificar si Docker está corriendo
docker info >nul 2>&1
if errorlevel 1 (
    echo Error: Docker no esta corriendo. Por favor inicia Docker Desktop.
    exit /b 1
)

REM Construir e iniciar los servicios
echo Construyendo e iniciando contenedores...
echo Esto puede tardar varios minutos la primera vez...
docker-compose up -d --build

echo.
echo Esperando a que los servicios esten listos...
timeout /t 15 /nobreak >nul

REM Verificar estado de los servicios
echo.
echo Estado de los servicios:
docker-compose ps

echo.
echo Sistema iniciado!
echo.
echo Servicios disponibles:
echo    - Frontend: http://localhost:4200
echo    - Backend API: http://localhost:8080/sistema-inventario/v1
echo    - MySQL: localhost:3307
echo.
echo Comandos utiles:
echo    - Ver logs: docker-compose logs -f
echo    - Ver logs de un servicio: docker-compose logs -f [backend^|frontend^|mysql]
echo    - Detener: docker-compose down
echo    - Reiniciar un servicio: docker-compose restart [backend^|frontend^|mysql]
echo.

pause

