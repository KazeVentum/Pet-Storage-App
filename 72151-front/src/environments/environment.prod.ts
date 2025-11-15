import packageInfo from '../../package.json';

export const environment = {
  appVersion: packageInfo.version,
  production: true,
  apiUrl: '/sistema-inventario/v1'  // Usa proxy de Nginx
};
