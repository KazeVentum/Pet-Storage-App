/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { MessageUtils } from 'src/app/utils/message-utils';
import { PedidoCompraDetalle } from 'src/app/models/pedido-compra-detalle';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel

@Component({
  selector: 'app-pedido-compra',
  standalone: true,
  imports: [CommonModule, FormsModule], // Add FormsModule
  templateUrl: './pedido-compra.component.html',
  styleUrl: './pedido-compra.component.scss'
})
export class PedidoCompraComponent implements OnInit {
  pedidosDetalle: PedidoCompraDetalle[] = [];
  estados: string[] = ['pendiente', 'recibido', 'cancelado'];
  selectedEstado: string = this.estados[0]; // Default to 'pendiente'

  constructor(
    private readonly pedidoCompraService: PedidoCompraService,
    private readonly messageUtils: MessageUtils
  ) { }

  ngOnInit(): void {
    this.cargarPedidosDetalle();
  }

  cargarPedidosDetalle(): void {
    this.pedidoCompraService.getPedidosDetallePorEstado(this.selectedEstado).subscribe({
      next: (data) => {
        this.pedidosDetalle = data;
      },
      error: (error) => {
        console.error('Error al cargar pedidos por estado:', error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar los pedidos de compra detallados', 'error');
      }
    });
  }

  onEstadoChange(event: any): void {
    this.selectedEstado = event.target.value;
    this.cargarPedidosDetalle();
  }
}
