var sohagApp = angular.module('SohagApp',['ngRoute']);

sohagApp.config(function($routeProvider, $locationProvider) {
  console.log('in config ');
  /*$locationProvider.html5Mode(true);
  $locationProvider.hashPrefix("!"); //Support for hasbangs url (SEO)
  */
  $routeProvider.
  when('/dashboard',{
    templateUrl : 'templates/homepage.html',
    controller : 'HomeController as homectrl'
  }).
  when('/register',{
    templateUrl : 'templates/register.html',
    controller : 'RegisterController as regcntrl'
  }).
  when('/',{
    templateUrl : 'templates/login.html',
    controller : 'LoginController as logincntrl'
  }).
  otherwise({
    redirectTo: '/'
  }) 

 
});
