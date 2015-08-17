
/** AngularJS Routing Configuration file **/

(function(){
    'use strict';
   
    angular.module('restApp', ['ngRoute', 'myService'])
    .config(function($routeProvider) {
    	$routeProvider
    		// route for the guest user or home page view
	        .when('/', {
	            templateUrl: 'app/views/guest.tmpl.html',            	
	        })
	        // route for the owner landing page view
	        .when('/owner', {
	            templateUrl: 'app/views/owner.tmpl.html',
	            controller: 'OwnerCtrl',
	            controllerAs: 'octrl'
	        })
	        .otherwise({
	            redirectTo: '/'
	        });
    });
    
})();