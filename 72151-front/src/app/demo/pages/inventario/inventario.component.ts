import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { InventarioService } from 'src/app/services/inventario.service';
import { UbicacionService } from 'src/app/services/ubicacion.service';
import { Inventario } from 'src/app/models/inventario';
import { Ubicacion } from 'src/app/models/ubicacion';
import { InventarioResumen } from 'src/app/models/inventario-resumen';
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
  selectedUbicacion: number | null = -1; // Default to -1 for "Todas"
  msjSpinner: string = 'Cargando...';
  isLowStockView: boolean = false;
  inventarioResumen: InventarioResumen[] = [];
  mostrarResumen: boolean = false;

  constructor(
    private readonly inventarioService: InventarioService,
    private readonly ubicacionService: UbicacionService,
    private readonly spinner: NgxSpinnerService,
    private readonly messageUtils: MessageUtils
  ){}

  ngOnInit(): void {
    this.cargarUbicaciones();
  }

  cargarUbicaciones(): void {
    this.spinner.show();
    this.ubicacionService.getUbicaciones().subscribe({
      next: (data) => {
        this.ubicaciones = data;
        console.log('Ubicaciones cargadas:', this.ubicaciones);
        this.spinner.hide();
        this.mostrarResumenInventario(); // Call summary AFTER ubicaciones are loaded
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudieron cargar las ubicaciones', 'error');
        console.error('Error al cargar ubicaciones:', error);
      }
    });
  }

  cargarTodosLosInventarios(): void {
    this.isLowStockView = false;
    this.mostrarResumen = false;
    this.spinner.show();
    this.inventarioService.listarTodosLosInventarios().subscribe({
      next: (data) => {
        this.inventarios = data;
        console.log('Todos los inventarios cargados:', this.inventarios);
        this.spinner.hide();
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudieron cargar todos los inventarios', 'error');
        console.error('Error al cargar todos los inventarios:', error);
      }
    });
  }

  mostrarResumenInventario(): void {
    this.isLowStockView = false;
    this.inventarios = []; // Clear other views
    this.selectedUbicacion = -1; // Set to -1 for "Todas" when summary is active
    this.mostrarResumen = true;
    this.spinner.show();
    this.inventarioService.obtenerResumenInventario().subscribe({
      next: (data) => {
        this.inventarioResumen = data.map(resumen => {
          const ubicacion = this.ubicaciones.find(u => u.nombreUbicacion === resumen.nombreUbicacion);
          return {
            ...resumen,
            idDireccion: ubicacion ? ubicacion.idDireccion : undefined // Assign idDireccion if found
          };
        });
        console.log('Resumen de Inventario cargado:', this.inventarioResumen);
        this.spinner.hide();
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudo cargar el resumen de inventario', 'error');
        console.error('Error al cargar resumen de inventario:', error);
      }
    });
  }

  onUbicacionChange(): void {
    this.isLowStockView = false;
    this.mostrarResumen = false; // Hide summary when a specific location is selected or "Todas"
    this.inventarios = []; // Clear current inventory display

    if (this.selectedUbicacion === -1) {
      this.cargarTodosLosInventarios();
    } else if (this.selectedUbicacion !== null) {
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
      // If selectedUbicacion is null (e.g., "Seleccione una ubicación" disabled option), show summary
      this.mostrarResumenInventario();
    }
  }

  mostrarProductosBajoStock(): void {
    this.isLowStockView = true;
    this.mostrarResumen = false; 
    this.selectedUbicacion = null; 
    this.inventarios = []; 
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

  verDetalleUbicacion(ubicacionId: number): void {
    if (ubicacionId !== null && ubicacionId !== undefined) {
      this.selectedUbicacion = ubicacionId;
      this.onUbicacionChange();
    } else {
      console.error('Error: ID de ubicación no válido para ver detalle.', ubicacionId);
      this.messageUtils.showMessage('Error', 'No se pudo obtener el detalle de la ubicación. ID no válido.', 'error');
    }
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
        if (this.selectedUbicacion === null || this.selectedUbicacion === undefined || this.selectedUbicacion === -1) {
          return item.ubicacion?.nombreUbicacion || 'N/A'; // For "Todas" or summary, show item's location
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