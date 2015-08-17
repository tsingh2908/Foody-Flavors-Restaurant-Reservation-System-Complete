
/** AngularJS Controller for Application Home Page or Guest User  View **/

(function(){
	'use strict';
	
	angular.module('restApp').controller('GuestCtrl', GuestController);
	
	GuestController.$inject = ['dataService', '$filter', '$window', '$http', '$location']
	function GuestController(dataService, $filter, $window, $http, $location){
		
		var gctrl = this;
		
		gctrl.guest = true;
		
		//function to get owner's existing Login Credentials and authenticate the owner
		gctrl.authenticateOwner = function(){
			dataService.authenticateOwner()
		    .then(function(loginCredentials){
				if(loginCredentials.payload != null){
					if(gctrl.loginCredentials){
						
						if(gctrl.loginCredentials.email == loginCredentials.payload.ownerEmail && gctrl.loginCredentials.password == loginCredentials.payload.ownerPassword){
							console.log("Valid Login Credentials.");
							gctrl.guest=false;
							$location.path('/owner');
							gctrl.loginCredentials = null;
							
						}
						else
						{
							$window.alert("Invalid Login Credentials.");
							gctrl.loginCredentials = null;
						}
					}
					else
					{
						$window.alert("Please enter the Login credentials.");
					}
				}				
			},					
			function(err){
		        console.log(err);
		    });	
					
		};		


		
		//function to add/create a new reservation
        gctrl.addRes = function() {
        	
        	dataService.addRes(gctrl.newRes)
                .then(function(newReservation) {
                	if(newReservation.payload != null){
    					console.log(newReservation);
    					gctrl.newRes = null;
    					$window.alert("Your Reservation Confirmation Code is: " + newReservation.payload.cnfCode + ". " + "Please make a note of it for any future correspondence with us.");
                	}
                },
                function(err){
                console.log(err);
            });
                	
        };

		
		//function to get a single Reservation based on Reservation Confirmation Code
		gctrl.getRes = function(){
			
			if(gctrl.cnfCode){
				dataService.getRes(gctrl.cnfCode)
                .then(function(reservation){
                	
                	if(reservation.payload != null){	
						var dateParts = reservation.payload.date.split('-');
						reservation.payload.date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
						reservation.payload.time = new Date(reservation.payload.time);
						
						gctrl.oldRes = reservation.payload;
					}
					else
					{
						$window.alert(reservation.message);
						gctrl.oldRes = null;
						gctrl.cnfCode = '';
						
					}
                },	
            	function(err){
                    console.log(err);
                });
			}
		};
          
		//function to edit/update an existing Reservation based on Reservation Confirmation Code
		gctrl.updateRes = function(){
			
			if(gctrl.cnfCode){
				gctrl.oldRes.cnfCode = gctrl.cnfCode;
				dataService.updateRes(gctrl.oldRes.cnfCode, gctrl.oldRes)
                .then(function(updatedReservation){
                	console.log(updatedReservation);
                	if(updatedReservation.payload != null){
						$window.alert(updatedReservation.message);
						gctrl.oldRes = null;
						gctrl.cnfCode = '';
					}
					else
					{
						$window.alert(updatedReservation.message);
					}
                },	
            	function(err){
                    console.log(err);
                });
			}
		};	
		
		//function to delete/cancel an existing Reservation based on Reservation Confirmation Code
		/*Not Implemented Yet*/	
		
	}
	
})();