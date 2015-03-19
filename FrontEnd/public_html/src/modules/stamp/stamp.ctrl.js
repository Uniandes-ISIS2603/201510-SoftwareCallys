(function () {
    var stampModule = angular.module('stampModule');

    stampModule.controller('stampCtrl', ['$scope', 'stampService', function ($scope, stampService) {
            stampService.extendCtrl(this, $scope);
            this.fetchRecords();
            this.editMode = false;
            this.upload = function () {
                this.editMode = !this.editMode;
            };

            this.rate = function () {
                this.saveRecords();
            };

            this.rateStamp = function (rating) {
                $scope.currentRecord.rating = rating;
                this.saveRecords();

            };
            this.deleteStamp = function (record) {
                this.deleteRecord(record);

            };

            this.getStamp = function () {
                var self = this;
                this.api.getList().then(function (data) {
                    $scope.records = data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                });
            };
        }]);
    stampModule.directive('ratingStamps', function () {
        return{
            restrict: 'A',
            template: '<ul class="rating line1">' +
                    '<line1 ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
                    '\u2605' +
                    '</line1>' +
                    '</ul>',
            scope: {
                ratingValue: '=',
                max: '='
            },
            link: function (scope, elem, attrs) {
                var updateStars = function () {
                    scope.stars = [];
                    for (var i = 0; i < scope.max; i++) {
                        scope.stars.push({filled: i < scope.ratingValue});
                    }
                };
                scope.toggle = function (index) {
                    scope.ratingValue = index + 1;
                    scope.onRatingSelected({
                        rating: index + 1
                    });
                };
                scope.$watch('ratingValue', function (oldVal, newVal) {
                    if (newVal) {
                        updateStars();
                    }
                });
            }
        }
    });

})();