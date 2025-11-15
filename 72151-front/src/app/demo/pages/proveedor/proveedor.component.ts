/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageUtils } from 'src/app/utils/message-utils';
import { ProveedorService } from 'src/app/services/proveedor.service';
import { Proveedor } from 'src/app/models/proveedor';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule, AbstractControl } from '@angular/forms';

declare const bootstrap: any;

@Component({
  selector: 'app-proveedor',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './proveedor.component.html',
  styleUrl: './proveedor.component.scss'
})
export class ProveedorComponent {
  proveedores: Proveedor[] = [];
  modalInstance: any;
  modoFormulario: string = '';
  titleModal: string = '';
  proveedorSelected: Proveedor;

  form: FormGroup;

  constructor(
    private readonly messageUtils: MessageUtils,
    private readonly proveedorService: ProveedorService,
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      nombreProveedor: ['', [Validators.required]],
      telefono: [''],
      email: ['', [Validators.email]],
      activo: [true]
    });
    this.cargarListaProveedores();
  }

  cargarListaProveedores() {
    this.proveedorService.getProveedores().subscribe({
      next: (data) => {
        this.proveedores = data;
      },
      error: (error) => {
        console.log(error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar los proveedores', 'error');
      }
    });
  }

  crearProveedorModal(modoForm: string) {
    this.modoFormulario = modoForm;
    this.titleModal = modoForm == 'C' ? 'Crear Proveedor' : 'Editar Proveedor';
    const modalElement = document.getElementById('crearProveedorModal');
    if (modalElement) {
      this.modalInstance ??= new bootstrap.Modal(modalElement);
      this.modalInstance.show();
    }
    if (modoForm === 'C') {
      this.form.reset({
        activo: true
      });
      this.proveedorSelected = null;
    }
  }

  cerrarModal() {
    this.form.reset();
    this.form.markAsPristine();
    this.form.markAsUntouched();
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
    this.proveedorSelected = null;
  }

  abrirModoEdicion(proveedor: Proveedor) {
    this.crearProveedorModal('E');
    this.proveedorSelected = proveedor;
    this.form.patchValue({
      nombreProveedor: proveedor.nombreProveedor,
      telefono: proveedor.telefono || '',
      email: proveedor.email || '',
      activo: proveedor.activo
    });
  }

  guardarProveedor() {
    if (this.form.valid) {
      const proveedorData: Proveedor = {
        idProveedor: this.modoFormulario === 'E' ? this.proveedorSelected?.idProveedor : undefined,
        nombreProveedor: this.form.get('nombreProveedor')?.value,
        telefono: this.form.get('telefono')?.value || null,
        email: this.form.get('email')?.value || null,
        activo: this.form.get('activo')?.value
      };

      if (this.modoFormulario === 'C') {
        this.proveedorService.guardarProveedor(proveedorData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Proveedor creado exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaProveedores();
          },
          error: (error) => {
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al guardar', 'error');
          }
        });
      } else {
        this.proveedorService.actualizarProveedor(proveedorData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Proveedor actualizado exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaProveedores();
          },
          error: (error) => {
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al actualizar', 'error');
          }
        });
      }
    } else {
      this.messageUtils.showMessage('Advertencia', 'El formulario no es válido', 'warning');
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
}

