import { Marca } from './marca';
import { Raza } from './raza';

export class Producto {
    idProducto?: number;
    nombreProducto: string;
    descripcion?: string;
    precioVenta: number;
    precioCompra: number;
    estado: 'activo' | 'descontinuado' | 'agotado';
    pesoKg?: number;
    
    marca?: Marca;
    razas?: Raza[];
}

