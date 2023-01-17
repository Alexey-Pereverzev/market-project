angular.module('market').controller('storeController', function ($scope, $http, $localStorage, $rootScope) {

    $scope.productsPage = [];

    $scope.loadWithFilter = function (page = 1) {
        $http({
            url: 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params: {
                p: page,
                page_size: 5,
                productFilter: $scope.filter ? $scope.filter.productFilter : null,
                priceMinFilter: $scope.filter ? $scope.filter.priceMinFilter : null,
                priceMaxFilter: $scope.filter ? $scope.filter.priceMaxFilter : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);
        });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
            + '/add/' + id)
            .then(function (response) {
            });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.isPageFirst = function () {
        return ($scope.productsPage.number <= 0);
    };

    $scope.isPageLast = function () {
        return ($scope.productsPage.number > $scope.productsPage.totalPages-2);
    };

    $scope.createNewProduct = function () {
        $http.post('http://localhost:5555/core/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.loadWithFilter();
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + id)
            .then(function (response) {
                $scope.loadWithFilter();
            });
    }

    $scope.applyProductData = function (p) {
        $rootScope.product = p;
    }


    $scope.loadWithFilter(null);
});


