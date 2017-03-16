/* global sohagServerUrl */

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
            };
            return $http(req);
        },
        getLoginData: function (userdata) {

            var req = {
                method: 'POST',
                url: sohagServerUrl + "logindata",
                headers: {
                    'Content-Type': "text/html"
                },
                data: userdata
            };
            return $http(req);
        },
        registerUser: function (userdata) {

            var req = {
                method: 'POST',
                url: sohagServerUrl + "registerdata",
                headers: {
                    'Content-Type': "json/application"
                },
                data: userdata
            };
            return $http(req);
        }
    };
    
    
});
