(function () {
    'use strict';

    angular.module('order')
        .controller('OrderSummaryController', OrderSummaryController);

    OrderSummaryController.$inject = ['$uibModalInstance', 'orderSummary', 'type'];
    function OrderSummaryController($uibModalInstance, orderSummary, type) {
        var vm = this;

        vm.type = type;
        vm.summary = orderSummary;

        vm.close = close;

        function close() {
            $uibModalInstance.dismiss('close');
        }

    }
})
();