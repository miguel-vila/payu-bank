angular.module('payuBank.controllers').controller('AccountDetailController', function ($scope, $http, $state, $stateParams) {
	
	$http.get('/accounts/'+$stateParams.accountId).then(function (reponse) {
		$scope.account = reponse.data;
	}, function (error) {
		console.log('error = ',error);
	});

	$scope.createMovement = function () {
		$http.post('/accounts/'+$stateParams.accountId+'/movements',$scope.newMov).then(function (response) {
			$scope.account.movements.push($scope.newMov);
			$scope.newMov = {};
		},function (error) {
			console.log('error ',error);
		});
	};

});