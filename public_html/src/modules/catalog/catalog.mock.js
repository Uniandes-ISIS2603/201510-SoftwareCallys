(function () {
    var countryModule = angular.module('catalogModule');

    countryModule.run(['$httpBackend', 'catalog.context', 'MockModule.mockRecords', function ($httpBackend, context, mockRecords) {
            function mockUrls(entity_url) {
                mockRecords[entity_url] = [];
                var fullUrl = baseUrl + '/' + entity_url;
                var url_regexp = new RegExp(fullUrl + '/([0-9]+)');
                $httpBackend.whenGET(fullUrl).respond(function (method, url, data, params) {
                    var responseObj = {totalRecords: mockRecords[entity_url].length};
                    if (params && params.page && params.maxRecords) {
                        var start_index = (params.page - 1) * params.maxRecords;
                        var end_index = start_index + params.maxRecords;
                        responseObj.records = mockRecords[entity_url].slice(start_index,end_index);
                    }else {
                        responseObj.records = mockRecords[entity_url];
                    }
                    return [200, responseObj, {}];
                });
                $httpBackend.whenGET(url_regexp).respond(function (method, url) {
                    var id = parseInt(url.split('/').pop());
                    var record;
                    angular.forEach(mockRecords[entity_url], function (value) {
                        if (value.id === id) {
                            record = angular.copy(value);
                        }
                    });
                    return [200, record, {}];
                });
                $httpBackend.whenPOST(fullUrl).respond(function (method, url, data) {
                    var record = angular.fromJson(data);
                    record.id = Math.floor(Math.random() * 10000);
                    mockRecords[entity_url].push(record);
                    return [200, record, {}];
                });
                $httpBackend.whenPUT(url_regexp).respond(function (method, url, data) {
                    var record = angular.fromJson(data);
                    angular.forEach(mockRecords[entity_url], function (value, key) {
                        if (value.id === record.id) {
                            mockRecords[entity_url].splice(key, 1, record);
                        }
                    });
                    return [200, null, {}];
                });
                $httpBackend.whenDELETE(url_regexp).respond(function (method, url) {
                    var id = parseInt(url.split('/').pop());
                    angular.forEach(mockRecords[entity_url], function (value, key) {
                        if (value.id === id) {
                            mockRecords[entity_url].splice(key, 1);
                        }
                    });
                    return [200, null, {}];
                });
            }
            ;
            var ignore_regexp = new RegExp('^((?!' + baseUrl + ').)*$');
            $httpBackend.whenGET(ignore_regexp).passThrough();
            for (var i in urls) {
                if (urls.hasOwnProperty(i)) {
                    mockUrls(urls[i]);
                }
            }
        }]);
})();