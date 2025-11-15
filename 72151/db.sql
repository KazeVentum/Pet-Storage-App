-- ============================================
-- BASE DE DATOS: Sistema de Inventario y Pedidos
-- Basado en diagrama E-R notación Chen
-- ============================================

DROP DATABASE IF EXISTS sistema_inventario;
CREATE DATABASE sistema_inventario CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sistema_inventario;

-- ============================================
-- ENTIDADES FUERTES
-- ============================================

-- Tabla: MARCAS
CREATE TABLE MARCAS (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre_marca VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nombre_marca (nombre_marca)
) ENGINE=InnoDB;

-- Tabla: RAZAS
CREATE TABLE RAZAS (
    id_raza INT AUTO_INCREMENT PRIMARY KEY,
    nombre_raza VARCHAR(100) NOT NULL,
    tamano ENUM('pequeño', 'mediano', 'grande') NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    INDEX idx_nombre_raza (nombre_raza),
    INDEX idx_tamano (tamano)
) ENGINE=InnoDB;

-- Tabla: PRODUCTOS
CREATE TABLE PRODUCTOS (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(200) NOT NULL,
    descripcion TEXT,
    precio_venta DECIMAL(10,2) NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    estado ENUM('activo', 'descontinuado', 'agotado') DEFAULT 'activo',
    peso_kg DECIMAL(8,2),
    id_marca INT NOT NULL,
    CONSTRAINT fk_producto_marca FOREIGN KEY (id_marca) REFERENCES MARCAS(id_marca) ON DELETE RESTRICT,
    INDEX idx_nombre_producto (nombre_producto),
    INDEX idx_estado_producto (estado),
    INDEX idx_marca (id_marca),
    INDEX idx_precio_venta (precio_venta)
) ENGINE=InnoDB;

-- Tabla relación N:M PRODUCTOS-RAZAS (detalla)
CREATE TABLE PRODUCTOS_RAZAS (
    id_producto INT NOT NULL,
    id_raza INT NOT NULL,
    PRIMARY KEY (id_producto, id_raza),
    CONSTRAINT fk_prod_raza_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto) ON DELETE CASCADE,
    CONSTRAINT fk_prod_raza_raza FOREIGN KEY (id_raza) REFERENCES RAZAS(id_raza) ON DELETE CASCADE,
    INDEX idx_raza_producto (id_raza, id_producto)
) ENGINE=InnoDB;

-- Tabla: PROVEEDORES
CREATE TABLE PROVEEDORES (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proveedor VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    INDEX idx_nombre_proveedor (nombre_proveedor),
    INDEX idx_email_proveedor (email)
) ENGINE=InnoDB;

-- Tabla: TIPO_MOVIMIENTO
CREATE TABLE TIPO_MOVIMIENTO (
    id_tipo_mov INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
) ENGINE=InnoDB;

-- Tabla: USUARIOS
CREATE TABLE USUARIOS (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_correo (correo),
    INDEX idx_nombre_usuario (nombre)
) ENGINE=InnoDB;

-- Tabla: UBICACIONES
CREATE TABLE UBICACIONES (
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    id_ubicacion VARCHAR(20) NOT NULL UNIQUE,
    tipo_ubicacion ENUM('almacén', 'tienda', 'bodega') NOT NULL,
    nombre_ubicacion VARCHAR(100) NOT NULL,
    responsable VARCHAR(100),
    INDEX idx_tipo_ubicacion (tipo_ubicacion),
    INDEX idx_id_ubicacion (id_ubicacion)
) ENGINE=InnoDB;

-- ============================================
-- ENTIDADES DÉBILES
-- ============================================

-- Tabla: PEDIDOS_COMPRA
CREATE TABLE PEDIDOS_COMPRA (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('pendiente', 'recibido', 'cancelado') DEFAULT 'pendiente',
    fecha DATE NOT NULL,
    fecha_pedido DATE NOT NULL,
    total DECIMAL(12,2) DEFAULT 0,
    id_proveedor INT NOT NULL,
    CONSTRAINT fk_pedido_proveedor FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES(id_proveedor) ON DELETE RESTRICT,
    INDEX idx_estado_pedido (estado),
    INDEX idx_fecha_pedido (fecha_pedido),
    INDEX idx_proveedor_pedido (id_proveedor),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB;

-- Tabla: DETALLE_PEDIDOS
CREATE TABLE DETALLE_PEDIDOS (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad_recibida INT NOT NULL DEFAULT 0,
    cantidad_solicitada INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) GENERATED ALWAYS AS (cantidad_recibida * precio_unitario) STORED,
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (id_pedido) REFERENCES PEDIDOS_COMPRA(id_pedido) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto) ON DELETE RESTRICT,
    INDEX idx_pedido_detalle (id_pedido),
    INDEX idx_producto_detalle (id_producto),
    UNIQUE KEY uk_pedido_producto (id_pedido, id_producto)
) ENGINE=InnoDB;

