angular.module('payuBank.controllers').controller('DateRangeReportController', function ($scope, $http) {

	$scope.newDate = function () {
		return new Date();
	};

	$http.get('/clients').then(function (response) {
		$scope.clients = response.data;
	}, function (error) {
		console.log('error = ',error);
	});

	var formatNumber = function (n) {
		if(n<10)
			return '0'+n;
		return String(n);
	};

	$scope.startDate = function() {
		if($scope.startDay && $scope.startHour && $scope.startMinutes && $scope.startSeconds) {
			return toStringDate($scope.startDay)+" "+formatNumber($scope.startHour)+":"+formatNumber($scope.startMinutes)+":"+formatNumber($scope.startSeconds);
		}
	};

	$scope.endDate = function() {
		if($scope.endDay && $scope.endHour && $scope.endMinutes && $scope.endSeconds) {
			return toStringDate($scope.endDay)+" "+formatNumber($scope.endHour)+":"+formatNumber($scope.endMinutes)+":"+formatNumber($scope.endSeconds);
		}
	};

	var toStringDate = function (date) {
		return date.getUTCDate()+'-'+(date.getUTCMonth() + 1)+'-'+date.getFullYear();
	};

	$scope.getReport = function () {
		var startDate = $scope.startDate();
		if(!startDate){
			window.alert("Please complete the start date fields");
			return;
		}
		var endDate = $scope.endDate();
		if(!endDate){
			window.alert("Please complete the end date fields");
			return;
		}
		if(!$scope.clientId){
			window.alert("Please select some client");
			return;
		}
		var params = {
			clientId: parseInt($scope.clientId),
			start: startDate,
			end: endDate
		};

		$http.post('/reports/client-accounts-by-date-range', params).then(function (response) {
			$scope.accounts = response.data;
		},function (error) {
			console.log(error);
		});
	};

});