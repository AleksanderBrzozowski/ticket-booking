(function () {
    'use strict';

    angular.module('order')
        .service('OrderService', OrderService);

    OrderService.$inject = ['Restangular'];
    function OrderService(Restangular) {
        return {
            getDiscounts: getDiscounts,
            reserve: reserve
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

        function ordersPath(id) {
            return angular.isUndefined(id) ? Restangular.all('orders') : Restangular.one('orders', id);
        }
    }
})();