-- Tabla: INVENTARIO
CREATE TABLE INVENTARIO (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    id_direccion INT NOT NULL,
    stock_minimo INT NOT NULL DEFAULT 10,
    stock_maximo INT NOT NULL DEFAULT 1000,
    stock_actual INT NOT NULL DEFAULT 0,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_inventario_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto) ON DELETE RESTRICT,
    CONSTRAINT fk_inventario_ubicacion FOREIGN KEY (id_direccion) REFERENCES UBICACIONES(id_direccion) ON DELETE RESTRICT,
    UNIQUE KEY uk_producto_ubicacion (id_producto, id_direccion),
    INDEX idx_stock_actual (stock_actual),
    INDEX idx_ubicacion_inv (id_direccion),
    INDEX idx_producto_inv (id_producto),
    CHECK (stock_actual >= 0),
    CHECK (stock_minimo < stock_maximo)
) ENGINE=InnoDB;

-- Tabla: MOVIMIENTOS_INVENTARIO
CREATE TABLE MOVIMIENTOS_INVENTARIO (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_inventario INT NOT NULL,
    id_usuario INT NOT NULL,
    id_tipo_mov INT NOT NULL,
    id_direccion_origen INT,
    id_direccion_destino INT,
    cantidad_bultoa INT NOT NULL,
    fecha_movimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mov_inventario FOREIGN KEY (id_inventario) REFERENCES INVENTARIO(id_inventario) ON DELETE RESTRICT,
    CONSTRAINT fk_mov_usuario FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario) ON DELETE RESTRICT,
    CONSTRAINT fk_mov_tipo FOREIGN KEY (id_tipo_mov) REFERENCES TIPO_MOVIMIENTO(id_tipo_mov) ON DELETE RESTRICT,
    CONSTRAINT fk_mov_origen FOREIGN KEY (id_direccion_origen) REFERENCES UBICACIONES(id_direccion) ON DELETE RESTRICT,
    CONSTRAINT fk_mov_destino FOREIGN KEY (id_direccion_destino) REFERENCES UBICACIONES(id_direccion) ON DELETE RESTRICT,
    INDEX idx_inventario_mov (id_inventario),
    INDEX idx_usuario_mov (id_usuario),
    INDEX idx_tipo_mov (id_tipo_mov),
    INDEX idx_fecha_movimiento (fecha_movimiento),
    INDEX idx_origen (id_direccion_origen),
    INDEX idx_destino (id_direccion_destino),
    CHECK (cantidad_bultoa > 0)
) ENGINE=InnoDB;

-- ============================================
-- TRIGGERS PARA INTEGRIDAD DE DATOS
-- ============================================

DELIMITER //

-- Trigger: Actualizar total del pedido al insertar detalle
CREATE TRIGGER trg_actualizar_total_pedido_insert
AFTER INSERT ON DETALLE_PEDIDOS
FOR EACH ROW
BEGIN
    UPDATE PEDIDOS_COMPRA 
    SET total = (
        SELECT SUM(subtotal) 
        FROM DETALLE_PEDIDOS 
        WHERE id_pedido = NEW.id_pedido
    )
    WHERE id_pedido = NEW.id_pedido;
END//

-- Trigger: Actualizar total del pedido al modificar detalle
CREATE TRIGGER trg_actualizar_total_pedido_update
AFTER UPDATE ON DETALLE_PEDIDOS
FOR EACH ROW
BEGIN
    UPDATE PEDIDOS_COMPRA 
    SET total = (
        SELECT SUM(subtotal) 
        FROM DETALLE_PEDIDOS 
        WHERE id_pedido = NEW.id_pedido
    )
    WHERE id_pedido = NEW.id_pedido;
END//

