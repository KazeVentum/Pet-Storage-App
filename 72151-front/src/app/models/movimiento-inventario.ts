import { Inventario } from './inventario';
import { TipoMovimiento } from './tipo-movimiento';
import { Ubicacion } from './ubicacion';
import { Usuario } from './usuario';

export class MovimientoInventario {
    id_movimiento?: number;
    id_inventario: number;
    id_usuario: number;
    id_tipo_mov: number;
    id_direccion_origen?: number;
    id_direccion_destino?: number;
    cantidad_bultoa: number;
    fecha_movimiento?: Date;
    inventario?: Inventario;
    tipo_movimiento?: TipoMovimiento;
    usuario?: Usuario;
    ubicacion_origen?: Ubicacion;
    ubicacion_destino?: Ubicacion;
}

