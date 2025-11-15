import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ubicacion } from '../models/ubicacion';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {
  private readonly api = 'ubicacion';

  constructor(private readonly backendService: BackendService) {}

  getUbicaciones(): Observable<Ubicacion[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getUbicacion(id: number): Observable<Ubicacion> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  guardarUbicacion(ubicacion: Ubicacion): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', ubicacion);
  }

  actualizarUbicacion(ubicacion: Ubicacion): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', ubicacion);
  }

  eliminarUbicacion(id: number): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'eliminar', { id_direccion: id });
  }
}

