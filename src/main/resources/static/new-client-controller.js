angular.module('payuBank.controllers').controller('NewClientController', function ($scope, $http, $state) {
	$scope.client = {};

	$scope.createClient = function () {
		if($scope.newClientForm.$valid){
			$http.post('/clients', $scope.client).then(function (response) {
				$scope.client.id = response.id;
				console.log('success!');
				$state.go('payu.clients');
			}, function (error) {
				console.log('error creating client = ', error);
			});
		}
	};

});