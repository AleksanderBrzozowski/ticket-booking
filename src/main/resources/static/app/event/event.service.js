(function () {
    'use strict';

    angular.module('event')
        .service('EventService', EventService);

    EventService.$inject = ['Restangular'];
    function EventService(Restangular) {
        return {
            getEventsByPlayIdAndBuilding: getEventsByBuildingAndPlay
        };

        function getEventsByBuildingAndPlay(buildingId, playId){
            return eventsPath().getList({buildingId: buildingId, playId: playId})
        }

        function eventsPath(id) {
            return angular.isUndefined(id) ? Restangular.all('events') : Restangular.one('events', id);

        }
    }
})();