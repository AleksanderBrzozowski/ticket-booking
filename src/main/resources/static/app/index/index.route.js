(function () {
    'use strict';

    angular.module('index')
        .config(routerConfig);

    routerConfig.$inject = ['$stateProvider'];
    function routerConfig($stateProvider) {
        $stateProvider
            .state('index', {
                url: '?query',
                views: {
                    'navbar': {
                        templateUrl: 'app/navbar/main.html',
                        controller: 'NavbarController as vm'
                    },
                    '': {
                        template: '<div class="container"><div class="ui-view"/></div>'
                    }
                },
                abstract: true
            });
    }
})();
