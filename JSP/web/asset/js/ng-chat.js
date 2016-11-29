var app = angular.module('chatApp', []);

app.controller('chatCtrl', ['$scope','$sce', function ($scope, $sce) {
  console.log("ada");
  $scope.chatData = '';
  $scope.send = function() {
      if ($scope.inputChat != null) {
          console.log("ada2");
          $scope.chatData = $sce.trustAsHtml($scope.chatData + '<p class="chat self">' + $scope.inputChat + '</p><br>');
          console.log($scope.chatData);
      }
  }
  
  $scope.sendF = function() {
      if ($scope.inputChatF != null) {
          console.log("ada2");
          $scope.chatData = $sce.trustAsHtml($scope.chatData + '<p class="chat friend">' + $scope.inputChatF + '</p><br>');
          console.log($scope.chatData);
      }
  }
}]);

function auto_grow(element) {
    element.style.height = "34px";
    element.style.height = (element.scrollHeight)+"px";
}
