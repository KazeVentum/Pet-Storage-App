import { Producto } from './producto';
import { Ubicacion } from './ubicacion';

export class Inventario {
    id_inventario?: number;
    id_producto: number;
    id_direccion: number;
    stock_minimo: number;
    stock_maximo: number;
    stock_actual: number;
    fecha_actualizacion?: Date;
    producto?: Producto;
    ubicacion?: Ubicacion;
}

