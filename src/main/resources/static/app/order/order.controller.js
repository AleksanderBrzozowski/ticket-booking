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
        vm.chosenEvent = null;

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
        vm.bookForm = bookForm;
        vm.buyForm = buyForm;

        function chooseCity(cityIndex) {
            if (cityIndex === vm.chosenCityIndex) {
                return;
            }
            vm.chosenBuildingIndex = -1;
            vm.chosenEvent = null;

            vm.chosenCityIndex = cityIndex;

            vm.buildings = eventsJson[cityIndex].buildings.map(function(object) {
                return object.building;
            });
        }

        function chooseBuilding(buildingIndex) {
            if (buildingIndex === vm.chosenBuildingIndex) {
                return;
            }
            vm.chosenBuildingIndex = buildingIndex;
            vm.chosenEvent = null;

            vm.events = eventsJson[vm.chosenCityIndex].buildings[buildingIndex].events
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