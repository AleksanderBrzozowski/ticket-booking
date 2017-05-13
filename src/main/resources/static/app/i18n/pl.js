(function () {
    'use strict';

    angular.module('core')
        .config(translation);

    translation.$inject = ['$translateProvider'];
    function translation($translateProvider) {
        $translateProvider.translations('pl', {
            NAVBAR:{
                SEARCH: 'Szukaj filmu',
                CINEMAS: 'Kina',
                THEATRES: 'Teatry'
            }
        });
    }
})();