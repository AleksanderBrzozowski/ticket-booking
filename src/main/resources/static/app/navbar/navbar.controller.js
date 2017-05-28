(function () {
    'use strict';

    angular.module('navbar')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', '$scope', '$stateParams'];
    function NavbarController($state, $scope, $stateParams) {
        var vm = this;

        $scope.navbarCollapsed = true;

        vm.bars = [
            createBar('NAVBAR.HOME', 'index.home'),
            createBar('NAVBAR.THEATRES', 'index.building.theatres'),
            createBar('NAVBAR.CINEMAS', 'index.building.cinemas')
        ];

        vm.playQuery = $stateParams.query;

        vm.menuBarClick = menuBarClick;
        vm.playQueryAction = playQueryAction;

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

        function playQueryAction() {
            $state.go('.', {query: vm.playQuery}, {notify: false})
                .then(function () {
                    $state.go('index.play', null, {reload: true})
                });
        }

    }
})();