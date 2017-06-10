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
                NO_PLAYS: 'Brak spektakli'
            },
            CHOOSE: 'Wybierz',
            EVENT: {
                CHOOSE: 'Wybierz wydarzenie',
                PRICE: 'Cena',
                CURRENCY: 'PLN'
            },
            DATE: 'Data',
            ORDER: {
                BUY: 'Kupuję',
                BOOK: 'Rezerwuję',
                ADD_ROW: 'Dodaj wiersz',
                REMOVE_ROW: 'Usuń wiersz',
                CHOOSE_DISCOUNT: 'Wybierz zniżkę',
                PRICE_SUM: 'Suma',
                ORDER: 'Zamów',
                CHOOSE_MINIMUM_ONE_SEATING: 'Wybierz przynajmniej jedno miejsce'
            },
            SEAT: {
                CHOOSE_SEATS: 'Wybierz miejsca',
                ROW: 'rząd',
                NUMBER: 'numer'
            },
            FIELD_REQUIRED: 'Pole jest wymagane'
        });
    }
})();