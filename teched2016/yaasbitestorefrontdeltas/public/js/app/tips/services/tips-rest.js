// ADJUSTED_AS_NEEDED : Added this file which defines a factory method to create a Restangular Object for calling the Tips Endpoint

'use strict';
angular.module('ds.tips')
   .factory('TipsREST', [  'Restangular', 'SiteConfigSvc', 

   	function(Restangular, siteConfig){
       return {
        	Tips: Restangular.withConfig(function (RestangularConfigurer) {
               RestangularConfigurer.setBaseUrl(
               		siteConfig.apis.tipsEndpoint.baseUrl  
               );
           })
       };

   }]);