-- Trigger: Actualizar stock en movimiento de entrada
CREATE TRIGGER trg_movimiento_entrada
AFTER INSERT ON MOVIMIENTOS_INVENTARIO
FOR EACH ROW
BEGIN
    DECLARE tipo_nombre VARCHAR(50);
    
    SELECT nombre_tipo INTO tipo_nombre 
    FROM TIPO_MOVIMIENTO 
    WHERE id_tipo_mov = NEW.id_tipo_mov;
    
    IF tipo_nombre IN ('entrada', 'compra', 'ajuste_positivo') THEN
        UPDATE INVENTARIO 
        SET stock_actual = stock_actual + NEW.cantidad_bultoa
        WHERE id_inventario = NEW.id_inventario;
    ELSEIF tipo_nombre IN ('salida', 'venta', 'ajuste_negativo') THEN
        UPDATE INVENTARIO 
        SET stock_actual = stock_actual - NEW.cantidad_bultoa
        WHERE id_inventario = NEW.id_inventario;
    END IF;
END//

DELIMITER ;

-- ============================================
-- DATOS INICIALES
-- ============================================

-- Tipos de movimiento
INSERT INTO TIPO_MOVIMIENTO (nombre_tipo, descripcion) VALUES
('entrada', 'Entrada de productos al inventario'),
('salida', 'Salida de productos del inventario'),
('transferencia', 'Transferencia entre ubicaciones'),
('ajuste_positivo', 'Ajuste positivo de inventario'),
('ajuste_negativo', 'Ajuste negativo de inventario'),
('devolucion', 'Devolución de productos');

-- Usuario administrador inicial
INSERT INTO USUARIOS (nombre, correo, password) VALUES
('Administrador', 'admin@sistema.com', SHA2('admin123', 256));

-- ============================================
-- VISTAS ÚTILES
-- ============================================

-- Vista: Productos con bajo stock
CREATE VIEW v_productos_bajo_stock AS
SELECT 
    p.id_producto,
    p.nombre_producto,
    i.stock_actual,
    i.stock_minimo,
    u.nombre_ubicacion,
    m.nombre_marca
FROM INVENTARIO i
INNER JOIN PRODUCTOS p ON i.id_producto = p.id_producto
INNER JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
INNER JOIN MARCAS m ON p.id_marca = m.id_marca
WHERE i.stock_actual <= i.stock_minimo;

-- Vista: Resumen de pedidos
CREATE VIEW v_resumen_pedidos AS
SELECT 
    pc.id_pedido,
    pc.fecha_pedido,
    pc.estado,
    pr.nombre_proveedor,
    pc.total,
    COUNT(dp.id_detalle) as total_items
FROM PEDIDOS_COMPRA pc
INNER JOIN PROVEEDORES pr ON pc.id_proveedor = pr.id_proveedor
LEFT JOIN DETALLE_PEDIDOS dp ON pc.id_pedido = dp.id_pedido
GROUP BY pc.id_pedido, pc.fecha_pedido, pc.estado, pr.nombre_proveedor, pc.total;

-- Vista: Movimientos de inventario con detalles
CREATE VIEW v_movimientos_detallados AS
SELECT 
    mi.id_movimiento,
    mi.fecha_movimiento,
    tm.nombre_tipo,
    p.nombre_producto,
    mi.cantidad_bultoa,
    u1.nombre_ubicacion as origen,
    u2.nombre_ubicacion as destino,
    us.nombre as usuario
FROM MOVIMIENTOS_INVENTARIO mi
INNER JOIN TIPO_MOVIMIENTO tm ON mi.id_tipo_mov = tm.id_tipo_mov
INNER JOIN INVENTARIO inv ON mi.id_inventario = inv.id_inventario
INNER JOIN PRODUCTOS p ON inv.id_producto = p.id_producto
INNER JOIN USUARIOS us ON mi.id_usuario = us.id_usuario
LEFT JOIN UBICACIONES u1 ON mi.id_direccion_origen = u1.id_direccion
LEFT JOIN UBICACIONES u2 ON mi.id_direccion_destino = u2.id_direccion;

-- ============================================
-- ÍNDICES COMPUESTOS ADICIONALES PARA OPTIMIZACIÓN
-- ============================================

-- Índice para búsquedas frecuentes de productos activos por marca
CREATE INDEX idx_producto_marca_estado ON PRODUCTOS(id_marca, estado, precio_venta);

-- Índice para reportes de inventario por ubicación y stock
CREATE INDEX idx_inventario_ubicacion_stock ON INVENTARIO(id_direccion, stock_actual);

-- Índice para historial de movimientos por fecha y tipo
CREATE INDEX idx_movimientos_fecha_tipo ON MOVIMIENTOS_INVENTARIO(fecha_movimiento, id_tipo_mov);

-- Índice para pedidos por proveedor y estado
CREATE INDEX idx_pedidos_proveedor_estado ON PEDIDOS_COMPRA(id_proveedor, estado, fecha_pedido);

-- ============================================
-- FIN DEL SCRIPT
-- ============================================