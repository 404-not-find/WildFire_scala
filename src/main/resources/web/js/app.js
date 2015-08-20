(function() {
  'use strict';

  var myApp = angular.module('knowledgeApp', 
		  [		'ui.bootstrap',
		   		'ngRoute'
		   
		   ]);

  myApp.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {
      $routeProvider.when('/knowledge/:navbarName/:action', {
    	  templateUrl:'knowledge/template.html',
    	  controller:'navCtrl'
    		  });
  }]);

  myApp.controller('NavbarCtrl', function ($scope,$http,$location) {
      $http.get("/js/nav.json").success(function(json){
          $scope.navbar = json;
      });
  });

  myApp.controller('textController',function($scope,$http){
	  $scope.items = {
			  '实体':'entity',
			  '事件':'event',
			  '文档':'document'
	  };
		  
//	  $scope.selectedItem = $scope.items[0]; 
	  
	  $scope.saveForm = function(object){
		  
		  alert(111)
//		  var postData = {text:'long blob of text'};  
////		  var config = {params: {id: '5'}};  
//		  $http.post('/knowledge/Concept', postData).success(function(data) {
//		      $scope.data = data;
//		      alert(111);
//		  });
		  
		  
		  
		  $http.post('/knowledge/Concept', object, {
		      headers: {
		          'Content-Type': 'application/json; charset=UTF-8'
		      }
		  }).success(function(data) {
		      $scope.data = data;
		      alert(data);
		  });
		  
		  
	  }
	  
	
  });
  var navApp = myApp.controller('navCtrl', [
        '$scope','$routeParams','$http',function ($scope,$routeParams,$http){
        	$scope.navbarName = $routeParams.navbarName;
        	$scope.action = $routeParams.action;
        	$scope.navbarUrl= "knowledge/" + $routeParams.navbarName +"/"+ $routeParams.action + ".html";
        }                                    
  ]);
  
  
  
})();