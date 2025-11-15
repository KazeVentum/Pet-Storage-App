/* eslint-disable @typescript-eslint/no-explicit-any */
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageUtils } from 'src/app/utils/message-utils';
import { MarcaService } from 'src/app/services/marca.service';
import { Marca } from 'src/app/models/marca';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule, AbstractControl } from '@angular/forms';

declare const bootstrap: any;

@Component({
  selector: 'app-marca',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './marca.component.html',
  styleUrl: './marca.component.scss'
})
export class MarcaComponent {
  marcas: Marca[] = [];
  modalInstance: any;
  modoFormulario: string = '';
  titleModal: string = '';
  marcaSelected: Marca;

  form: FormGroup;

  constructor(
    private readonly messageUtils: MessageUtils,
    private readonly marcaService: MarcaService,
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      nombreMarca: ['', [Validators.required]]
    });
    this.cargarListaMarcas();
  }

  cargarListaMarcas() {
    this.marcaService.getMarcas().subscribe({
      next: (data) => {
        this.marcas = data;
      },
      error: (error) => {
        console.log(error);
        this.messageUtils.showMessage('Error', 'No se pudieron cargar las marcas', 'error');
      }
    });
  }

  crearMarcaModal(modoForm: string) {
    this.modoFormulario = modoForm;
    this.titleModal = modoForm == 'C' ? 'Crear Marca' : 'Editar Marca';
    const modalElement = document.getElementById('crearMarcaModal');
    if (modalElement) {
      this.modalInstance ??= new bootstrap.Modal(modalElement);
      this.modalInstance.show();
    }
    if (modoForm === 'C') {
      this.form.reset();
      this.marcaSelected = null;
    }
  }

  cerrarModal() {
    this.form.reset();
    this.form.markAsPristine();
    this.form.markAsUntouched();
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
    this.marcaSelected = null;
  }

  abrirModoEdicion(marca: Marca) {
    this.crearMarcaModal('E');
    this.marcaSelected = marca;
    this.form.patchValue({
      nombreMarca: marca.nombreMarca
    });
  }

  guardarMarca() {
    if (this.form.valid) {
      const marcaData: Marca = {
        idMarca: this.modoFormulario === 'E' ? this.marcaSelected?.idMarca : undefined,
        nombreMarca: this.form.get('nombreMarca')?.value
      };

      if (this.modoFormulario === 'C') {
        this.marcaService.guardarMarca(marcaData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Marca creada exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaMarcas();
          },
          error: (error) => {
            this.messageUtils.showMessage('Error', error.error?.message || 'Error al guardar', 'error');
          }
        });
      } else {
        this.marcaService.actualizarMarca(marcaData).subscribe({
          next: (data) => {
            this.messageUtils.showMessage('Éxito', data.message || 'Marca actualizada exitosamente', 'success');
            this.cerrarModal();
            this.cargarListaMarcas();
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

