var sohagApp = angular.module('SohagApp');

sohagApp.factory('SohagRootService', function ($http) {

    return {
	
		getHomePageData: function () {

            var req = {
                method: 'POST',
                url: sohagServerUrl + "getHomePageData",
                headers: {
                    'Content-Type': "text/html"
                },
                data: ""
            }
            return $http(req);
        }
      
    }
});
