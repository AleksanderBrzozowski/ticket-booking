(function () {
    'use strict';

    angular.module('order')
        .controller('OrderController', OrderController);

    OrderController.$inject = ['play', 'buildingId', 'city', '$uibModalInstance', 'EventService', 'PlayService', 'RoomService', 'OrderService'];
    function OrderController(play, buildingId, city, $uibModalInstance, EventService, PlayService, RoomService, OrderService) {
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

        var seats = [];
        vm.possibleSeats = [];

        vm.discounts = [];

        vm.loading = true;
        vm.loadingEvents = false;
        vm.loadingDiscounts = false;
        vm.loadingSeats = false;

        vm.firstName = null;
        vm.lastName = null;
        vm.telephone = null;

        vm.rows = null;

        vm.submitting=false;

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
        vm.chooseEvent = chooseEvent;
        vm.addRow = addRow;
        vm.removeRow = removeRow;
        vm.getPossibleSeats = getPossibleSeats;
        vm.getPrice = getPrice;
        vm.getPriceSum = getPriceSum;
        vm.submit = submit;

        function getPriceSum() {
            return _.reduce(vm.rows, function(sum, object, index){
                return sum + getPrice(index)
            }, 0)
        }

        function getPossibleSeats(index) {
            var possibleSeats = _.clone(seats);
            var chosenSeats = vm.rows.map(function (object) {
                return object.chosen;
            });

            possibleSeats = _.filter(possibleSeats, function (possibleSeat) {
                var indexOf = chosenSeats.indexOf(possibleSeat);
                return indexOf === -1 || indexOf === index;
            });

            return possibleSeats;
        }

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

        function chooseEvent() {
            vm.loadingSeats = true;

            RoomService.getSeats(vm.chosenEvent.room.id, vm.chosenEvent.id)
                .then(function (response) {
                    seats = response;
                    vm.possibleSeats = _.clone(seats);
                    vm.rows = [];
                    addRow();
                    vm.loadingSeats = false;
                });

            if (vm.discounts.length < 1) {
                vm.loadingDiscounts = true;
                OrderService.getDiscounts().then(function (response) {
                    vm.discounts = response;
                    vm.loadingDiscounts = false;
                })
            }
        }

        function addRow() {
            vm.rows.push({
                chosen: null,
                chosenDiscount: null
            })
        }

        function removeRow() {
            vm.rows.pop();
        }

        function close() {
            $uibModalInstance.dismiss('close');
        }

        function getPrice(index) {
            var chosenDiscount = vm.rows[index].chosenDiscount;
            if (angular.isDefined(chosenDiscount) && chosenDiscount !== null) {
                return vm.chosenEvent.price - (vm.chosenEvent.price * chosenDiscount.value * 0.01);
            } else {
                return vm.chosenEvent.price;
            }
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

        function submit() {
            var tickets = vm.rows.map(function (row) {
                return {
                    seatId: row.chosen.id,
                    discountId: row.chosenDiscount ? row.chosenDiscount.id : null
                }
            });
            OrderService.reserve(vm.firstName, vm.lastName, vm.telephone, vm.chosenEvent.id, tickets);
            vm.submitting = true;
        }
    }
})
();