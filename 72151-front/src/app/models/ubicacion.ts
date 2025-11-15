export class Ubicacion {
    id_direccion?: number;
    id_ubicacion: string;
    tipo_ubicacion: 'almacén' | 'tienda' | 'bodega';
    nombre_ubicacion: string;
    responsable?: string;
}

