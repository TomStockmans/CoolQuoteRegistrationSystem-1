import environment from './environment';
import 'whatwg-fetch';
import {HttpClient} from 'aurelia-fetch-client';
import {I18N} from 'aurelia-i18n'; 
import Backend from 'i18next-xhr-backend';

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
    .plugin('aurelia-validation')
    .plugin('aurelia-i18n', (instance) => {
      // register backend plugin
      instance.i18next.use(Backend);

      // adapt options to your needs (see http://i18next.com/docs/options/)
      // make sure to return the promise of the setup method, in order to guarantee proper loading
      return instance.setup({
        backend: {                                   // <-- configure backend settings
          loadPath: './locales/{{lng}}/{{ns}}.json' // <-- XHR settings for where to get the files from
        },
        lng : 'en',
        defaultNS: 'translation',
        attributes : ['t','i18n'],
        fallbackLng : 'en',
        debug : false
      });
    })
    .plugin('aurelia-notification', config => {
      config.configure({
        translate: true,  // 'true' needs aurelia-i18n to be configured
        notifications: {
          'success': 'humane-jackedup-success',
          'error': 'humane-jackedup-error',
          'info': 'humane-jackedup-info'
        }
      })
    })
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
      .withBaseUrl('http://localhost:8080/api/')
      .withDefaults({
        credentials: 'same-origin',
        headers: {
          'X-Requested-With': 'Fetch'
        }
      })
      .withInterceptor({
        request(request) {
          if (environment.debug || environment.testing) {
            console.log(`Requesting ${request.method} ${request.url}`);
          }
          return request;
        },
        response(response) {
          console.log(`Received ${response.status} ${response.url}`);
          return response;
        }
      });
    ;
  });
  aurelia.container.registerInstance(HttpClient, http);

  aurelia.start().then(() => aurelia.setRoot());
}
