(function () {
    'use strict';

    angular.module('navbar')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state'];
    function NavbarController($state) {
        var vm = this;

        vm.bars = [
            createBar('NAVBAR.HOME', 'index.home'),
            createBar('NAVBAR.THEATRES', 'index.building.theatres'),
            createBar('NAVBAR.CINEMAS', 'index.building.cinemas')
        ];

        vm.menuBarClick = menuBarClick;

        function createBar(name, state) {

            return {
                'name': name,
                'state': state,
                'active': $state.current.name === state
            }
        }

        function menuBarClick(bar) {
            angular.forEach(vm.bars, function (bar) {
                bar.active = false;
            });

            bar.active = true;
            $state.go(bar.state);
        }

    }
})();