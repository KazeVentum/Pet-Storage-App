import { Producto } from './producto';
import { Ubicacion } from './ubicacion';

export class Inventario {
    idInventario?: number;
    idProducto: number;
    idDireccion: number;
    stockMinimo: number;
    stockMaximo: number;
    stockActual: number;
    fechaActualizacion?: Date;
    producto?: Producto;
    ubicacion?: Ubicacion;
}

