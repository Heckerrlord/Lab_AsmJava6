var app = angular.module("myApp", []);

//app.config(function($httpProvider) {
//	$httpProvider.defaults.headers.common['Authorization'] = "Basic dXNlcjI6MTIz"
//});

app.controller("myCtrl", function($scope, $http) {
	var url = "/rest/authorities";
	$http.get(url).then(resp => {
		$scope.db = resp.data;
	})

	$scope.index_of = function(username, role) {
		return $scope.db.authorities.findIndex(a => a.account.username == username && a.role.id == role)
	}

	$scope.update = function(username, role) {
		var index = $scope.index_of(username, role);
		if (index >= 0) {
			var id = $scope.db.authorities[index].id;
			$http.delete(`${url}/${id}`).then(resp => {
				$scope.db.authorities.splice(index, 1);
			})
		} else {
			var authority = {
				account: { username: username },
				role: { id: role }
			};
			$http.post('/rest/authorities', authority).then(resp => {
				$scope.db.authorities.push(resp.data);
			})
		}
	}
})