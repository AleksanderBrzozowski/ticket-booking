(function () {
    'use strict';

    angular.module('order')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['play', 'buildingId', 'city', '$uibModalInstance', 'EventService', 'PlayService'];
    function OrderController(play, buildingId, city, $uibModalInstance, EventService, PlayService) {
        var vm = this;

        var buildingsJson = null;
        var eventsJson = null;

        vm.play = play;

        vm.cities = [];
        vm.chosenCityIndex = -1;

        vm.buildings = [];
        vm.chosenBuildingIndex = -1;

        vm.events = [];
        vm.chosenEvent = null;

        vm.loading = true;
        vm.loadingEvents = false;

        PlayService.getBuildings(play.id).then(function (response) {
            buildingsJson = response;

            vm.cities = response.map(function (building) {
                return building.city;
            });

            if (city !== null && buildingId !== null) {
                var cityIndex = vm.cities.indexOf(city);
                if (cityIndex !== -1) {
                    chooseCity(cityIndex);
                    var buildingIndex = _.findIndex(vm.buildings, ['id', buildingId]);
                    if (buildingIndex !== -1) {
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
        vm.bookForm = bookForm;
        vm.buyForm = buyForm;

        function chooseCity(cityIndex) {
            if (cityIndex === vm.chosenCityIndex) {
                return;
            }
            vm.chosenBuildingIndex = -1;
            vm.chosenEvent = null;

            vm.chosenCityIndex = cityIndex;

            vm.buildings = buildingsJson[cityIndex].buildings
        }

        function chooseBuilding(buildingIndex) {
            if (buildingIndex === vm.chosenBuildingIndex) {
                return;
            }
            vm.chosenBuildingIndex = buildingIndex;

            vm.loadingEvents = true;
            EventService.getEventsByPlayIdAndBuilding(vm.buildings[buildingIndex].id, play.id)
                .then(function (response) {
                    vm.events = response;
                    vm.loadingEvents = false;
                });
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
})
();