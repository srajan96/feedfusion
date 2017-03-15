var sohagApp = angular.module('SohagApp',['ngRoute']);

sohagApp.config(function($routeProvider, $locationProvider) {
  console.log('in config ');
  /*$locationProvider.html5Mode(true);
  $locationProvider.hashPrefix("!"); //Support for hasbangs url (SEO)
  */
  $routeProvider.
  when('/',{
    templateUrl : 'templates/homepage.html',
    controller : 'HomeController as homectrl'
  })


});

