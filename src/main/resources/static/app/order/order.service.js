(function () {
    'use strict';

    angular.module('order')
        .service('OrderService', OrderService);

    OrderService.$inject = ['Restangular'];
    function OrderService(Restangular) {
        return {
            getDiscounts: getDiscounts,
            reserve: reserve,
            buy: buy
        };

        function getDiscounts() {
            return ordersPath().all('discounts').getList();
        }

        function reserve(firstName, lastName, telephone, eventId, tickets) {
            return ordersPath().all('reserve').customPOST({
                firstName: firstName,
                lastName: lastName,
                eventId: eventId,
                tickets: tickets,
                telephone: telephone
            })
        }

        function buy(eventId, tickets) {
            return ordersPath().all('buy').customPOST({
                eventId: eventId,
                tickets: tickets
            })
        }

        function ordersPath(id) {
            return angular.isUndefined(id) ? Restangular.all('orders') : Restangular.one('orders', id);
        }
    }
})();