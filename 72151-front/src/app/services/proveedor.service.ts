import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proveedor } from '../models/proveedor';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {
  private readonly api = 'proveedor';

  constructor(private readonly backendService: BackendService) {}

  getProveedores(): Observable<Proveedor[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getProveedoresActivos(): Observable<Proveedor[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar-activos');
  }

  guardarProveedor(proveedor: Proveedor): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', proveedor);
  }

  actualizarProveedor(proveedor: Proveedor): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', proveedor);
  }

  eliminarProveedor(id: number): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'eliminar', { id_proveedor: id });
  }
}

