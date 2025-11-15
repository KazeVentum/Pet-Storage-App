import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Inventario } from '../models/inventario';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {
  private readonly api = 'inventario';

  constructor(private readonly backendService: BackendService) {}

  getInventarios(): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getInventario(id: number): Observable<Inventario> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  getInventarioPorUbicacion(idUbicacion: number): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, `listar-por-ubicacion/${idUbicacion}`);
  }

  getProductosBajoStock(): Observable<Inventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'productos-bajo-stock');
  }

  guardarInventario(inventario: Inventario): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', inventario);
  }

  actualizarInventario(inventario: Inventario): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', inventario);
  }
}

