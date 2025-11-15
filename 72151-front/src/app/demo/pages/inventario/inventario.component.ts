import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { InventarioService } from 'src/app/services/inventario.service';
import { UbicacionService } from 'src/app/services/ubicacion.service';
import { Inventario } from 'src/app/models/inventario';
import { Ubicacion } from 'src/app/models/ubicacion';
import { MessageUtils } from 'src/app/utils/message-utils';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxSpinnerModule],
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.scss']
})
export class InventarioComponent implements OnInit {
  inventarios: Inventario[] = [];
  ubicaciones: Ubicacion[] = [];
  selectedUbicacion: number | null = null;
  msjSpinner: string = 'Cargando...';
  isLowStockView: boolean = false;

  constructor(
    private readonly inventarioService: InventarioService,
    private readonly ubicacionService: UbicacionService,
    private readonly spinner: NgxSpinnerService,
    private readonly messageUtils: MessageUtils
  ) {}

  ngOnInit(): void {
    this.cargarUbicaciones();
  }

  cargarUbicaciones(): void {
    this.spinner.show();
    this.ubicacionService.getUbicaciones().subscribe({
      next: (data) => {
        this.ubicaciones = data;
        console.log('Ubicaciones cargadas:', this.ubicaciones);
        if (this.ubicaciones.length > 0 && this.ubicaciones[0].idDireccion) {
          this.selectedUbicacion = this.ubicaciones[0].idDireccion;
          this.onUbicacionChange();
        } else {
          this.spinner.hide();
        }
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudieron cargar las ubicaciones', 'error');
        console.error('Error al cargar ubicaciones:', error);
      }
    });
  }

  onUbicacionChange(): void {
    if (this.selectedUbicacion) {
      this.isLowStockView = false;
      this.spinner.show();
      this.inventarioService.listarInventariosPorUbicacion(this.selectedUbicacion).subscribe({
        next: (data) => {
          this.inventarios = data;
          console.log('Inventarios cargados:', this.inventarios);
          this.spinner.hide();
        },
        error: (error) => {
          this.spinner.hide();
          this.messageUtils.showMessage('Error', 'No se pudo cargar el inventario para esta ubicación', 'error');
          console.error('Error al cargar inventarios:', error);
        }
      });
    } else {
      this.inventarios = [];
    }
  }

  mostrarProductosBajoStock(): void {
    this.isLowStockView = true;
    this.selectedUbicacion = null;
    this.spinner.show();
    this.inventarioService.listarProductosBajoStock().subscribe({
      next: (data) => {
        this.inventarios = data;
        console.log('Productos bajo stock cargados:', this.inventarios);
        this.spinner.hide();
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudieron cargar los productos con bajo stock', 'error');
        console.error('Error al cargar productos bajo stock:', error);
      }
    });
  }

  getStockStatusColor(item: Inventario): string {
    const percentage = (item.stockActual / item.stockMaximo) * 100;
    if (percentage <= (item.stockMinimo / item.stockMaximo) * 100) {
      return 'bg-danger'; // Bajo stock
    } else if (percentage <= 50) {
      return 'bg-warning'; // Stock medio
    }
    return 'bg-success'; // Stock alto
  }

  getNombreUbicacion(item: Inventario): string {
    try {
      if (this.isLowStockView) {
        return item.ubicacion?.nombreUbicacion || 'N/A';
      } else {
        if (this.selectedUbicacion === null || this.selectedUbicacion === undefined) {
          return 'N/A';
        }
        const ubicacion = this.ubicaciones.find(u => u.idDireccion === this.selectedUbicacion);
        return ubicacion?.nombreUbicacion || 'N/A';
      }
    } catch (error) {
      console.error('Error en getNombreUbicacion:', error);
      return 'N/A';
    }
  }
}