import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DetallePedidoProducto } from '../models/detalle-pedido-producto';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DetallePedidoService {
  private readonly api = 'detalle-pedido';

  constructor(private readonly backendService: BackendService) {}

  getDetallesPorPedido(idPedido: number): Observable<DetallePedidoProducto[]> {
    return this.backendService.get(environment.apiUrl, this.api, `listar-por-pedido/${idPedido}`);
  }
}
