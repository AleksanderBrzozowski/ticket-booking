(function () {
    'use strict';

    angular.module('room')
        .service('RoomService', RoomService);

    RoomService.$inject = ['Restangular'];
    function RoomService(Restangular) {
        return {
            getSeats: getSeats
        };

        function getSeats(roomId, eventId){
            return seatsPath(roomId).all('seats').getList({eventId: eventId})
        }

        function seatsPath(id) {
            return angular.isUndefined(id) ? Restangular.all('rooms') : Restangular.one('rooms', id);

        }
    }
})();