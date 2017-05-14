(function () {
    'use strict';

    angular.module('index')
        .config(routerConfig);

    routerConfig.$inject = ['$stateProvider'];
    function routerConfig($stateProvider) {
        $stateProvider
            .state('index', {
                views: {
                    'navbar': {
                        templateUrl: 'app/navbar/main.html',
                        controller: 'NavbarController as vm'
                    },
                    '': {
                        template: '<div class="ui-view"></div>'
                    }
                },
                abstract: true
            });
    }
})();
