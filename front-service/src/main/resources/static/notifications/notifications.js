angular.module('market').controller('notificationController', function ($rootScope, $scope, $http) {

    $rootScope.loadNotifications = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/notifications',
            method: 'GET',
        }).then(function (response) {
            $rootScope.nots = response.data;
        });
    };


    $rootScope.lastNot = $rootScope.nots[0].id;
});