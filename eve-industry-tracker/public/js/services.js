/*global define */

'use strict';

define(['angular'], function(angular) {
  
  /* Services */
  
  // Demonstrate how to register services
  // In this case it is a simple value service.
  angular.module('myApp.services', []).
value('version', '0.1');

angular.module('services.wallet.journals', ['ngResource']).
factory('Journals', function($resource) {
  return $resource('/wallet/journals.json', {}, {
    save: {method: "PUT"}
  });
});

});