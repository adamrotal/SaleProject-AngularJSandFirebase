var app = angular.module('chatApp', []);

app.controller('chatCtrl', function($scope){
  $scope.users = ["deapamungkas", "adamrotal", "fajar", "dandu"];
});

function auto_grow(element) {
    element.style.height = "34px";
    element.style.height = (element.scrollHeight)+"px";
}
