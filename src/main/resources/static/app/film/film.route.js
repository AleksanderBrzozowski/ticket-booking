(function () {
    'use strict';

    angular.module('film')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider.state('film', {
            url: '/film',
            templateUrl: 'app/film/main.html',
            controller: 'FilmController as vm'
        })
    }
})();