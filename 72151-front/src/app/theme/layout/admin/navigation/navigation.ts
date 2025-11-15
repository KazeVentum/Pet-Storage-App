export interface NavigationItem {
  id: string;
  title: string;
  type: 'item' | 'collapse' | 'group';
  translate?: string;
  icon?: string;
  hidden?: boolean;
  url?: string;
  classes?: string;
  exactMatch?: boolean;
  external?: boolean;
  target?: boolean;
  breadcrumbs?: boolean;

  children?: NavigationItem[];
}

export const NavigationItems: NavigationItem[] = [
  {
    id: 'navigation',
    title: 'Sistema de Inventario',
    type: 'group',
    icon: 'icon-navigation',
    children: [
      {
        id: 'usuario',
        title: 'Usuarios',
        type: 'item',
        url: '/inicio/usuarios',
        icon: 'feather icon-user',
        classes: 'nav-item'
      },
      {
        id: 'producto',
        title: 'Productos',
        type: 'item',
        url: '/inicio/productos',
        icon: 'feather icon-package',
        classes: 'nav-item'
      },
      {
        id: 'marca',
        title: 'Marcas',
        type: 'item',
        url: '/inicio/marcas',
        icon: 'feather icon-tag',
        classes: 'nav-item'
      },
      {
        id: 'raza',
        title: 'Razas',
        type: 'item',
        url: '/inicio/razas',
        icon: 'feather icon-gitlab',
        classes: 'nav-item'
      },
      {
        id: 'proveedor',
        title: 'Proveedores',
        type: 'item',
        url: '/inicio/proveedores',
        icon: 'feather icon-grid',
        classes: 'nav-item'
      }
    ]
  },
  /* ---------- Nuevos menus aqui -------------  */  
];
