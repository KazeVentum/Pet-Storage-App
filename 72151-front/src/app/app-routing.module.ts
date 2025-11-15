import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './theme/layout/admin/admin.component';
import { UsuarioComponent } from './demo/pages/usuario/usuario.component';
import { ProductoComponent } from './demo/pages/producto/producto.component';
import { MarcaComponent } from './demo/pages/marca/marca.component';
import { RazaComponent } from './demo/pages/raza/raza.component';
import { ProveedorComponent } from './demo/pages/proveedor/proveedor.component';
import { InventarioComponent } from './demo/pages/inventario/inventario.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full'
  },  
  {
    path: 'inicio',
    component: AdminComponent,
    data: { title: 'Inicio' },
    children: [      
      { path: 'usuarios', component: UsuarioComponent, data: { title: 'Usuarios' }},
      { path: 'productos', component: ProductoComponent, data: { title: 'Productos' }},
      { path: 'marcas', component: MarcaComponent, data: { title: 'Marcas' }},
      { path: 'razas', component: RazaComponent, data: { title: 'Razas' }},
      { path: 'proveedores', component: ProveedorComponent, data: { title: 'Proveedores' }}, 
      { path: 'inventario', component: InventarioComponent, data: { title: 'Inventario' }}
    ]
  },
  { path: '**', redirectTo: 'inicio' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
