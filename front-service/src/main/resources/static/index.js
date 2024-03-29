(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'registerController'
            })
            .when('/edit', {
                templateUrl: 'edit/edit.html',
                controller: 'editController'
            })
            .when('/order_info', {
                templateUrl: 'order_info/order_info.html',
                controller: 'orderInfoController'
            })
            .when('/notifications', {
                templateUrl: 'notifications/notifications.html',
                controller: 'notificationController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
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
        if (!$localStorage.novemberMarketGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate_id')
                .then(function (response) {
                    $localStorage.novemberMarketGuestCartId = response.data.value;
                    alert($localStorage.novemberMarketGuestCartId);
                });
        }
    }
})();

angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $scope.userRoles=[];

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.novemberMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.userRoles = response.data.roles;

                    alert($scope.user.username + ' is authorized');

                    $scope.mergeCarts();

                    $rootScope.loadNotifications();

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.novemberMarketUser;
        $http.defaults.headers.common.Authorization = '';
        $rootScope.clearCart();
    };

    $scope.mergeCarts = function() {
        $http.get('http://localhost:5555/cart/api/v1/cart/merge/'
            + $localStorage.novemberMarketGuestCartId)
            .then(function (response) {
            });
    }

    $rootScope.isUserLoggedIn = function () {
        return !!$localStorage.novemberMarketUser;
    };

    $scope.isUserHasUserRole = function () {
        if ($scope.userRoles !== null) {
            return ($scope.userRoles.indexOf("ROLE_USER") !== -1);
        } else {
            return false;
        }
    }

    $scope.isUserHasAdminRole = function () {
        if ($scope.userRoles !== null) {
            return ($scope.userRoles.indexOf("ROLE_ADMIN") !== -1);
        } else {
            return false;
        }
    }

    $scope.getCurrentUserInfo = function () {
        $http.get('http://localhost:5555/auth/about_me')
            .then(function (response) {
                alert(response.data.value);
            });
    };


});