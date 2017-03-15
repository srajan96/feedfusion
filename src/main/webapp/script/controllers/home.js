var sohagApp = angular.module('SohagApp');

sohagApp.controller('HomeController', function ($scope, $routeParams, $rootScope, SohagRootService) {
    console.log('in home controller');
    $scope.home = {};
    $scope.tab=0;
    $scope.dataStatus="not-ready";
    $scope.flipped="false";
    $scope.setTab = function(tabIn){
      $scope.tab = tabIn;
    };

    $scope.isSet = function(tabIn){
      return $scope.tab === tabIn;
    };
    SohagRootService.getHomePageData().then(
        function (response) {
            $scope.home = response.data;
            console.log("loading home data");
            console.log($scope.home);
            $scope.dataStatus="ready";
        },
        function (response) {
            console.log("loading home ....");
            console.log(response);
        }

    );
    

});

