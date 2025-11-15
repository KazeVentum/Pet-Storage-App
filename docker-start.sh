#!/bin/bash

# Script para iniciar el sistema completo de inventario con Docker

echo "🚀 Iniciando Sistema de Inventario Completo con Docker..."
echo ""

# Verificar si Docker está corriendo
if ! docker info > /dev/null 2>&1; then
    echo "❌ Error: Docker no está corriendo. Por favor inicia Docker Desktop."
    exit 1
fi

# Construir e iniciar los servicios
echo "📦 Construyendo e iniciando contenedores..."
echo "   Esto puede tardar varios minutos la primera vez..."
docker-compose up -d --build

echo ""
echo "⏳ Esperando a que los servicios estén listos..."
sleep 15

# Verificar estado de los servicios
echo ""
echo "📊 Estado de los servicios:"
docker-compose ps

echo ""
echo "✅ Sistema iniciado!"
echo ""
echo "📍 Servicios disponibles:"
echo "   - Frontend: http://localhost:4200"
echo "   - Backend API: http://localhost:8080/sistema-inventario/v1"
echo "   - MySQL: localhost:3307"
echo ""
echo "📝 Comandos útiles:"
echo "   - Ver logs: docker-compose logs -f"
echo "   - Ver logs de un servicio: docker-compose logs -f [backend|frontend|mysql]"
echo "   - Detener: docker-compose down"
echo "   - Reiniciar un servicio: docker-compose restart [backend|frontend|mysql]"
echo ""

