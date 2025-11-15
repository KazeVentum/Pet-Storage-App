# Proyecto Angular - Sistema de Biblioteca

Este es un proyecto Angular 19 basado en el template Datta Able, diseñado para gestionar un sistema de biblioteca con funcionalidades para administrar autores, libros, préstamos y usuarios.

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado en tu sistema:

- **Node.js** (versión 18 o superior) - [Descargar Node.js](https://nodejs.org/)
- **npm** (viene incluido con Node.js) o **yarn**
- **Angular CLI** (se instalará automáticamente como dependencia del proyecto)

Para verificar que tienes Node.js instalado, ejecuta en tu terminal:

```bash
node --version
npm --version
```

## 🚀 Instalación

### 1. Clonar el repositorio

Si aún no has clonado el repositorio:

```bash
git clone <url-del-repositorio>
cd 72151-front
```

### 2. Instalar dependencias

Instala todas las dependencias del proyecto usando npm:

```bash
npm install
```

Este comando instalará todas las dependencias necesarias listadas en `package.json`, incluyendo:
- Angular 19 y sus módulos
- Bootstrap 5
- ApexCharts para gráficos
- SweetAlert2 para alertas
- Y otras dependencias del proyecto

**Nota:** La instalación puede tardar varios minutos dependiendo de tu conexión a internet.

### 3. Verificar la instalación

Para verificar que Angular CLI está correctamente instalado:

```bash
npx ng version
```

Deberías ver información sobre la versión de Angular CLI y las dependencias instaladas.

## 🏃 Ejecutar el Proyecto

### Modo Desarrollo

Para iniciar el servidor de desarrollo:

```bash
npm start
```

O alternativamente:

```bash
ng serve
```

El servidor de desarrollo se iniciará y la aplicación estará disponible en:

**http://localhost:4200**

El navegador se abrirá automáticamente. Si no, abre manualmente tu navegador y navega a la URL anterior.

### Características del Servidor de Desarrollo

- **Recarga automática**: Los cambios en el código se reflejan automáticamente en el navegador
- **Source Maps**: Facilita la depuración del código
- **Hot Module Replacement**: Actualiza los módulos sin recargar toda la página

## 🛠️ Scripts Disponibles

El proyecto incluye varios scripts npm que puedes ejecutar:

| Comando | Descripción |
|---------|-------------|
| `npm start` | Inicia el servidor de desarrollo en `http://localhost:4200` |
| `npm run build` | Compila el proyecto para producción en la carpeta `dist/` |
| `npm run build-prod` | Compila el proyecto con configuración de producción |
| `npm run watch` | Compila el proyecto en modo watch (observa cambios) |
| `npm test` | Ejecuta las pruebas unitarias con Karma |
| `npm run lint` | Ejecuta el linter para verificar el código |
| `npm run lint:fix` | Ejecuta el linter y corrige automáticamente los errores |
| `npm run prettier` | Formatea el código usando Prettier |

## 📁 Estructura del Proyecto

```
72151-front/
├── src/
│   ├── app/
│   │   ├── demo/
│   │   │   ├── pages/          # Páginas principales
│   │   │   │   ├── autor/      # Gestión de autores
│   │   │   │   ├── libro/      # Gestión de libros
│   │   │   │   ├── prestamo/   # Gestión de préstamos
│   │   │   │   └── usuario/    # Gestión de usuarios
│   │   │   └── ui-elements/    # Componentes de UI
│   │   ├── models/             # Modelos de datos
│   │   ├── services/           # Servicios de backend
│   │   ├── interceptors/       # Interceptores HTTP
│   │   └── theme/             # Componentes de tema y layout
│   ├── assets/                # Recursos estáticos (imágenes, iconos, etc.)
│   ├── environments/          # Configuraciones de entorno
│   └── styles.scss            # Estilos globales
├── angular.json               # Configuración de Angular
├── package.json              # Dependencias del proyecto
└── tsconfig.json            # Configuración de TypeScript
```

## ⚙️ Configuración

### Variables de Entorno

El proyecto utiliza archivos de entorno para configurar la URL del backend. Puedes modificar estos archivos según tu entorno:

- **Desarrollo**: `src/environments/environment.ts`
- **Producción**: `src/environments/environment.prod.ts`

Por defecto, el backend está configurado en:

```typescript
apiUrl: 'http://localhost:8000/biblioteca/v1'
```

Asegúrate de que el backend esté corriendo en esta URL o modifica la configuración según corresponda.

## 🐳 Docker (Opcional)

El proyecto incluye archivos Docker para despliegue:

```bash
# Construir la imagen
docker build -t biblioteca-front .

# Ejecutar con docker-compose
docker-compose up
```

## 🧪 Testing

Para ejecutar las pruebas unitarias:

```bash
npm test
```

Las pruebas se ejecutan con Karma y Jasmine.

## 📦 Build para Producción

Para compilar el proyecto para producción:

```bash
npm run build
```

Los archivos compilados se generarán en la carpeta `dist/`. Estos archivos están optimizados y listos para desplegarse en un servidor web.

## 🔧 Solución de Problemas

### Error: "ng: command not found"

Si recibes este error, asegúrate de haber ejecutado `npm install` correctamente. También puedes usar `npx ng` en lugar de `ng`.

### Error: "Port 4200 is already in use"

Si el puerto 4200 está ocupado, puedes especificar otro puerto:

```bash
ng serve --port 4201
```

### Problemas con las dependencias

Si encuentras problemas con las dependencias:

```bash
# Eliminar node_modules y package-lock.json
rm -rf node_modules package-lock.json

# Reinstalar dependencias
npm install
```

### Vulnerabilidades de seguridad

Si npm reporta vulnerabilidades, puedes intentar corregirlas:

```bash
npm audit fix
```

## 📚 Tecnologías Utilizadas

- **Angular 19**: Framework principal
- **TypeScript 5.6**: Lenguaje de programación
- **Bootstrap 5**: Framework CSS
- **RxJS 7.8**: Programación reactiva
- **ApexCharts**: Gráficos y visualizaciones
- **SweetAlert2**: Alertas y notificaciones
- **ESLint**: Linter para TypeScript
- **Karma & Jasmine**: Testing

## 📝 Notas Adicionales

- El proyecto utiliza SCSS para los estilos
- La aplicación está configurada para usar HashLocationStrategy (URLs con `#`)
- Se incluyen interceptores HTTP para manejar headers automáticamente
- El proyecto está basado en el template Datta Able Angular

## 🤝 Contribuir

1. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
2. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
3. Push a la rama (`git push origin feature/AmazingFeature`)
4. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**Desarrollado con ❤️ usando Angular**
