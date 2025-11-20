export interface DetallePedidoProducto {
  idDetalle: number;
  idPedido: number;
  cantidadRecibida: number;
  cantidadSolicitada: number;
  precioUnitario: number; // BigDecimal from Java maps to number
  subtotal: number;      // BigDecimal from Java maps to number
  idProducto: number;
  nombreProducto: string;
  descripcionProducto: string;
}
