var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', { templateUrl: 'pages/home.html' })
        .when('/about', { templateUrl: 'pages/about.html' })
        .when('/contact', { templateUrl: 'pages/contact.html' })
        .when('/student', { templateUrl: 'pages/student.html', controller: 'myCtrl1' })
        .when('/students', { templateUrl: 'pages/students.html', controller: 'myCtrl2' })
        .when('/category', { templateUrl: 'pages/category.html', controller: 'myCtrl3' })
        .when('/excel', { templateUrl: 'pages/excel.html', controller: 'myCtrl4' })
        .when('/filemanager', { templateUrl: 'pages/filemanager.html', controller: 'myCtrl5' })
});

// Student
let host = "https://java6poly-it16315-default-rtdb.asia-southeast1.firebasedatabase.app";
app.controller("myCtrl1", function ($scope, $http) {
    $scope.form = {};
    $scope.items = {};
    $scope.load_all = function () {
        var url = `${host}/students.json`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success ", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.edit = function (key) {
        var url = `${host}/students/${key}.json`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            $scope.key = key;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/students.json`;
        $http.post(url, item).then(resp => {
            $scope.key = resp.data.name;
            $scope.items[$scope.key] = item;
            $scope.reset();
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/students/${$scope.key}.json`;
        $http.put(url, item).then(resp => {
            $scope.items[$scope.key] = resp.data;
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.delete = function (key) {
        var url = `${host}/students/${key}.json`;
        $http.delete(url).then(resp => {
            delete $scope.items[key];
            $scope.reset();
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.reset = function () {
        $scope.form = { gender: true, country: 'VN' };
        $scope.key = null;
    };
    // Thực hiện tải toàn bộ students
    $scope.load_all();
    $scope.reset();
});

// Students
let server = "http://localhost:8080/rest";
app.controller("myCtrl2", function ($scope, $http) {
    $scope.form = {};
    $scope.items = {};
    $scope.load_all = function () {
        var url = `${server}/students`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success ", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.edit = function (email) {
        var url = `${server}/students/${email}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${server}/students`;
        $http.post(url, item).then(resp => {
            $scope.items.put(item);
            $scope.reset();
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${server}/students/${$scope.form.email}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.email == $scope.form.email);
            $scope.items[index] = resp.data;
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.delete = function (email) {
        var url = `${server}/students/${email}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(item => item.email == email);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success ", resp);
        }).catch(error => {
            console.log(" Error", error);
        });
    }
    $scope.reset = function () {
        $scope.form = { gender: true, country: 'VN' };
        $scope.key = null;
    };

    // Thực hiện tải toàn bộ students
    $scope.load_all();
    $scope.reset();
});

// Category
app.controller("myCtrl3", function ($scope, $http) {
    let url = "http://localhost:8080/rest/categories";
    $http.get(url).then(resp => {
        console.log("Success ", resp);
    }).catch(error => {
        console.log("Error", error);
    });
});

// Excel
app.controller("myCtrl4", function ($scope, $http) {
    $scope.import = function (files) {
        var reader = new FileReader();
        reader.onloadend = async () => { // => reader.result	
            var workbook = new ExcelJS.Workbook();
            await workbook.xlsx.load(reader.result);
            const worksheet = workbook.getWorksheet(1); // lấy sheet đầu tiền
            worksheet.eachRow((row, index) => {
                if (index > 1) {
                    let student = {
                        email: row.getCell(1).value,
                        fullname: row.getCell(2).value,
                        marks: +row.getCell(3).value,
                        gender: true && row.getCell(4).value,
                        country: row.getCell(5).value
                    }
                    var url = "http://localhost:8080/rest/students";
                    $http.post(url, student).then(resp => {
                        console.log("Success", resp.data);
                    }).catch(error => {
                        console.log("Error", error);
                    })
                }
            });
        };
        reader.readAsArrayBuffer(files[0]);
    }
});

// File Manager
app.controller("myCtrl5", function ($scope, $http) {
    var url = `http://localhost:8080/rest/files/images`;
    $scope.url = function (fileimage) {
        return `${url}/${fileimage}`;
    };
    $scope.list = function () {
        $http.get(url).then(resp => {
            $scope.filenames = resp.data;
        }).catch(error => {
            console.log("Errors", error);
        });
    };
    $scope.upload = function (files) {
        var form = new FormData();
        for (var i = 0; i < files.length; i++) {
            form.append("files", files[i]);
        }
        $http.post(url, form, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.filenames.push(...resp.data);
        }).catch(error => {
            console.log("Errors", error);
        });
    };
    $scope.delete = function (filename) {
        $http.delete(`${url}/${filename}`).then(resp => {
            let i = $scope.filenames.findIndex(name => name == filename);
            $scope.filenames.splice(i, 1);
        }).catch(error => {
            console.log("Errors", error);
        });
    };
    //
    $scope.list();
});


// jQuery
jQuery(function ($) {
    "use strict";
    $('.dropdown-menu a.dropdown-toggle').on('click', function (e) {
        if (!$(this).next().hasClass('show')) {
            $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
        }
        var $subMenu = $(this).next(".dropdown-menu");
        $subMenu.toggleClass('show');
        $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
            $('.dropdown-submenu .show').removeClass("show");
        });
        return false;
    });
});