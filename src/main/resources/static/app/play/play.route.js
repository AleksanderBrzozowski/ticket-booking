(function () {
    'use strict';
    angular.module('play')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider
            .state('index.play', {
                url: '/plays',
                templateUrl: 'app/play/main.html',
                controller: 'PlayController as vm'
            });
    }
})();