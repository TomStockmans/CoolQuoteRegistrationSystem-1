import environment from './environment';
import 'whatwg-fetch';
import {HttpClient} from 'aurelia-fetch-client';

//Configure Bluebird Promises.
//Note: You may want to use environment-specific configuration.
Promise.config({
  warnings: {
    wForgottenReturn: false
  }
});


export function configure(aurelia) {
  aurelia.use
    .standardConfiguration()
    .feature('resources');

  if (environment.debug) {
    aurelia.use.developmentLogging();
  }

  if (environment.testing) {
    aurelia.use.plugin('aurelia-testing');
  }
  
  let http = new HttpClient();
  http.configure(config => {
    config
      .useStandardConfiguration()
      .withBaseUrl('api/')
      .withDefaults({
        credentials: 'same-origin',
        headers: {
          'X-Requested-With': 'Fetch'
        }
      })
    ;
  });
  aurelia.container.registerInstance(HttpClient, http);

  aurelia.start().then(() => aurelia.setRoot());
}
