import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoMovimiento } from '../models/tipo-movimiento';
import { BackendService } from './backend.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipoMovimientoService {
  private readonly api = 'tipo-movimiento';

  constructor(private readonly backendService: BackendService) {}

  getTiposMovimiento(): Observable<TipoMovimiento[]> {
    return this.backendService.get(environment.apiUrl, this.api, 'listar');
  }
}

