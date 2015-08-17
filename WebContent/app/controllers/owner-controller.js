
/** AngularJS Controller for Owner Home/Landing Page View **/

(function(){
	'use strict';
	
	angular.module('restApp').controller('OwnerCtrl', OwnerController);
	
	OwnerController.$inject = ['dataService', '$filter', '$window', '$http']
	
	function OwnerController(dataService, $filter, $window, $http){
		
		var octrl = this;
		
		octrl.owner = true;
		
		//function to get or load All Reservations on the 'Reservations' View
		octrl.getAllRes = function(){
			
			dataService.getAllRes()
            .then(function(reservations) {
        	
				if(reservations.payload != null){
					for(var i=0; i<reservations.payload.length; i++){
						var dateParts = reservations.payload[i].date.split('-');
						reservations.payload[i].date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
						reservations.payload[i].time = new Date(reservations.payload[i].time);
					}
					octrl.reservations = reservations.payload;
				}
				else
				{
					$window.alert(reservations.message);
				}
				
	            },
	            function(err){
	            console.log(err);
	        });
	                	
	    };
	    
	    //load all Reservations when the Owner Home page loads
		init();
		
		//function to get and load all Reservations when the Owner Home page loads
		function init() {
			octrl.getAllRes();
		};
		
	    //function to add/create a new reservation by the Owner
		octrl.addRes = function(){
			
			dataService.addRes(octrl.resDetail)
            .then(function(newReservation) {
				
				if(newReservation.payload != null){
					
					octrl.resDetail = null;
					octrl.reservations.push(newReservation.payload);
					$window.alert("Reservation Confirmation Code is: " + newReservation.payload.cnfCode + ". " + "Please make a note and share it with the customer.");
				}
				else
				{
					$window.alert(newReservation.message);
				}
            },		
			function(err){
	            console.log(err);
	        });
	                	
	    };
		
		
		
		//function to view more Reservation Details on the 'Reservations' View
		octrl.viewResDetails = function(position){
			octrl.resDetail = octrl.reservations[position];
		};
		
		
		//function to edit/update an existing Reservation based on Reservation Confirmation Code
		octrl.updateRes = function(){
			
			if(octrl.resDetail.cnfCode){
				dataService.updateRes(octrl.resDetail.cnfCode, octrl.resDetail)
                .then(function(updatedReservation){
                	console.log(updatedReservation);
                	if(updatedReservation.payload != null){
                		var dateParts = updatedReservation.payload.date.split('-');
                		updatedReservation.payload.date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
                		updatedReservation.payload.time = new Date(updatedReservation.payload.time);
						
						octrl.resDetail = updatedReservation.payload;
						$window.alert(updatedReservation.message);
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
			
		
		//function to get a single Table already assigned to a Reservation based on Reservation Confirmation Code
		octrl.getReservedTable = function(){
			
			if(octrl.selectedReservation.cnfCode){
				dataService.getReservedTable(octrl.selectedReservation.cnfCode)
                .then(function(reservedTable){
                	if(reservedTable.payload != null){
						
                		reservedTable.payload.since = new Date(reservedTable.payload.since);
						octrl.reservedTable = reservedTable.payload;
					}
					else
					{						
						octrl.reservedTable = null;					
					}
					
				},	
            	function(err){
                    console.log(err);
                });
			}
		};	
		
		//function to assign/change a selected Table for an existing Reservation manually based on Table Id
		octrl.assignTable = function(){
			
			if(octrl.selectedTable == null){
				
				$window.alert("Please select an available table to assign it to a reservation!")
			}
			else if (octrl.reservedTable == null) 
			{
				octrl.selectedTable.since = octrl.selectedReservation.time;
				octrl.selectedTable.cnfCode = octrl.selectedReservation.cnfCode;
								
				//assign New selected Table
				dataService.assignTable(octrl.selectedTable.tableId, octrl.selectedTable)
                .then(function(assignedTable){
            	
            		if(assignedTable.payload != null){
            			
						$window.alert(assignedTable.message);
						octrl.getAllTables();
            		}
					else{
						$window.alert(assignedTable.message);
					}
				},	
            	function(err){
                    console.log(err);
                });
			}				
			else 
			{	
				octrl.selectedTable.since = octrl.selectedReservation.time;
				octrl.selectedTable.cnfCode = octrl.selectedReservation.cnfCode;
				
				//release Old Table
				dataService.assignTable(octrl.reservedTable.tableId, octrl.reservedTable)
                .then(function(releasedTable){
                	if(releasedTable.payload != null){
                		console.log(releasedTable);
                	}
                },
                function(err){
                    console.log(err);
                });
            	
				//assign New selected Table
				dataService.assignTable(octrl.selectedTable.tableId, octrl.selectedTable)
                .then(function(assignedTable){
            		if(assignedTable.payload != null){
						$window.alert(assignedTable.message);
						assignedTable.payload.since = new Date(assignedTable.payload.since);
						octrl.reservedTable = assignedTable.payload
						octrl.getAllTables();
					}
					else
					{
						$window.alert(assignedTable.message);
					}
                },					
        		function(err){
                    console.log(err);
                });	
		
			}
						
		};
			
					
		//function to get or load All Tables on the 'Seating Area' or 'Restaurant Seating Map' View
		octrl.getAllTables = function(){
			
			octrl.selectedTable = null;
			dataService.getAllTables()
            .then(function(tables) {
            	
            	if(tables.payload != null){
            		for(var i=0; i<tables.payload.length; i++){
            			tables.payload[i].since = new Date(tables.payload[i].since);
					}
					
					octrl.tables = tables.payload;
					console.log(octrl.tables);
				}				
				else
				{
					$window.alert(tables.message);
				}
			},					
    		function(err){
                console.log(err);
            });	
					
		};
		
		
		//function to get and view Reservation Details related to an "Occupied" table on the 'Seating Area' View based on Reservation Confirmation Code 
		octrl.getAndViewRes = function(position){			
			dataService.getRes(octrl.tables[position].cnfCode)
            .then(function(reservation){
				if(reservation.payload != null){
					
					var dateParts = reservation.payload.date.split('-');
					reservation.payload.date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
					reservation.payload.time = new Date(reservation.payload.time);
					
					octrl.resDetail = reservation.payload;		
				}				
				else
				{
					$window.alert(reservation.message);
			
				}
				
			},					
    		function(err){
                console.log(err);
            });	
					
		};
	
	
		//function to get existing Restaurant Profile on the 'Restaurant Profile' View
		octrl.getProfile = function(){
			dataService.getProfile()
            .then(function(profile){
				if(profile.payload != null){
				
					octrl.profile = profile.payload;
				}				
				else
				{
					$window.alert(profile.message);
				}
			},					
    		function(err){
                console.log(err);
            });	
					
		};
		
		//function to edit/update Restaurant Profile
		octrl.updateProfile = function(){
			dataService.updateProfile(octrl.profile)
            .then(function(updatedProfile){
				if(updatedProfile.payload != null){
					$window.alert(updatedProfile.message);
				}
				else
				{
					$window.alert(updatedProfile.message);
				}
				
			},					
    		function(err){
                console.log(err);
            });	
					
		};
		
		
		//function to get or load all Restaurant Business Hours/Timings on the 'Settings' View
		octrl.getAllTimings = function(){
			dataService.getAllTimings()
            .then(function(timings){
				if(timings.payload != null){
					for(var i=0; i<timings.payload.length; i++){
						timings.payload[i].fromTime = new Date(timings.payload[i].fromTime);
						timings.payload[i].toTime = new Date(timings.payload[i].toTime);
					}
					octrl.timings = timings.payload;
				}
				else
				{
					$window.alert(timings.message);
				}
			},					
    		function(err){
                console.log(err);
            });	
					
		};	

		
		//function to edit/update Restaurant Business Hours/Timings
		/*Not Implemented Yet*/
		

		//function to get or load All Customer Contacts on the 'Customer Contacts' View
		octrl.getAllCust = function(){
			dataService.getAllCust()
            .then(function(customers){
   
            	if(customers.payload != null){
            		
            		octrl.customers = customers.payload;
				}
				else
				{
					$window.alert(customers.message);
				}
        	},					
    		function(err){
                console.log(err);
            });	
					
		};	
				
	            
		
		//function to get and display all Past Reservations(if any) of an existing Customer on 'Customer Contacts' View based on Customer Id
		octrl.getPastReservations = function(){
			
			if(octrl.selectedCustomer.custId){
				dataService.getPastReservations(octrl.selectedCustomer.custId)
	            .then(function(pastReservations){
            		if(pastReservations.payload.length != 0){
					
						for(var i=0; i<pastReservations.payload.length; i++){
							var dateParts = pastReservations.payload[i].date.split('-');
							pastReservations.payload[i].date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
							pastReservations.payload[i].time = new Date(pastReservations.payload[i].time);
						}
					
						octrl.pastReservations = pastReservations.payload;
					
					}
					else
					{
						
						octrl.msgNoReservations = pastReservations.message;
						
					}
					
				},					
				function(err){
					console.log(err);
				});	
					
			}
		};	
			
		
		//function to get and view Reservation Details for any Past Reservation of an existing Customer 
		octrl.getAndViewPastRes = function(position){			
			dataService.getRes(octrl.pastReservations[position].cnfCode)
            .then(function(pastReservation){
        	
            	if(pastReservation.payload != null){
					
					var dateParts = pastReservation.payload.date.split('-');
					pastReservation.payload.date = new Date(dateParts[0], dateParts[1]-1, dateParts[2]);
					pastReservation.payload.time = new Date(pastReservation.payload.time);
					
					octrl.resDetail = pastReservation.payload;					
				}				
				else
				{
					$window.alert(pastReservation.message);
			
				}
			},					
			function(err){
				console.log(err);
			});	
					
		};	
		
		
	}
	
})();