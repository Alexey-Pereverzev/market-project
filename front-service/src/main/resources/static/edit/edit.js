angular.module('market').controller('editController', function ($scope, $http, $localStorage, $rootScope) {

    $scope.product = $rootScope.product;

    $scope.editProduct = function () {
        $http.post('http://localhost:5555/core/api/v1/products/update', $scope.product)
            .then(function (response) {
        }).then(function (response) {
            alert('Продукт ' + $scope.product.title + ' успешно изменен');
            $scope.product = null;
            $scope.loadWithFilter();
        });
    }

});