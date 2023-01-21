angular.module('market').controller('ordersController', function ($scope, $http, $rootScope) {
    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.orders = response.data;
            });
    };

    $scope.applyOrderData = function (o) {
        $rootScope.order = o;
    }

    $scope.loadOrders();
});