angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {

    $scope.loadWithFilter = function() {
        let suffix = "";
        let operands = 0;
        if (($scope.productFilter!=null && $scope.productFilter!=="")) {
            suffix = "/?";
            operands = operands + 1;
        }
        if (($scope.priceMinFilter!=null && $scope.priceMinFilter!=="")) {
            suffix = "/?";
            operands = operands + 1;
        }
        if (($scope.priceMaxFilter!=null&& $scope.priceMaxFilter!=="")) {
            suffix = "/?";
            operands = operands + 1;
        }
        if ($scope.productFilter!=null && $scope.productFilter!=="") {
            suffix = suffix + "productFilter=" + $scope.productFilter;
            if (operands > 1) {
                suffix = suffix + '\&';
                operands = operands - 1;
            }
        }
        if ($scope.priceMinFilter!=null && $scope.priceMinFilter!=="") {
            suffix = suffix + "priceMinFilter=" + $scope.priceMinFilter;
            if (operands > 1) {
                suffix = suffix + '\&';
                operands = operands - 1;
            }
        }
        if ($scope.priceMaxFilter!=null && $scope.priceMaxFilter!=="") {
            suffix = suffix + "priceMaxFilter=" + $scope.priceMaxFilter;
        }
        $http.get('http://localhost:5555/core/api/v1/products' + suffix)
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/'
            + $localStorage.novemberMarketGuestCartId
            + '/add/' + id)
            .then(function (response) {
            });
    }

    $scope.loadWithFilter(null);
});


