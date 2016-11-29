var app = angular.module('chatApp', []);

app.controller('chatCtrl', ['$scope','$sce', function ($scope, $sce) {
  
  $scope.chatData = '';
  $scope.chatShow = false;
  $scope.username = '';
  $scope.usernameClicked = '';
        
  $scope.send = function() {
      if ($scope.inputChat != null) {
          console.log("ada2");
          $scope.chatData = $sce.trustAsHtml($scope.chatData + '<p class="chat self" >' + $scope.inputChat + '</p><br>');
          console.log($scope.chatData);
      }
  }
  
  $scope.clickChat = function(user) {
      
      if ($scope.username == user || $scope.username == '') {
          $scope.chatShow = !$scope.chatShow;
          $scope.username = user;
      } else if (!$scope.chatShow && $scope.username != user){
          $scope.chatShow = !$scope.chatShow;
          $scope.username = user;
      }
  }
  
}]);
