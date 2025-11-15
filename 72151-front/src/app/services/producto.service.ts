import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private readonly api = 'producto';

  constructor(private readonly backendService: BackendService) {}

  getProductos(): Observable<Producto[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  getProducto(id: number): Observable<Producto> {
    return this.backendService.get(environment.apiUrl, this.api, `obtener/${id}`);
  }

  getProductosActivos(): Observable<Producto[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar-activos');
  }

  guardarProducto(producto: Producto): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', producto);
  }

  actualizarProducto(producto: Producto): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', producto);
  }

  eliminarProducto(id: number): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'eliminar', { id_producto: id });
  }
}

