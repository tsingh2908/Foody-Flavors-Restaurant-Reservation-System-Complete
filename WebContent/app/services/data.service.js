
/** AngularJS Data Service that uses AngularJS $http service to add or retrieve data for 'foody-flavors' app from the database using RESTful API on the server **/

(function () {
    'use strict';

    angular.module('myService', []).service('dataService', dataServiceFn);

    dataServiceFn.$inject = ['$q','$http'];
    
    function dataServiceFn($q, $http) {
        var self = this;
        
        self.authenticateOwner = authenticateOwner;
        self.addRes = addRes;
        self.getRes = getRes;
        self.updateRes = updateRes;
        self.getAllRes = getAllRes;
        self.getReservedTable = getReservedTable;
        self.assignTable = assignTable;
        self.getAllTables = getAllTables;
        self.getProfile = getProfile;
        self.updateProfile = updateProfile;
        self.getAllTimings = getAllTimings;
        self.getAllCust = getAllCust;
        self.getPastReservations = getPastReservations;
        
        //function to get owner's existing Login Credentials and authenticate the owner
        function authenticateOwner(){
			var defer = $q.defer();
			
			$http({
				method: 'GET',
				url: 'api/login/get'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
        
        //function to add/create a new reservation
		function addRes(reservation) {
			var defer = $q.defer(); 
            
			$http({
				method: 'POST',
				url: 'api/reservation/create',
				data: reservation
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;					
		}
		
		
		//function to get a single Reservation based on Reservation Confirmation Code
		function getRes(cnfCode) {
			var defer = $q.defer(); 
			
			$http({
				method: 'GET',
				url: 'api/reservation/get/' + cnfCode
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		
		
		//function to edit/update an existing Reservation based on Reservation Confirmation Code
		function updateRes(cnfCode, oldReservation) {
			var defer = $q.defer();
			
			$http({
				method: 'PUT',
				url: 'api/reservation/put/' + cnfCode,
				data: oldReservation
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;
		}
		
		
		//function to delete/cancel an existing Reservation based on Reservation Confirmation Code
		/*Not Implemented Yet*/
     
		
		//function to get All Reservations
		function getAllRes(){
			var defer = $q.defer(); 
			
			$http({
				method: 'GET',
				url: 'api/reservation/all'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;
		}
		
		
		//function to get a single Table already assigned to a Reservation based on Reservation Confirmation Code
		function getReservedTable(cnfCode){
			var defer = $q.defer(); 
			
			$http({
				method: 'GET',
				url: 'api/seatingarea/get/' + cnfCode
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
			
		//function to assign/update a selected Table for an existing Reservation manually based on Table Id
		function assignTable(tableId, selectedTable){
			var defer = $q.defer();
			
			$http({
				method: 'PUT',
				url: 'api/seatingarea/put/' + tableId,
				data: selectedTable
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;
		}
		
		
		//function to get All Tables
		function getAllTables(){
			var defer = $q.defer(); 
			
			$http({
				method: 'GET',
				url: 'api/seatingarea/all'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		
		
		//function to get existing Restaurant Profile
		 function getProfile(){
			var defer = $q.defer();
			
			$http({
				method: 'GET',
				url: 'api/profile/get'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		 
		 
		//function to edit/update Restaurant Profile
		function updateProfile(profile){
			var defer = $q.defer();
			
			$http({
				method: 'PUT',
				url: 'api/profile/put',
				data: profile
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;
		} 
		
		
		//function to get all Restaurant Business Hours/Timings
		function getAllTimings(){
			var defer = $q.defer();
			
			$http({
				method: 'GET',
				url: 'api/timings/all'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		
		
		//function to get All Customer Contacts
		function getAllCust(){
			var defer = $q.defer();
			
			$http({
				method: 'GET',
				url: 'api/customer/all'
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		
		
		//function to get all Past Reservations(if any) of an existing Customer based on Customer Id
		function getPastReservations(custId){
			var defer = $q.defer();
			
			$http({
				method: 'GET',
				url: 'api/reservation/get/past/' + custId
			})
				.success(function (data) {
					defer.resolve(data);
				})
				.error(function (err) {
					defer.reject(err)
				});
			
			return defer.promise;	
		}
		
    }

})();