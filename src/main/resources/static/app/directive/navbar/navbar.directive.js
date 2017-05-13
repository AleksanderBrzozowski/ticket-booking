(function () {
    'use strict';

    angular.module('navbar')
        .directive('navbar', directive);

    function directive() {
        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'app/directive/navbar/main.html',
            controller: 'NavbarController as vm',
            resolve: {

            }
        }
    }
})();