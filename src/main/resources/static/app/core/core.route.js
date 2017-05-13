(function() {
    'use strict';

    angular.module('core')
        .config(routeConfig);

    routeConfig.$inject = ['$urlRouterProvider'];
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
    }
})();