var app = angular.module('chatApp', []);

app.controller('chatCtrl', ['$scope','$sce','$http', function ($scope, $sce, $http) {
  $scope.selfUsername;
  $scope.chatData = '';
  $scope.chatShow = false;
  $scope.username = '';
  $scope.usernameClicked = '';
        
  $scope.send = function() {
      if ($scope.inputChat != null) {
        var url = "http://localhost:3000/sendMessage";
        var params = "usernamePengirim="+$scope.selfUsername+"&usernamePenerima="+$scope.username+"&pesan="+$scope.inputChat;
        
        $http.get(url+"?"+params).success(function(res) {
            console.log(res)
        })
        
          console.log("ada2");
          $scope.chatData = $sce.trustAsHtml($("#chatData").html());
          $scope.chatData = $sce.trustAsHtml($scope.chatData + '<p class="chat self" >' + $scope.inputChat + '</p><br>');
          console.log($scope.chatData);
      }
  }
  
  $scope.clickChat = function(user) {
      
      if ($scope.username == user || $scope.username == '') {
          $scope.chatShow = !$scope.chatShow;
          $scope.username = user;
          $scope.chatData = '';
      } else if (!$scope.chatShow && $scope.username != user){
          $scope.chatShow = !$scope.chatShow;
          $scope.username = user;
          $scope.chatData = '';
      }
  }
  
}]);
