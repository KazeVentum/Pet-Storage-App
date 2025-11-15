#!/bin/bash

# Script para iniciar el sistema de inventario con Docker

echo "🚀 Iniciando Sistema de Inventario con Docker..."
echo ""

# Verificar si Docker está corriendo
if ! docker info > /dev/null 2>&1; then
    echo "❌ Error: Docker no está corriendo. Por favor inicia Docker Desktop."
    exit 1
fi

# Construir e iniciar los servicios
echo "📦 Construyendo e iniciando contenedores..."
docker-compose up -d --build

echo ""
echo "⏳ Esperando a que los servicios estén listos..."
sleep 10

# Verificar estado de los servicios
echo ""
echo "📊 Estado de los servicios:"
docker-compose ps

echo ""
echo "✅ Sistema iniciado!"
echo ""
echo "📍 Servicios disponibles:"
echo "   - Aplicación: http://localhost:8080/sistema-inventario/v1"
echo "   - MySQL: localhost:3307"
echo ""
echo "📝 Ver logs: docker-compose logs -f"
echo "🛑 Detener: docker-compose down"
echo ""

