(function () {
    'use strict';

    angular.module('building')
        .controller('BuildingController', BuildingController);

    BuildingController.$inject = ['buildingsJson'];
    function BuildingController(buildingsJson) {
        var vm = this;

        vm.chosenCityIndex = -1;
        vm.cities = buildingsJson.map(function (building) {
            return building.city;
        });

        vm.chosenBuildingIndex = -1;
        vm.buildings = [];

        vm.chooseCity = chooseCity;
        vm.chooseBuilding = chooseBuilding;

        function chooseCity(chosenCityIndex) {
            vm.chosenCityIndex = chosenCityIndex;
            vm.buildings = buildingsJson[chosenCityIndex].buildings;
        }

        function chooseBuilding(chosenBuildingIndex) {
            vm.chosenBuildingIndex = chosenBuildingIndex;
        }
    }
})();