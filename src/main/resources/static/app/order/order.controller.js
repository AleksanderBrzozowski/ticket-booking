(function () {
    'use strict';

    angular.module('order')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['play', 'buildingId', 'city', '$uibModalInstance', 'EventService'];
    function OrderController(play, buildingId, city, $uibModalInstance, EventService) {
        var vm = this;

        var eventsJson = null;

        vm.play = play;

        vm.cities = [];
        vm.chosenCityIndex = -1;

        vm.buildings = [];
        vm.chosenBuildingIndex = -1;

        vm.events = [];
        vm.chosenEventIndex = -1;

        vm.loading = true;
        EventService.getEvents(play.id)
            .then(function (response) {
                eventsJson = response;
                vm.cities = response.map(function (object) {
                    return object.city;
                });

                if (city !== null && buildingId !== null) {
                    var cityIndex = vm.cities.indexOf(city);
                    if (cityIndex !== -1) {
                        chooseCity(cityIndex);
                        var buildingIndex = _.findIndex(vm.buildings, ['id', buildingId]);
                        if(buildingIndex !== -1) {
                            chooseBuilding(buildingIndex)
                        }
                    }
                }

                vm.loading = false;
            });
        resetOrderAndBuyButtons();

        vm.close = close;

        vm.chooseBuilding = chooseBuilding;
        vm.chooseCity = chooseCity;
        vm.chooseEvent = chooseEvent;
        vm.bookForm = bookForm;
        vm.buyForm = buyForm;

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


        function bookForm() {
            resetOrderAndBuyButtons();
            vm.order.book = true;
        }

        function buyForm() {
            resetOrderAndBuyButtons();
            vm.order.buy = true;
        }

        function resetOrderAndBuyButtons() {
            vm.order = {
                buy: false,
                book: false
            };
        }
    }
})();