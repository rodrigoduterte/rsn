import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> Gael_Ngouana
=======
>>>>>>> d9296d760cbbd49d4e7000258e2489d7fd58631d
