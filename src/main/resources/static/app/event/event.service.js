(function () {
    'use strict';

    angular.module('event')
        .service('EventService', EventService);

    EventService.$inject = ['Restangular'];
    function EventService(Restangular) {
        return {
            getEvents: getEvents
        };

        function getEvents(playId) {
            return eventsPath().all('building-group').getList({playId: playId})
        }

        function eventsPath(id) {
            return angular.isUndefined(id) ? Restangular.all('events') : Restangular.one('events', id);

        }
    }
})();