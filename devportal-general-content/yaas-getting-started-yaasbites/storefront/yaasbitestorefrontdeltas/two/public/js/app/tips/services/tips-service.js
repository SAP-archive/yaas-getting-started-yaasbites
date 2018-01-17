// ADJUSTED_AS_NEEDED : Added this file which specifies the functions our Tips Module will offer
'use strict';  

angular.module('ds.tips')
    .factory('TipsSvc', ['TipsREST',

        function(TipsREST){
            return {
                // A comma separated list of functions (currenty just one)
                getRandomTip: function () {
                	// Restangular call to our Tips Endpoint
                    return TipsREST.Tips.all('tips').getList();
                }
            };
        }]);