import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovimientoInventario } from '../models/movimiento-inventario';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovimientoInventarioService {
  private readonly api = 'movimiento-inventario';

  constructor(private readonly backendService: BackendService) {}

  getMovimientos(): Observable<MovimientoInventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getMovimiento(id: number): Observable<MovimientoInventario> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  guardarMovimiento(movimiento: MovimientoInventario): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', movimiento);
  }
}

