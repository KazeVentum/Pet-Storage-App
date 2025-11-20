/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';
import { MessageUtils } from 'src/app/utils/message-utils';
import { DetallePedidoProducto } from 'src/app/models/detalle-pedido-producto';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-pedido-detalle-view',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedido-detalle-view.component.html',
  styleUrl: './pedido-detalle-view.component.scss'
})
export class PedidoDetalleViewComponent implements OnInit, OnDestroy {
  idPedido: number | null = null;
  detallesPedidoProducto: DetallePedidoProducto[] = [];
  totalSubtotal: number = 0;
  totalCantidadSolicitada: number = 0;
  // estadoAnterior: string | null = null; // Removed

  private routeSubscription: Subscription;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly detallePedidoService: DetallePedidoService,
    private readonly messageUtils: MessageUtils
  ) { }

  ngOnInit(): void {
    this.routeSubscription = this.route.paramMap.subscribe(params => {
      this.idPedido = Number(params.get('idPedido'));
      
      // Removed: this.estadoAnterior = this.route.snapshot.queryParamMap.get('estado'); 

      if (this.idPedido) {
        this.cargarDetallesPedido(this.idPedido);
      } else {
        this.messageUtils.showMessage('Error', 'ID de pedido no proporcionado.', 'error');
        this.goBack();
      }
    });
  }

  ngOnDestroy(): void {
    if (this.routeSubscription) {
      this.routeSubscription.unsubscribe();
    }
  }

  cargarDetallesPedido(idPedido: number): void {
    this.detallePedidoService.getDetallesPorPedido(idPedido).subscribe({
      next: (data) => {
        this.detallesPedidoProducto = data;
        this.calcularTotales();
      },
      error: (error) => {
        console.error('Error al cargar detalles del pedido:', error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar los detalles del pedido', 'error');
        this.goBack();
      }
    });
  }

  private calcularTotales(): void {
    this.totalSubtotal = this.detallesPedidoProducto.reduce((sum, detalle) => sum + (detalle.subtotal || 0), 0);
    this.totalCantidadSolicitada = this.detallesPedidoProducto.reduce((sum, detalle) => sum + (detalle.cantidadSolicitada || 0), 0);
  }

  goBack(): void {
    this.router.navigate(['/inicio/pedidos-compra']); // No query params
  }
}
