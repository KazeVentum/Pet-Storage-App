/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageUtils } from 'src/app/utils/message-utils';
import { RazaService } from 'src/app/services/raza.service';
import { Raza } from 'src/app/models/raza';
import { RazaInventario } from 'src/app/models/raza-inventario';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule, AbstractControl } from '@angular/forms';

declare const bootstrap: any;

@Component({
  selector: 'app-raza',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './raza.component.html',
  styleUrl: './raza.component.scss'
})
export class RazaComponent implements OnInit {
  razas: Raza[] = [];
  topRazasInventario: RazaInventario[] = [];
  modalInstance: any; // For crearRazaModal
  topRazasModalInstance: any; // For topRazasInventarioModal
  modoFormulario: string = '';
  titleModal: string = '';
  razaSelected: Raza;

  form: FormGroup;

  constructor(
    private readonly messageUtils: MessageUtils,
    private readonly razaService: RazaService,
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      nombreRaza: ['', [Validators.required]],
      tamano: ['', [Validators.required]],
      activo: [true]
    });
  }

  ngOnInit(): void {
    this.cargarListaRazas();
    // this.cargarTopRazas(); // Removed automatic call
  }

  cargarListaRazas() {
    this.razaService.getRazas().subscribe({
      next: (data) => {
        this.razas = data;
      },
      error: (error) => {
        console.log(error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar las razas', 'error');
      }
    });
  }

  cargarTopRazas() {
    this.razaService.getTop5RazasByInventario().subscribe({
      next: (data) => {
        this.topRazasInventario = data;
      },
      error: (error) => {
        console.log(error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar el top de razas por inventario', 'error');
      }
    });
  }

  abrirTopRazasModal() { // New method to open the top razas modal
    this.cargarTopRazas(); // Load data before opening the modal
    const modalElement = document.getElementById('topRazasInventarioModal');
    if (modalElement) {
      this.topRazasModalInstance ??= new bootstrap.Modal(modalElement);
      this.topRazasModalInstance.show();
    }
  }

  crearRazaModal(modoForm: string) {
    this.modoFormulario = modoForm;
    this.titleModal = modoForm == 'C' ? 'Crear Raza' : 'Editar Raza';
    const modalElement = document.getElementById('crearRazaModal');
    if (modalElement) {
      this.modalInstance ??= new bootstrap.Modal(modalElement);
      this.modalInstance.show();
    }
    if (modoForm === 'C') {
      this.form.reset({
        activo: true
      });
      this.razaSelected = null;
    }
  }

  cerrarModal() { // This closes the crearRazaModal
    this.form.reset();
    this.form.markAsPristine();
    this.form.markAsUntouched();
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
    this.razaSelected = null;
  }

  abrirModoEdicion(raza: Raza) {
    this.crearRazaModal('E');
    this.razaSelected = raza;
    this.form.patchValue({
      nombreRaza: raza.nombreRaza,
      tamano: raza.tamano,
      activo: raza.activo
    });
  }

  guardarRaza() {
    if (this.form.valid) {
      const razaData: Raza = {
        idRaza: this.modoFormulario === 'E' ? this.razaSelected?.idRaza : undefined,
        nombreRaza: this.form.get('nombreRaza')?.value,
        tamano: this.form.get('tamano')?.value,
        activo: this.form.get('activo')?.value
      };

      if (this.modoFormulario === 'C') {
        this.razaService.guardarRaza(razaData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Raza creada exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaRazas();
          },
          error: (error) => {
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al guardar', 'error');
          }
        });
      } else {
        this.razaService.actualizarRaza(razaData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Raza actualizada exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaRazas();
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

