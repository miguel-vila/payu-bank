angular.module('payuBank.controllers').controller('ClientsController', function ($scope, $http) {
	$scope.msg = "soy del scope";
	$http.get('/clients').then(function (response) {
		console.log('data = ',response.data);
		$scope.clients = response.data;
	}, function (error) {
		console.log('error = ',error);
	});

	$scope.delete = function (clientId) {
		$http.delete('/clients/'+clientId).then(function () {
			$scope.clients = $scope.clients.filter(function (client) {
				return client.id !== clientId;
			});	
		}, function (error) {
			console.log('error deleting: ',error);
		});
	};
});