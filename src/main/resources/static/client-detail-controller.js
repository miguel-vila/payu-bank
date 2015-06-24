angular.module('payuBank.controllers').controller('ClientDetailController', function ($scope, $http, $stateParams, $state) {

	$http.get('/clients/'+$stateParams.clientId).then(function (response) {
		$scope.client = response.data;
	}, function (error) {
		console.log('error consultando cliente = ', error);
	});

	$http.get('/clients/'+$stateParams.clientId+'/accounts').then(function (response) {
			$scope.accounts = response.data;
		},function (error) {
			console.log('error = ',error);
	});

	$scope.update = function () {
		$http.put('/clients/'+$scope.client.id, $scope.client).then(function (response) {
			$scope.responseMessage = "Client successfully updated!";
		}, function (error) {
			console.log('error = ',error);
		});
	};

	$scope.createEmptyAccount = function () {
		$http.post('/clients/'+$scope.client.id+'/accounts').then(function (response) {
			var accountId = response.data.id;
			$state.go('payu.account-detail', {accountId: accountId});
		}, function (error) {
			console.log('error = ',error);
		});
	};

});