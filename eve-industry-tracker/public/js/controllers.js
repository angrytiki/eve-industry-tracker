/*global define */

'use strict';

define(['angular'], function(angular) {

/* Controllers */

var controllers = {};

controllers.MyCtrl1 = function($scope, Journals) {
  $scope.hello = "Hello, world!";
  
  $scope.objects = Journals.query();
}
controllers.MyCtrl1.$inject = ['$scope', 'Journals'];

controllers.MyCtrl2 = function() {}
controllers.MyCtrl2.$inject = [];

return controllers;

});