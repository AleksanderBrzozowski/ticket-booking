(function () {
    'use strict';

    angular.module('play')
        .service('PlayService', PlayService);

    PlayService.$inject = ['Restangular'];
    function PlayService(Restangular) {
        return {
            getPlays: getPlays,
            getBuildings: getBuildings
        };

        function getPlays(query) {
            return playsPath().getList({query: query})
        }

        function getBuildings(id) {
            return playsPath(id).all('buildings').getList();
        }

        function playsPath(id) {
            return angular.isUndefined(id) ? Restangular.all('plays') : Restangular.one('plays', id);
        }
    }
})();