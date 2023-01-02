angular.module('market').controller('cartController', function ($rootScope, $scope, $http, $localStorage) {
    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
        )
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.createOrder = function () {
        if ($scope.phoneNumber!=null && $scope.phoneNumber!=="" && $scope.address!=null && $scope.address!=="") {
            $http.post('http://localhost:5555/core/api/v1/orders' +
                '?phoneNumber=' + $scope.phoneNumber +
                '\&' + 'address=' + $scope.address)
                .then(function (response) {
                    $rootScope.clearCart();
                });
        }
    }

    $scope.increase = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
            + '/add/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.decrease = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
            + '/decrease/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $rootScope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
            + '/clear/')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.guestCreateOrder = function () {
        alert('Для оформления заказа необходимо войти в учетную запись');
    }

    $scope.loadCart();
});