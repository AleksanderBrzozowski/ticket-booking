(function () {
    'use strict';

    angular.module('order')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['play', 'buildingId', 'cityId', '$uibModalInstance', 'EventService'];
    function OrderController(play, buildingId, cityId, $uibModalInstance, EventService) {
        var vm = this;

        var eventsJson = null;


        vm.play = play;

        vm.cities = [];
        vm.chosenCityIndex = -1;

        vm.buildings = [];
        vm.chosenBuildingIndex = -1;

        vm.events = [];
        vm.chosenEventIndex = -1;

        vm.close = close;
        vm.chooseBuilding = chooseBuilding;
        vm.chooseCity = chooseCity;
        vm.chooseEvent = chooseEvent;

        vm.loading = true;
        EventService.getEvents(play.id)
            .then(function (response) {
                eventsJson = response;
                vm.cities = response.map(function (object) {
                    return object.city;
                });
                vm.loading = false;
            });

        function chooseCity(cityIndex) {
            vm.chosenBuildingIndex = -1;
            vm.chosenEventIndex = -1;

            vm.chosenCityIndex = cityIndex;

            vm.buildings = eventsJson[cityIndex].buildings.map(function(object) {
                return object.building;
            });
        }

        function chooseBuilding(buildingIndex) {
            vm.chosenBuildingIndex = buildingIndex;
            vm.chosenEventIndex = -1;

            vm.events = eventsJson[vm.chosenCityIndex].buildings[buildingIndex].events
        }

        function chooseEvent(eventIndex) {
            vm.chosenEventIndex = eventIndex;
        }

        function close() {
            $uibModalInstance.close();
        }
    }
})();