(function ()
{
    var signupModule = angular.module('signupModule', ['CrudModule', 'MockModule']);
    signupModule.constant('signup.context', 'signups');
    signupModule.constant('signup.skipMock', true);
    signupModule.config(['signup.context', 'MockModule.urlsProvider','signup.skipMock', function (context, urlsProvider, skipMock)
    {
         urlsProvider.registerUrl(context,skipMock);
    }]);
})(window.angular);