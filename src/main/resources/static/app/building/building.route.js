(function () {
    'use strict';
    angular.module('building')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider
            .state('index.building', {
                url: '/building',
                template: '<div ui-view=""></div>',
                abstract: true
            })
            .state('index.building.cinemas', {
                url: '/cinemas',
                templateUrl: 'app/building/main.html',
                controller: 'BuildingController as vm',
                resolve: {
                    buildingsJson: function(BuildingService) {
                        return BuildingService.getBuildings('CINEMA')
                    }
                }
            })
            .state('index.building.theatres', {
                url: '/theatres',
                templateUrl: 'app/building/main.html',
                controller: 'BuildingController as vm',
                resolve: {
                    buildingsJson: function(BuildingService) {
                        return BuildingService.getBuildings('THEATRE')
                    }
                }
            });
    }
})();