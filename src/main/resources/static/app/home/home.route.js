(function () {
    'use strict';

    angular.module('home')
        .config(routeConfig);

    routeConfig.$inject = ['$stateProvider'];
    function routeConfig($stateProvider) {
        $stateProvider.state('index.home', {
            url: '/home',
            templateUrl: 'app/home/main.html'
        })
    }
})();