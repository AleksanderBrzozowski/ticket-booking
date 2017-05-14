(function () {
    'use strict';

    angular.module('core')
        .config(translation);

    translation.$inject = ['$translateProvider'];
    function translation($translateProvider) {
        $translateProvider.translations('pl', {
            NAVBAR: {
                SEARCH: 'Szukaj spektaklu',
                CINEMAS: 'Kina',
                THEATRES: 'Teatry',
                HOME: 'Strona główna',
                TOGGLE_NAVIGATION: 'Rozwiń pasek'
            },
            HOME: {
                TITLE: 'Aplikacja do kupowania biletów',
                DESCRIPTION: 'Znajdź teatr lub kino, wybierz spektakl i zamów bilety już dziś!'
            },
            BUILDING: {
                CHOOSE_BUILDING: 'Wybierz budynek',
                CHOOSE_CITY: 'Wybierz miasto'
            }
        });
    }
})();