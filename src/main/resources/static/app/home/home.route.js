(function () {
    'use strict';

    angular.module('home')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider.state('home', {
            url: '/home',
            templateUrl: 'app/home/main.html'
        })
    }
})();