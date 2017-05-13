(function() {
    'use strict';
    angular.module('building')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider
            .state('building', {
                url: '/building/:type',
                templateUrl: 'app/building/main.html',
                controller: 'BuildingController as vm',
                resolve: {
                    buildingsJson: function($stateParams, BuildingService) {
                        return BuildingService.getBuildings($stateParams.type)
                    }
                }
            });
    }
})();