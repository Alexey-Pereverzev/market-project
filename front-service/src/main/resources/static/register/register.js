angular.module('market').controller('registerController', function ($scope, $http, $localStorage) {

    $scope.registerMe = function () {
        if ($scope.user.username!=null && $scope.user.username!=="" && $scope.user.password!=null && $scope.user.password!=="" &&
            $scope.user.confirmPassword!=null && $scope.user.confirmPassword!=="" && $scope.user.email!=null && $scope.user.email!=="") {
            $http.post('http://localhost:5555/auth/reg/register', $scope.user)
                .then(function (response) {
                    alert(response.data.value);
                });
        }
    }


});