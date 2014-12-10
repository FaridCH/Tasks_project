var app = angular.module('ToDoList-app', [ 'ngRoute', 'ngResource' ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/taches', {
		templateUrl : 'todos.html',
		controller : 'TachesController'
	}).when('/taches/create', {
		templateUrl : 'tache-create.html',
		controller : 'TacheCreateController'
	}).when('/taches/update/:tacheId', {
		templateUrl : 'tache-update.html',
		controller : 'TacheUpdateController'
	}).when('/taches/update', {
		templateUrl : 'finir-tache.html',
		controller : 'TacheMakeDoneController'
	})

});

app.factory('Tache', function($routeParams, $resource) {

	return $resource('http://localhost:8080/taches/:tacheId', {

		tacheId : '@tacheId'
	})
});

app.controller('TachesController', function($scope, Tache) {

	$scope.taches = Tache.query();
	$scope.tachesFinies = "Finie";
	$scope.tachesEnCours = "En cours";
	$scope.tacheFinie = function(tache) {
		return tache.state === $scope.tachesFinies;
	};
	$scope.tacheEnCours = function(tache) {
		return tache.state === $scope.tachesEnCours;
	};

});

app.controller('TacheCreateController', function($scope, Tache) {

	$scope.tache = new Tache();
	$scope.tache.state = "En cours";
	$scope.createTache = function() {

		$scope.tache.$save();
		window.location.href = 'http://localhost:8080/#/taches';
	};
});

app.controller('TacheUpdateController', function($scope, $routeParams, Tache) {

	$scope.tacheId = $routeParams.tacheId;

	$scope.tache = Tache.get({
		tacheId : $scope.tacheId
	});
	
	$scope.updateTache = function() {

		$scope.tache.$save();
		window.location.href = 'http://localhost:8080/#/taches';
	};
});

app.controller('TacheMakeDoneController', function($scope, Tache) {

	$scope.taches = Tache.query();

	$scope.onCompleteTodo = function(tache) {

		// console.log("onCompleteTodo -done: " + tache.state + " " + tache.id);
		tache.$save();
	};
	$scope.valider = function(tache) {
		window.location.href = 'http://localhost:8080/#/taches';
	};
});
