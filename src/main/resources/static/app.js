angular.module("payuBank.controllers", []);
angular.module("payuBank.services", []);
var payuBankApp = angular.module("payuBank", ["ui.router","payuBank.controllers", "payuBank.services"]);

payuBankApp.config(function ($stateProvider) {
    $stateProvider.
		state('payu',{
			url: '/',
			templateUrl: './views/home.html'
		}).
		state('payu.new-client',{
			url: 'new-client',
			templateUrl: './views/new-client.html',
			controller: 'NewClientController'
		}).
		state('payu.clients',{
			url: 'clients',
			templateUrl: './views/clients.html',
			controller: 'ClientsController'
		}).
		state('payu.client-detail',{
			url: 'clients/:clientId',
			templateUrl: './views/client-detail.html',
			controller: 'ClientDetailController'
		}).
		state('payu.account-detail',{
			url: 'accounts/:accountId',
			templateUrl: './views/account-detail.html',
			controller: 'AccountDetailController'
		}).
		state('payu.date-range-report',{
			url: 'date-range-report',
			templateUrl: './views/date-range-report.html',
			controller: 'DateRangeReportController'
		});
});