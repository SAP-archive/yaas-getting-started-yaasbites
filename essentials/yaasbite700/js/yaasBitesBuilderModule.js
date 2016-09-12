var yaasBitesBuilderModuleApp = angular.module('yaasBitesBuilderModuleApp', ["builder", "builder_editors"]);


yaasBitesBuilderModuleApp.config(function(RestangularProvider) {
	var SERVICES_API_PROXY="https://api.yaas.io/yaasgulps/yaasbites4/v1" ; // NEEDS_ADJUSTING
	RestangularProvider.setBaseUrl(SERVICES_API_PROXY);
});

yaasBitesBuilderModuleApp.controller('tipsCtrl', function($scope, $window, Restangular, currentAccountId, currentProjectId, notificationManager, linkManager) {
	$scope.accessToken = Builder.authManager().getAccessToken();		
	$scope.getTips = function() {
		$scope.restResponse = Restangular.all("tips").getList().$object;
	}
});
