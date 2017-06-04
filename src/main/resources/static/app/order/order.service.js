(function () {
    'use strict';

    angular.module('order')
        .service('OrderService', OrderService);

    OrderService.$inject = ['Restangular'];
    function OrderService(Restangular) {
        return {
            getDiscounts: getDiscounts
        };

        function getDiscounts(){
            return ordersPath().all('discounts').getList();
        }

        function ordersPath(id) {
            return angular.isUndefined(id) ? Restangular.all('orders') : Restangular.one('orders', id);

        }
    }
})();