import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Marca } from '../models/marca';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {
  private readonly api = 'marca';

  constructor(private readonly backendService: BackendService) {}

  getMarcas(): Observable<Marca[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }

  guardarMarca(marca: Marca): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'guardar', marca);
  }

  actualizarMarca(marca: Marca): Observable<any> {
    return this.backendService.put(environment.apiUrl, this.api, 'actualizar', marca);
  }

  eliminarMarca(id: number): Observable<any> {
    return this.backendService.post(environment.apiUrl, this.api, 'eliminar', { id_marca: id });
  }
}

