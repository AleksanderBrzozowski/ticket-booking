(function () {
    'use strict';

    angular.module('navbar')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state'];
    function NavbarController($state) {
        var vm = this;

        vm.goTheatres = goTheatres;
        vm.goCinemas = goCinemas;
        vm.goHome = goHome;

        function goTheatres() {
            goToBuilding('THEATRE')
        }

        function goCinemas() {
            goToBuilding('CINEMA')
        }

        function goHome() {
            $state.go('')
        }

        function goToBuilding(type) {
            $state.go('building', {type: type})
        }
    }
})();