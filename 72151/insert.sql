    -- ============================================
    -- DATOS DE PRUEBA: Tienda de Comida para Mascotas
    -- 3 Sucursales en Bogota - CORREGIDO
    -- ============================================

    USE sistema_inventario;

    -- Deshabilitar triggers temporalmente para evitar errores de recursión
    SET @ORIG_SQL_MODE=@@SQL_MODE;
    SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

    -- ============================================
    -- CORRECCIÓN DE TABLAS (ejecutar primero)
    -- ============================================

    -- Corregir ENUM de RAZAS

    -- Corregir ENUM de UBICACIONES

    -- ============================================
    -- MARCAS
    -- ============================================
    INSERT INTO MARCAS (nombre_marca) VALUES
    ('Dog Chow'),
    ('Cat Chow'),
    ('Chunky'),
    ('Pedigree'),
    ('Royal Canin'),
    ('Pro Plan'),
    ('Hills'),
    ('Dogourmet'),
    ('Max'),
    ('Mimaskot');

    -- ============================================
    -- RAZAS
    -- ============================================
    INSERT INTO RAZAS (nombre_raza, tamano) VALUES
    ('Chihuahua', 'pequenio'),
    ('Poodle', 'pequenio'),
    ('Schnauzer', 'pequenio'),
    ('Beagle', 'mediano'),
    ('Bulldog', 'mediano'),
    ('Cocker Spaniel', 'mediano'),
    ('Labrador', 'grande'),
    ('Golden Retriever', 'grande'),
    ('Pastor Aleman', 'grande'),
    ('Rottweiler', 'grande'),
    ('Mixto Pequeno', 'pequenio'),
    ('Mixto Mediano', 'mediano'),
    ('Mixto Grande', 'grande');

    -- ============================================
    -- PROVEEDORES
    -- ============================================
    INSERT INTO PROVEEDORES (nombre_proveedor, telefono, email, activo) VALUES
    ('Distribuidora Pet Food Ltda', '601-7654321', 'ventas@petfoodltda.com', TRUE),
    ('Alimentos Mascotas Colombia', '601-8765432', 'pedidos@alimentosmascotas.co', TRUE),
    ('Importadora Nestle Purina', '601-3456789', 'distribucion@purina.com.co', TRUE),
    ('Mayorista Canino S.A.S', '601-2345678', 'info@mayoristacanino.com', TRUE),
    ('Suministros Pet Center', '601-9876543', 'contacto@petcenter.co', TRUE);

    -- ============================================
    -- UBICACIONES
    -- ============================================
    INSERT INTO UBICACIONES (id_ubicacion, tipo_ubicacion, nombre_ubicacion, responsable) VALUES
    ('BOG-CHAPINERO', 'tienda', 'Sucursal Chapinero', 'Carlos Rodriguez'),
    ('BOG-SUBA', 'tienda', 'Sucursal Suba', 'Maria Gonzalez'),
    ('BOG-KENNEDY', 'tienda', 'Sucursal Kennedy', 'Jorge Perez'),
    ('BOG-ALMACEN', 'almacen', 'Almacen Central Bogota', 'Luis Martinez');

    -- ============================================
    -- USUARIOS
    -- ============================================
    INSERT INTO USUARIOS (nombre, correo, password, activo) VALUES
    ('Carlos Rodriguez', 'carlos.rodriguez@petshop.com', SHA2('carlos123', 256), TRUE),
    ('Maria Gonzalez', 'maria.gonzalez@petshop.com', SHA2('maria123', 256), TRUE),
    ('Jorge Perez', 'jorge.perez@petshop.com', SHA2('jorge123', 256), TRUE);

    -- ============================================
    -- PRODUCTOS
    -- ============================================
    INSERT INTO PRODUCTOS (nombre_producto, descripcion, precio_venta, precio_compra, estado, peso_kg, id_marca) VALUES
    -- Dog Chow
    ('Dog Chow Adultos Razas Medianas y Grandes 22kg', 'Alimento completo para perros adultos de razas medianas y grandes', 185000, 140000, 'activo', 22.00, 1),
    ('Dog Chow Adultos Razas Pequenas 8kg', 'Nutricion balanceada para razas pequenas adultas', 75000, 58000, 'activo', 8.00, 1),
    ('Dog Chow Cachorros 17kg', 'Formula especial para cachorros en crecimiento', 165000, 125000, 'activo', 17.00, 1),
    ('Dog Chow Senior 8kg', 'Alimento para perros mayores de 7 anos', 82000, 62000, 'activo', 8.00, 1),

    -- Chunky
    ('Chunky Adultos Carne y Cereales 30kg', 'Alimento economico con proteina de carne', 145000, 110000, 'activo', 30.00, 3),
    ('Chunky Adultos Pollo y Arroz 25kg', 'Receta con pollo y arroz natural', 135000, 102000, 'activo', 25.00, 3),
    ('Chunky Cachorros Pollo 15kg', 'Nutricion especial para cachorros', 98000, 74000, 'activo', 15.00, 3),

    -- Pedigree
    ('Pedigree Adultos Razas Medianas 21kg', 'Alimento completo con vitaminas y minerales', 175000, 132000, 'activo', 21.00, 4),
    ('Pedigree Adultos Razas Pequenas 7kg', 'Croquetas pequenas faciles de masticar', 68000, 51000, 'activo', 7.00, 4),
    ('Pedigree Cachorros Razas Medianas 15kg', 'Con DHA para desarrollo cerebral', 142000, 107000, 'activo', 15.00, 4),

    -- Royal Canin
    ('Royal Canin Medium Adult 15kg', 'Nutricion premium para razas medianas', 285000, 215000, 'activo', 15.00, 5),
    ('Royal Canin Mini Adult 7.5kg', 'Formula para perros pequenos', 165000, 125000, 'activo', 7.50, 5),
    ('Royal Canin Maxi Adult 15kg', 'Para perros de razas grandes', 295000, 223000, 'activo', 15.00, 5),

    -- Pro Plan
    ('Pro Plan Adult Complete 15kg', 'Nutricion cientificamente avanzada', 245000, 185000, 'activo', 15.00, 6),
    ('Pro Plan Puppy Complete 15kg', 'Para cachorros de todas las razas', 255000, 193000, 'activo', 15.00, 6),

    -- Hills
    ('Hills Science Diet Adult 12kg', 'Nutricion clinicamente probada', 275000, 208000, 'activo', 12.00, 7),

    -- Max
    ('Max Premium Adultos 25kg', 'Alimento balanceado economico', 125000, 95000, 'activo', 25.00, 9),

    -- Cat Chow
    ('Cat Chow Adultos Pescado 8kg', 'Alimento completo para gatos adultos', 92000, 70000, 'activo', 8.00, 2),
    ('Cat Chow Adultos Carne 3kg', 'Nutricion balanceada sabor carne', 42000, 32000, 'activo', 3.00, 2),
    ('Cat Chow Gatitos 8kg', 'Desarrollo y crecimiento de gatitos', 98000, 74000, 'activo', 8.00, 2),

    -- Snacks
    ('Dogourmet Snack Huesos 500g', 'Huesos masticables para higiene dental', 18000, 13500, 'activo', 0.50, 8),
    ('Dogourmet Galletas Mix 1kg', 'Galletas surtidas para premios', 22000, 16500, 'activo', 1.00, 8),
    ('Chunky Biscuits Sabores 800g', 'Galletas crujientes sabor pollo', 15000, 11000, 'activo', 0.80, 3),

    -- Accesorios
    ('Mimaskot Shampoo Perros 500ml', 'Shampoo hipoalergenico pH balanceado', 28000, 21000, 'activo', 0.50, 10),
    ('Mimaskot Shampoo Gatos 500ml', 'Shampoo especial para felinos', 32000, 24000, 'activo', 0.50, 10),
    ('Mimaskot Antipulgas Pipeta x3', 'Tratamiento antipulgas y garrapatas', 35000, 26000, 'activo', 0.05, 10),
    ('Mimaskot Desparasitante Tabletas x4', 'Desparasitante interno amplio espectro', 28000, 21000, 'activo', 0.02, 10);

    -- ============================================
    -- RELACIÓN PRODUCTOS-RAZAS
    -- ============================================
    -- Dog Chow Adultos Medianas/Grandes (id_producto=1)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 12), (1, 13);

    -- Dog Chow Adultos Pequenas (id_producto=2)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (2, 1), (2, 2), (2, 3), (2, 11);

    -- Dog Chow Cachorros (id_producto=3)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10);

    -- Chunky Adultos 30kg (id_producto=5)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (5, 7), (5, 8), (5, 9), (5, 10), (5, 13);

    -- Pedigree Adultos Medianas (id_producto=8)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (8, 4), (8, 5), (8, 6), (8, 12);

    -- Royal Canin Medium (id_producto=11)
    INSERT INTO PRODUCTOS_RAZAS (id_producto, id_raza) VALUES
    (11, 4), (11, 5), (11, 6), (11, 12);

    -- ============================================
    -- INVENTARIO - Primero obtener id_direccion
    -- Chapinero=1, Suba=2, Kennedy=3, Almacén=4
    -- ============================================

    -- Sucursal Chapinero (id_direccion debe obtenerse de UBICACIONES)
    INSERT INTO INVENTARIO (id_producto, id_direccion, stock_minimo, stock_maximo, stock_actual) 
    SELECT 1, id_direccion, 10, 100, 45 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 2, id_direccion, 15, 80, 32 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 3, id_direccion, 8, 60, 28 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 5, id_direccion, 12, 120, 55 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 6, id_direccion, 10, 100, 38 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 8, id_direccion, 8, 70, 25 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 11, id_direccion, 5, 40, 18 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 14, id_direccion, 5, 35, 15 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 18, id_direccion, 20, 100, 42 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 21, id_direccion, 30, 200, 85 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL SELECT 24, id_direccion, 15, 80, 35 FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO';

    -- Sucursal Suba
    INSERT INTO INVENTARIO (id_producto, id_direccion, stock_minimo, stock_maximo, stock_actual)
    SELECT 1, id_direccion, 10, 100, 52 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 2, id_direccion, 15, 80, 41 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 3, id_direccion, 8, 60, 22 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 4, id_direccion, 8, 50, 19 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 5, id_direccion, 12, 120, 68 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 7, id_direccion, 10, 80, 31 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 9, id_direccion, 10, 60, 24 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 12, id_direccion, 5, 35, 14 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 15, id_direccion, 5, 35, 12 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 17, id_direccion, 8, 50, 21 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 19, id_direccion, 15, 70, 28 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 22, id_direccion, 25, 150, 67 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA'
    UNION ALL SELECT 25, id_direccion, 15, 80, 33 FROM UBICACIONES WHERE id_ubicacion = 'BOG-SUBA';

    -- Sucursal Kennedy
    INSERT INTO INVENTARIO (id_producto, id_direccion, stock_minimo, stock_maximo, stock_actual)
    SELECT 1, id_direccion, 10, 100, 38 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 2, id_direccion, 15, 80, 46 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 3, id_direccion, 8, 60, 25 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 5, id_direccion, 12, 120, 72 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 6, id_direccion, 10, 100, 44 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 8, id_direccion, 8, 70, 29 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 10, id_direccion, 5, 40, 11 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 13, id_direccion, 5, 35, 9 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 16, id_direccion, 6, 45, 17 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 17, id_direccion, 8, 50, 26 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 20, id_direccion, 10, 50, 18 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 23, id_direccion, 25, 150, 58 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY'
    UNION ALL SELECT 26, id_direccion, 20, 100, 44 FROM UBICACIONES WHERE id_ubicacion = 'BOG-KENNEDY';

    -- Almacén Central (todos los productos)
    INSERT INTO INVENTARIO (id_producto, id_direccion, stock_minimo, stock_maximo, stock_actual)
    SELECT p.id_producto, u.id_direccion, 
        CASE 
            WHEN p.id_producto <= 17 THEN 20 + (p.id_producto * 5)
            ELSE 50 + (p.id_producto * 3)
        END as stock_minimo,
        CASE 
            WHEN p.id_producto <= 17 THEN 200 + (p.id_producto * 20)
            ELSE 500 + (p.id_producto * 15)
        END as stock_maximo,
        CASE 
            WHEN p.id_producto <= 10 THEN 80 + (p.id_producto * 20)
            WHEN p.id_producto <= 20 THEN 120 + (p.id_producto * 8)
            ELSE 200 + (p.id_producto * 10)
        END as stock_actual
    FROM PRODUCTOS p
    CROSS JOIN (SELECT id_direccion FROM UBICACIONES WHERE id_ubicacion = 'BOG-ALMACEN') u;

    -- ============================================
    -- PEDIDOS DE COMPRA
    -- ============================================
    INSERT INTO PEDIDOS_COMPRA (estado, fecha, fecha_pedido, id_proveedor) VALUES
    ('recibido', '2025-10-15', '2025-10-15', 1),
    ('recibido', '2025-10-22', '2025-10-22', 3),
    ('pendiente', '2025-11-05', '2025-11-05', 2),
    ('recibido', '2025-10-28', '2025-10-28', 4),
    ('pendiente', '2025-11-08', '2025-11-08', 5);

    -- ============================================
    -- DETALLE DE PEDIDOS
    -- ============================================
    INSERT INTO DETALLE_PEDIDOS (id_pedido, id_producto, cantidad_recibida, cantidad_solicitada, precio_unitario) VALUES
    -- Pedido 1
    (1, 1, 50, 50, 140000),
    (1, 2, 40, 40, 58000),
    (1, 3, 30, 30, 125000),
    (1, 4, 25, 25, 62000),

    -- Pedido 2
    (2, 11, 20, 20, 215000),
    (2, 12, 20, 20, 125000),
    (2, 13, 20, 20, 223000),
    (2, 14, 25, 25, 185000),

    -- Pedido 3 (pendiente)
    (3, 5, 0, 60, 110000),
    (3, 6, 0, 50, 102000),
    (3, 7, 0, 40, 74000),

    -- Pedido 4
    (4, 18, 50, 50, 70000),
    (4, 19, 40, 40, 32000),
    (4, 20, 35, 35, 74000),

    -- Pedido 5 (pendiente)
    (5, 21, 0, 100, 13500),
    (5, 22, 0, 80, 16500),
    (5, 24, 0, 60, 21000);

    -- ============================================
    -- MOVIMIENTOS DE INVENTARIO
    -- Usar id_inventario e id_usuario correctos
    -- ============================================

    -- Entradas al almacén desde pedido 1
    INSERT INTO MOVIMIENTOS_INVENTARIO (id_inventario, id_usuario, id_tipo_mov, id_direccion_origen, id_direccion_destino, cantidad, fecha_movimiento)
    SELECT i.id_inventario, 1, 1, NULL, u.id_direccion, 50, '2025-10-15 10:30:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 1 AND u.id_ubicacion = 'BOG-ALMACEN'
    UNION ALL
    SELECT i.id_inventario, 1, 1, NULL, u.id_direccion, 40, '2025-10-15 10:35:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 2 AND u.id_ubicacion = 'BOG-ALMACEN'
    UNION ALL
    SELECT i.id_inventario, 1, 1, NULL, u.id_direccion, 30, '2025-10-15 10:40:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 3 AND u.id_ubicacion = 'BOG-ALMACEN';

    -- Transferencias del almacén a Chapinero
    INSERT INTO MOVIMIENTOS_INVENTARIO (id_inventario, id_usuario, id_tipo_mov, id_direccion_origen, id_direccion_destino, cantidad, fecha_movimiento)
    SELECT i.id_inventario, 1, 3, 
        (SELECT id_direccion FROM UBICACIONES WHERE id_ubicacion = 'BOG-ALMACEN'),
        (SELECT id_direccion FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'),
        20, '2025-10-16 09:00:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 1 AND u.id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL
    SELECT i.id_inventario, 1, 3,
        (SELECT id_direccion FROM UBICACIONES WHERE id_ubicacion = 'BOG-ALMACEN'),
        (SELECT id_direccion FROM UBICACIONES WHERE id_ubicacion = 'BOG-CHAPINERO'),
        15, '2025-10-16 09:15:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 2 AND u.id_ubicacion = 'BOG-CHAPINERO';

    -- Ventas en Chapinero
    INSERT INTO MOVIMIENTOS_INVENTARIO (id_inventario, id_usuario, id_tipo_mov, id_direccion_origen, id_direccion_destino, cantidad, fecha_movimiento)
    SELECT i.id_inventario, 1, 2, u.id_direccion, NULL, 5, '2025-10-18 11:20:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 1 AND u.id_ubicacion = 'BOG-CHAPINERO'
    UNION ALL
    SELECT i.id_inventario, 1, 2, u.id_direccion, NULL, 3, '2025-10-18 14:35:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 2 AND u.id_ubicacion = 'BOG-CHAPINERO';

    -- Ventas en Suba
    INSERT INTO MOVIMIENTOS_INVENTARIO (id_inventario, id_usuario, id_tipo_mov, id_direccion_origen, id_direccion_destino, cantidad, fecha_movimiento)
    SELECT i.id_inventario, 2, 2, u.id_direccion, NULL, 8, '2025-10-20 10:00:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 1 AND u.id_ubicacion = 'BOG-SUBA'
    UNION ALL
    SELECT i.id_inventario, 2, 2, u.id_direccion, NULL, 4, '2025-10-20 12:30:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 5 AND u.id_ubicacion = 'BOG-SUBA';

    -- Ventas en Kennedy
    INSERT INTO MOVIMIENTOS_INVENTARIO (id_inventario, id_usuario, id_tipo_mov, id_direccion_origen, id_direccion_destino, cantidad, fecha_movimiento)
    SELECT i.id_inventario, 3, 2, u.id_direccion, NULL, 7, '2025-10-22 11:15:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 5 AND u.id_ubicacion = 'BOG-KENNEDY'
    UNION ALL
    SELECT i.id_inventario, 3, 2, u.id_direccion, NULL, 5, '2025-10-22 16:20:00'
    FROM INVENTARIO i 
    JOIN UBICACIONES u ON i.id_direccion = u.id_direccion
    WHERE i.id_producto = 6 AND u.id_ubicacion = 'BOG-KENNEDY';

    -- ============================================
    -- VERIFICACIÓN DE DATOS
    -- ============================================
    SELECT 'MARCAS insertadas:' as tabla, COUNT(*) as registros FROM MARCAS
    UNION ALL SELECT 'RAZAS insertadas:', COUNT(*) FROM RAZAS
    UNION ALL SELECT 'PROVEEDORES insertados:', COUNT(*) FROM PROVEEDORES
    UNION ALL SELECT 'UBICACIONES insertadas:', COUNT(*) FROM UBICACIONES
    UNION ALL SELECT 'USUARIOS insertados:', COUNT(*) FROM USUARIOS
    UNION ALL SELECT 'PRODUCTOS insertados:', COUNT(*) FROM PRODUCTOS
    UNION ALL SELECT 'INVENTARIO registros:', COUNT(*) FROM INVENTARIO
    UNION ALL SELECT 'PEDIDOS_COMPRA:', COUNT(*) FROM PEDIDOS_COMPRA
    UNION ALL SELECT 'DETALLE_PEDIDOS:', COUNT(*) FROM DETALLE_PEDIDOS
    UNION ALL SELECT 'MOVIMIENTOS_INVENTARIO:', COUNT(*) FROM MOVIMIENTOS_INVENTARIO;

    -- ============================================
    -- FIN DE LOS DATOS DE PRUEBA
    -- ============================================

    -- Restaurar modo SQL original
    SET SQL_MODE=@ORIG_SQL_MODE;

