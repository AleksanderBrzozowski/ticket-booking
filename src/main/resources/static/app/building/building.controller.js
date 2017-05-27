(function () {
    'use strict';

    angular.module('building')
        .controller('BuildingController', BuildingController);

    BuildingController.$inject = ['buildingsJson', 'BuildingService'];
    function BuildingController(buildingsJson, BuildingService) {
        var vm = this;

        vm.chosenCityIndex = -1;
        vm.cities = buildingsJson.map(function (building) {
            return building.city;
        });

        vm.chosenBuildingIndex = -1;
        vm.buildings = [];

        vm.plays = [];
        vm.chosenPlayIndex = -1;

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
            BuildingService.getPlays(vm.buildings[chosenBuildingIndex].id)
                .then(function (response) {
                    vm.plays = response
                })
        }

        function choosePlay(chosenPlayIndex) {
            vm.chosenPlayIndex = chosenPlayIndex
        }

    }
})();