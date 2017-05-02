(function () {
    'use strict';

    angular.module('film')
        .controller('FilmController', FilmController);

    FilmController.$inject = [];
    function FilmController() {
        var vm = this;
    }
})();