import { Proveedor } from './proveedor';
import { DetallePedido } from './detalle-pedido';

export class PedidoCompra {
    id_pedido?: number;
    estado: 'pendiente' | 'recibido' | 'cancelado';
    fecha: Date;
    fecha_pedido: Date;
    total?: number;
    id_proveedor: number;
    proveedor?: Proveedor;
    detalles?: DetallePedido[];
}

