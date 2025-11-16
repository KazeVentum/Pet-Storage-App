import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Inventario } from '../models/inventario';
import { InventarioResumen } from '../models/inventario-resumen';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {
  private api = 'inventario';

  constructor(private backendService: BackendService) {}

  obtenerInventario(id: number): Observable<Inventario> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  listarInventariosPorUbicacion(idUbicacion: number): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, `listar-por-ubicacion/${idUbicacion}`);
  }

  listarProductosBajoStock(): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'productos-bajo-stock');
  }

  obtenerResumenInventario(): Observable<InventarioResumen[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'resumen');
  }

  listarTodosLosInventarios(): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }
}