var app = angular.module('chatApp', []);

app.controller('chatCtrl', ['$scope','$sce', function ($scope, $sce) {
  
  $scope.chatData = '';
  $scope.chatShow = false;
  $scope.username = '';
        
  $scope.send = function() {
      if ($scope.inputChat != null) {
          console.log("ada2");
          $scope.chatData = $sce.trustAsHtml($scope.chatData + '<p class="chat self" >' + $scope.inputChat + '</p><br>');
          console.log($scope.chatData);
      }
  }
  
  $scope.addusername = function(){
    $scope.username.push($scope.usrname);
  }
  
  $scope.clickChat = function(user) {
      $scope.chatShow = !$scope.chatShow;
      $scope.username = user;
  }
  
}]);

function auto_grow(element) {
    element.style.height = "34px";
    element.style.height = (element.scrollHeight)+"px";
}
