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
                CHOOSE_CITY: 'Wybierz miasto',
                CHOOSE_PLAY: 'Wybierz spektakl',
                CHOSEN_PLAY: 'Wybrany film'
            },
            PLAY: {
                NO_DESCRIPTION: 'Brak opisu',
                ORDER: 'Zamów',
                BOOK: 'Rezerwuj',
                NO_PLAYS: 'Brak spektakli'
            },
            CHOOSE: 'Wybierz',
            EVENT: {
                CHOOSE: 'Wybierz wydarzenie'
            },
            DATE: 'Data'
        });
    }
})();