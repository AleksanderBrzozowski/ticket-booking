(function () {
    'use strict';

    angular.module('building')
        .controller('BuildingController', BuildingController);

    BuildingController.$inject = ['BuildingService', 'type', '$uibModal'];
    function BuildingController(BuildingService, type, $uibModal) {
        var vm = this;

        vm.loading = true;

        var buildingsJson = null;

        vm.chosenCityIndex = -1;
        vm.cities = [];

        BuildingService.getBuildings(type)
            .then(function (response) {
                buildingsJson = response;

                vm.cities = response.map(function (building) {
                    return building.city;
                });

                vm.loading = false;
            });

        vm.chosenBuildingIndex = -1;
        vm.buildings = [];

        vm.plays = [];
        vm.chosenPlayIndex = -1;
        vm.play = null;
        vm.loadingPlays = false;

        vm.chooseCity = chooseCity;
        vm.chooseBuilding = chooseBuilding;
        vm.choosePlay = choosePlay;

        function chooseCity(chosenCityIndex) {
            vm.chosenCityIndex = chosenCityIndex;
            vm.chosenBuildingIndex = -1;
            vm.chosenPlayIndex = -1;

            vm.buildings = buildingsJson[chosenCityIndex].buildings;
        }

        function chooseBuilding(chosenBuildingIndex) {
            vm.chosenBuildingIndex = chosenBuildingIndex;
            vm.chosenPlayIndex = -1;
            vm.plays = [];
            vm.loadingPlays = true;
            BuildingService.getPlays(vm.buildings[chosenBuildingIndex].id)
                .then(function (response) {
                    vm.plays = response;
                    vm.loadingPlays = false;
                })
        }

        function choosePlay(play) {
            var city = vm.cities[vm.chosenCityIndex];
            var building = vm.buildings[vm.chosenBuildingIndex];

            $uibModal.open({
                templateUrl: 'app/order/main.html',
                controller: 'OrderController',
                size: 'lg',
                controllerAs: 'vm',
                resolve: {
                    'play': function() {return play},
                    'buildingId': function() {return building.id},
                    'city': function() {return city}
                }
            }).result.catch(function(res){
                if (!(res === 'cancel' || res === 'backdrop click')) {
                    throw res;
                }
            })
        }
    }
})();