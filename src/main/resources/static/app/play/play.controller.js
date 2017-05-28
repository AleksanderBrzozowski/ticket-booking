(function () {
    'use strict';

    angular.module('play')
        .controller('PlayController', PlayController);

    PlayController.$inject = ['PlayService', '$stateParams'];
    function PlayController(PlayService, $stateParams) {
        var vm = this;

        vm.loading = true;
        vm.plays = [];

        PlayService.getPlays($stateParams.query)
            .then(function (response) {
                vm.plays = response;
            })
            .finally(function () {
                vm.loading = false;
            });
    }
})();