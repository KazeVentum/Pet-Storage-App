/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { MessageUtils } from 'src/app/utils/message-utils';
import { PedidoCompraDetalle } from 'src/app/models/pedido-compra-detalle';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router'; // Removed ActivatedRoute

@Component({
  selector: 'app-pedido-compra',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './pedido-compra.component.html',
  styleUrl: './pedido-compra.component.scss'
})
export class PedidoCompraComponent implements OnInit {
  pedidosDetalle: PedidoCompraDetalle[] = [];
  estados: string[] = ['pendiente', 'recibido', 'cancelado'];
  selectedEstado: string = this.estados[0]; // Default to 'pendiente'

  constructor(
    private readonly pedidoCompraService: PedidoCompraService,
    private readonly messageUtils: MessageUtils,
    private readonly router: Router // Inject Router
  ) { }

  ngOnInit(): void {
    const savedEstado = localStorage.getItem('pedidosCompraSelectedEstado');
    if (savedEstado && this.estados.includes(savedEstado)) {
      this.selectedEstado = savedEstado;
    } else {
      this.selectedEstado = this.estados[0]; // Default if no valid param
    }
    this.cargarPedidosDetalle(); // Load data based on selected or default state
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
    localStorage.setItem('pedidosCompraSelectedEstado', this.selectedEstado); // Save to localStorage
    this.cargarPedidosDetalle();
  }

  abrirDetallePedidoModal(idPedido: number): void {
    this.router.navigate(['/inicio/pedidos-compra', idPedido, 'detalle']); // No query params
  }
}
