angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8080/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.fillCart = function () {
        $http.get('http://localhost:8080/market/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
                // var cart = response.data;
                // console.log(cart);
            });
    };

    $scope.clearCart = function () {
        $http.get('http://localhost:8080/market/api/v1/cart/clear')
            .then(function (response) {
                $scope.fillCart();
                // var cart = response.data;
                // console.log(cart);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8080/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (id) {
        $http.get('http://localhost:8080/market/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }


    $scope.decrease = function (id) {
        $http.get('http://localhost:8080/market/api/v1/cart/decrease/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8080/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.fillTable();
    $scope.fillCart();
});