import { Producto } from './producto';

export class DetallePedido {
    id_detalle?: number;
    id_pedido: number;
    id_producto: number;
    cantidad_recibida: number;
    cantidad_solicitada: number;
    precio_unitario: number;
    subtotal?: number;
    producto?: Producto;
}

