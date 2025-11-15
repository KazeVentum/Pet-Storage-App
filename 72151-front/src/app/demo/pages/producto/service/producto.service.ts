import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from 'src/app/models/producto';
import { Respuesta } from 'src/app/models/respuesta';
import { BackendService } from 'src/app/services/backend.service';
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

  getProductosActivos(): Observable<Producto[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar-activos');
  }

  crearProducto(producto: Producto): Observable<Respuesta> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', producto);
  }

  actualizarProducto(producto: Producto): Observable<Respuesta> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', producto);
  }
}

