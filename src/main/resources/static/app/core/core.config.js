(function() {
    'use strict';

    angular.module('core')
        .config(restangularConfig)
        .config(translateConfig);

    restangularConfig.$inject = ['RestangularProvider'];
    function restangularConfig(RestangularProvider) {
        RestangularProvider.setBaseUrl('/api');
    }

    translateConfig.$inject = ['$translateProvider'];
    function translateConfig($translateProvider) {
        $translateProvider.preferredLanguage('pl');
        $translateProvider.useSanitizeValueStrategy('escape');
    }
})();