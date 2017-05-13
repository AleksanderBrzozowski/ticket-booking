(function () {
    'use strict';

    angular.module('building')
        .service('BuildingService', BuildingService);

    BuildingService.$inject = ['Restangular'];
    function BuildingService(Restangular) {
        return {
            getBuildings: getBuildings
        };

        function getBuildings(type) {
            return buildingsPath().get('city-sorted', {type: type})
        }

        function buildingsPath() {
            return Restangular.all('buildings')
        }
    }
})();