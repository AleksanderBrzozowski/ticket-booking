(function () {
    'use strict';

    angular.module('play')
        .controller('PlayController', PlayController);

    PlayController.$inject = ['PlayService', '$stateParams', '$uibModal'];
    function PlayController(PlayService, $stateParams, $uibModal) {
        var vm = this;

        vm.loading = true;
        vm.plays = [];

        vm.choosePlay = choosePlay;

        PlayService.getPlays($stateParams.query)
            .then(function (response) {
                vm.plays = response;
            })
            .finally(function () {
                vm.loading = false;
            });

        function choosePlay(play) {
            $uibModal.open({
                templateUrl: 'app/order/main.html',
                controller: 'OrderController',
                size: 'lg',
                controllerAs: 'vm',
                resolve: {
                    'play': play,
                    'buildingId': null,
                    'city': null
                }
            })
        }
    }
})();