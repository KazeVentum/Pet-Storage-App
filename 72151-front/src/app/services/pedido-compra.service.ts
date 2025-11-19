import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PedidoCompra } from '../models/pedido-compra';
import { PedidoCompraDetalle } from '../models/pedido-compra-detalle'; // New import
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PedidoCompraService {
  private readonly api = 'pedido-compra';

  constructor(private readonly backendService: BackendService) {}

  getPedidos(): Observable<PedidoCompra[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getPedido(id: number): Observable<PedidoCompra> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  getPedidosPorEstado(estado: string): Observable<PedidoCompra[]> {
    return this.backendService.get(environment.apiUrl, this.api, `listar-por-estado/${estado}`);
  }

  getPedidosDetallePorEstado(estado: string): Observable<PedidoCompraDetalle[]> { // New method
    return this.backendService.get(environment.apiUrl, this.api, `listar-detalle-por-estado/${estado}`);
  }

  guardarPedido(pedido: PedidoCompra): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', pedido);
  }

  actualizarPedido(pedido: PedidoCompra): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', pedido);
  }

  cambiarEstadoPedido(id: number, estado: string): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'cambiar-estado', {
      id_pedido: id,
      estado: estado
    });
  }
}

