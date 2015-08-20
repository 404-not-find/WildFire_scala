(function() {
  'use strict';

  var treesApp = angular.module('knowledgeApp', ['ui.tree']);
  treesApp.controller('treesCtrl', function($scope) {

    $scope.remove = function(scope) {
      scope.remove();
    };

    $scope.toggle = function(scope) {
      scope.toggle();
    };

    $scope.newSubItem = function(scope) {
      var nodeData = scope.$modelValue;
      nodeData.nodes.push({
        id: nodeData.id * 10 + nodeData.nodes.length,
        title: nodeData.title + '.' + (nodeData.nodes.length + 1),
        nodes: []
      });
    };

    $scope.editSubItem = function(scope){
    	alert(scope);
    }
    $scope.tree1 = [{
      "id": 1,
      "title": "词袋",
      "nodes": [],
    }];
    $scope.tree2 = [{
      "id": 1,
      "title": "tree2 - item1",
      "nodes": [],
    }, {
      "id": 2,
      "title": "tree2 - item2",
      "nodes": [],
    }, {
      "id": 3,
      "title": "tree2 - item3",
      "nodes": [],
    }, {
      "id": 4,
      "title": "tree2 - item4",
      "nodes": [],
    }];
  });

})();