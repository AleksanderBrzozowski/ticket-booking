(function () {
    'use strict';

    angular.module('building')
        .service('BuildingService', BuildingService);

    BuildingService.$inject = ['Restangular'];
    function BuildingService(Restangular) {
        return {
            getBuildings: getBuildings,
            getPlays: getPlays
        };

        function getBuildings(type) {
            return buildingsPath().get('city-sorted', {type: type})
        }

        function getPlays(buildingId) {
            return buildingsPath(buildingId).getList('plays')
        }

        function buildingsPath(id) {
            return angular.isUndefined(id) ? Restangular.all('buildings') : Restangular.one('buildings', id);
        }
    }
})();