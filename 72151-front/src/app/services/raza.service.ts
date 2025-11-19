import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Raza } from '../models/raza';
import { RazaInventario } from '../models/raza-inventario'; // New import
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RazaService {
  private readonly api = 'raza';

  constructor(private readonly backendService: BackendService) {}

  getRazas(): Observable<Raza[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getRazasActivas(): Observable<Raza[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar-activas');
  }

  guardarRaza(raza: Raza): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', raza);
  }

  actualizarRaza(raza: Raza): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', raza);
  }

  eliminarRaza(id: number): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'eliminar', { id_raza: id });
  }

  getTop5RazasByInventario(): Observable<RazaInventario[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'top-inventario');
  }
}

