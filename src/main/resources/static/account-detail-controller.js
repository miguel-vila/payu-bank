angular.module('payuBank.controllers').controller('AccountDetailController', function ($scope, $http, $state, $stateParams) {
	
	$http.get('/accounts/'+$stateParams.accountId).then(function (reponse) {
		$scope.account = reponse.data;
	}, function (error) {
		console.log('error = ',error);
	});

	var processMovement = function (movement) {
		$scope.account.movements.push(movement);
		var difference = movement.type == 'DEBIT' ? movement.value : -movement.value;
		$scope.account.balance += difference;
	};

	$scope.initializeNewMovement = function() {
		$scope.newMov = {
			type: 'DEBIT',
			ammount: 1000
		};
	};

	$scope.createMovement = function () {
		$http.post('/accounts/'+$stateParams.accountId+'/movements',$scope.newMov).then(function (response) {
			$scope.error = null;
			processMovement(response.data);
			$scope.initializeNewMovement();
		},function (error) {
			$scope.error = error.data;
		});
	};

});