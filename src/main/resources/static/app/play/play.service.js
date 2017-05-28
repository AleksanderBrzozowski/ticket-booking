(function () {
    'use strict';

    angular.module('play')
        .service('PlayService', PlayService);

    PlayService.$inject = ['Restangular'];
    function PlayService(Restangular) {
        return {
            getPlays: getPlays
        };

        function getPlays(query) {
            return playsPath().getList({query: query})
        }

        function playsPath() {
            return Restangular.all('plays');
        }
    }
})();