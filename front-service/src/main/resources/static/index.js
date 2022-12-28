angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.novemberMarketUser) {
        try {
            let jwt = $localStorage.novemberMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.novemberMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.novemberMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.novemberMarketUser.token;
        }
    }

    // $scope.loadProducts = function () {
    //     $http.get('http://localhost:5555/core/api/v1/products')
    //         .then(function (response) {
    //             $scope.products = response.data;
    //         });
    // };

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

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.clearCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.decrease = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/decrease/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    // $scope.deleteProduct = function (id) {
    //     $http.delete('http://localhost:5555/core/api/v1/products/' + id)
    //         .then(function (response) {
    //             $scope.loadProducts();
    //         });
    // }
    //
    // $scope.createNewProduct = function () {
    //     // console.log($scope.newProduct);
    //     $http.post('http://localhost:5555/core/api/v1/products', $scope.newProduct)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.loadProducts();
    //         });
    // }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.novemberMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.novemberMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        return !!$localStorage.novemberMarketUser;
    };

    $scope.getCurrentUserInfo = function () {
        $http.get('http://localhost:5555/auth/about_me')
            .then(function (response) {
                alert(response.data.value);
            });
    };

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders', $scope.cart)
            .then(function (response) {
                $scope.clearCart();
                }
            );
    }

    $scope.loadWithFilter(null);
    $scope.loadCart();
});