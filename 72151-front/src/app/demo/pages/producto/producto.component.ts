/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Producto } from 'src/app/models/producto';
import { ProductoService } from './service/producto.service';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { FormBuilder, FormControl, FormGroup, Validators, FormsModule, ReactiveFormsModule, AbstractControl } from '@angular/forms';
import { Marca } from 'src/app/models/marca';
import { MarcaService } from 'src/app/services/marca.service';
import { Raza } from 'src/app/models/raza';
import { RazaService } from 'src/app/services/raza.service';
import { MessageUtils } from 'src/app/utils/message-utils';
import { ProductoMasVendido } from 'src/app/models/producto-mas-vendido';

declare const bootstrap: any;

@Component({
  selector: 'app-producto',
  standalone: true,
  imports: [NgxSpinnerModule, ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './producto.component.html',
  styleUrl: './producto.component.scss'
})
export class ProductoComponent implements OnInit {
  msjSpinner: string = '';
  modalInstance: any;
  modoFormulario: string = '';
  titleModal: string = '';

  productoSelected: Producto;
  productos: Producto[] = [];
  marcas: Marca[] = [];
  razas: Raza[] = [];
  razasSeleccionadas: number[] = [];

  // Propiedades para Productos Más Vendidos
  diasOptions: number[] = [10, 15, 20, 30];
  limitOptions: number[] = [3, 5, 10];
  selectedDias: number = 30;
  selectedLimit: number = 3;
  productosMasVendidos: ProductoMasVendido[] = [];
  mostrarProductosMasVendidos: boolean = false;

  form: FormGroup = new FormGroup({
    nombreProducto: new FormControl(''),
    descripcion: new FormControl(''),
    precioVenta: new FormControl(''),
    precioCompra: new FormControl(''),
    estado: new FormControl('activo'),
    pesoKg: new FormControl(''),
    marca: new FormControl('')
  });

  constructor(
    private readonly messageUtils: MessageUtils,
    private readonly productoService: ProductoService,
    private readonly spinner: NgxSpinnerService,
    private readonly formBuilder: FormBuilder,
    private readonly marcaService: MarcaService,
    private readonly razaService: RazaService
  ) {
    this.cargarFormulario();
  }

  ngOnInit(): void {
    this.getProductos(); // Load main product list by default
    this.getMarcas();
    this.getRazas();
  }

  getMarcas() {
    this.marcaService.getMarcas().subscribe({
      next: (data) => {
        this.marcas = data;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  getRazas() {
    this.razaService.getRazasActivas().subscribe({
      next: (data) => {
        this.razas = data;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  cargarFormulario() {
    this.form = this.formBuilder.group({
      nombreProducto: ['', [Validators.required]],
      descripcion: [''],
      precioVenta: ['', [Validators.required, Validators.min(0.01)]],
      precioCompra: ['', [Validators.required, Validators.min(0.01)]],
      estado: ['activo', [Validators.required]],
      pesoKg: ['', [Validators.min(0)]],
      marca: ['', [Validators.required]]
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  getProductos() {
    this.mostrarProductosMasVendidos = false; // Hide most sold products view
    this.productoService.getProductos().subscribe({
      next: (data) => {
        this.productos = data;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  getProductosMasVendidos(): void {
    this.msjSpinner = 'Cargando productos más vendidos...';
    this.spinner.show();
    this.mostrarProductosMasVendidos = true; // Show most sold products view
    this.productos = []; // Clear main product list
    this.productoService.getProductosMasVendidos(this.selectedDias, this.selectedLimit).subscribe({
      next: (data) => {
        this.productosMasVendidos = data;
        this.spinner.hide();
      },
      error: (error) => {
        this.spinner.hide();
        this.messageUtils.showMessage('Error', 'No se pudieron cargar los productos más vendidos', 'error');
        console.error('Error al cargar productos más vendidos:', error);
      }
    });
  }

  onMasVendidosChange(): void {
    this.getProductosMasVendidos();
  }

  toggleProductosMasVendidos(): void {
    if (this.mostrarProductosMasVendidos) {
      // If currently showing most sold, switch back to main products
      this.getProductos();
    } else {
      // If not showing most sold, switch to most sold products
      this.getProductosMasVendidos();
    }
  }

  crearModal(modoForm: string) {
    this.modoFormulario = modoForm;
    this.titleModal = modoForm == 'C' ? 'Crear Producto' : 'Editar Producto';
    const modalElement = document.getElementById('crearModal');
    if (modalElement) {
      this.modalInstance ??= new bootstrap.Modal(modalElement);
      this.modalInstance.show();
    }
    if (this.modoFormulario == 'C') {
      this.form.reset({
        nombreProducto: '',
        descripcion: '',
        precioVenta: '',
        precioCompra: '',
        estado: 'activo',
        pesoKg: '',
        marca: ''
      });
      this.razasSeleccionadas = [];
    }
  }

  abrirModoEdicion(producto: Producto) {
    this.productoSelected = producto;
    this.razasSeleccionadas = producto.razas?.map(r => r.idRaza) || [];
    this.form.patchValue({
      nombreProducto: this.productoSelected.nombreProducto,
      descripcion: this.productoSelected.descripcion || '',
      precioVenta: this.productoSelected.precioVenta,
      precioCompra: this.productoSelected.precioCompra,
      estado: this.productoSelected.estado,
      pesoKg: this.productoSelected.pesoKg || '',
      marca: this.productoSelected.marca?.idMarca
    });
    this.crearModal('E');
  }

  cerrarModal() {
    this.form.reset();
    this.form.markAsPristine();
    this.form.markAsUntouched();
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
    this.productoSelected = null;
    this.razasSeleccionadas = [];
  }

  toggleRaza(idRaza: number) {
    const index = this.razasSeleccionadas.indexOf(idRaza);
    if (index > -1) {
      this.razasSeleccionadas.splice(index, 1);
    } else {
      this.razasSeleccionadas.push(idRaza);
    }
  }

  isRazaSeleccionada(idRaza: number): boolean {
    return this.razasSeleccionadas.includes(idRaza);
  }

  guardarActualizar() {
    this.msjSpinner = 'Guardando';
    this.spinner.show();
    if (this.form.valid) {
      const formValue = this.form.getRawValue();
      const marcaId = formValue.marca;

      const productoData: Producto = {
        ...formValue,
        marca: { idMarca: marcaId },
        razas: this.razasSeleccionadas.map(id => ({ idRaza: id }))
      };

      if (this.modoFormulario === 'C') {
        this.productoService.crearProducto(productoData).subscribe({
          next: (data) => {
            this.spinner.hide();
            this.messageUtils.showMessage('Éxito', data.message, 'success');
            this.cerrarModal();
            this.getProductos();
          },
          error: (error) => {
            this.spinner.hide();
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al guardar', 'error');
          }
        });
      } else {
        const productoActualizado: Producto = {
          idProducto: this.productoSelected.idProducto,
          ...productoData
        };
        this.productoService.actualizarProducto(productoActualizado).subscribe({
          next: (data) => {
            this.spinner.hide();
            this.messageUtils.showMessage('Éxito', data.message, 'success');
            this.cerrarModal();
            this.getProductos();
          },
          error: (error) => {
            this.spinner.hide();
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al actualizar', 'error');
          }
        });
      }
    } else {
      this.spinner.hide();
      this.messageUtils.showMessage('Advertencia', 'El formulario no es válido', 'warning');
    }
  }
}


