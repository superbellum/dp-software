import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideNgxWebstorage, withLocalStorage} from 'ngx-webstorage';
import {provideHttpClient} from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideNgxWebstorage(
      withLocalStorage()
    ),
    provideHttpClient(),
  ]
};
