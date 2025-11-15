@echo off
REM Script para iniciar el sistema de inventario con Docker (Windows)

echo Iniciando Sistema de Inventario con Docker...
echo.

REM Verificar si Docker está corriendo
docker info >nul 2>&1
if errorlevel 1 (
    echo Error: Docker no esta corriendo. Por favor inicia Docker Desktop.
    exit /b 1
)

REM Construir e iniciar los servicios
echo Construyendo e iniciando contenedores...
docker-compose up -d --build

echo.
echo Esperando a que los servicios esten listos...
timeout /t 10 /nobreak >nul

REM Verificar estado de los servicios
echo.
echo Estado de los servicios:
docker-compose ps

echo.
echo Sistema iniciado!
echo.
echo Servicios disponibles:
echo    - Aplicacion: http://localhost:8080/sistema-inventario/v1
echo    - MySQL: localhost:3307
echo.
echo Ver logs: docker-compose logs -f
echo Detener: docker-compose down
echo.

pause

