---
title: Implement the Controller Code
---

The controller is responsible for defining the view's model in the scope, as well as intercepting user interactions and passing them to the appropriate functions in the UI service, which then delegates to the REST endpoint. Because the button to add a product to a wishlist will be on the **Product Detail** page, some simple controller code is added to the **public/js/app/products/controllers/product-detail-ctrl.js** file. Open the **products-index.js** file and add the **ds.wishlist** module as a dependency:

``` javascript
'use strict';

angular.module('ds.products', [
    'restangular',
    'ds.shared',
    'ds.cart',
    'ui.bootstrap',
    'ds.wishlist'
]);
```

Because you are calling the **addProductToWishlist** function from the Wishlist service, add the **WishlistSvc** as a dependency on the **ProductDetailCtrl** function. For example:

``` javascript
.controller('ProductDetailCtrl', ['$scope', '$rootScope', 'WishlistSvc', 'CartSvc', 'product', 'lastCatId', 'settings', 'GlobalData', 'CategorySvc','$filter',
    function($scope, $rootScope, WishlistSvc, CartSvc, product, lastCatId, settings, GlobalData, CategorySvc, $filter) {
```

Create an empty function on the controller's scope called **addToWishlist()**. For example:

``` javascript
$scope.addToWishlist = function () {

};
```

<div class="panel note">
The Wishlist service's <a href="http://localhost:8080/api-console/index.html">RAML specification</a> describes what REST operations are possible for a service, what headers the service expects, and how the service accepts and returns data. Clicking the POST button on the wishlist's RAML document shows an example request body, the request schema, and a list of required request headers.
</div>

After inspecting the schema, note the required attributes for the POST request body are **id**, **owner**, and **title**, all of which are **string** types. In a real-world scenario, you want to generate an ID, use the currently logged-in customer account number for the **owner** attribute, and enable the user to define their wishlist title. For this tutorial, simply set some arbitrary data for these attributes. Because the example Wishlist service that is running locally doesn't actually store any data, it enables you to make multiple posts with this same data without throwing any errors.

Create a new JavaScript object inside the **addToWishlist()** function named **newWishlist**. This object contains the **title** property. Set the value for this property to `defaultWishlistTitle`. The **newWishlist** function also contains a property called **items**, which, in accordance with the RAML specification for the Wishlist service, is an array. The item in this array has two properties: **product** and **amount**. The data for these properties is available on the `$scope` line. After this object is constructed, it gets passed to the **createWishlist** function of the **WishlistSvc**. Your **$scope.addToWishlist()** function should look similar to the following:

``` javascript
$scope.addToWishlist = function () {
    var newWishlist = {
        id: 'defaultWishlistId',
        owner: 'wishlistOwner@hybris.com',
        title: 'defaultWishlistTitle',
        items: [
            {
                product: $scope.product.product.id,
                amount: $scope.productDetailQty
            }
        ]
    };
    WishlistSvc.createWishlist(newWishlist);
};
```

At this point, you have completed all of the JavaScript logic for creating a new wishlist with a single item.  The only remaining step is to add the necessary HTML to add the button on the page.
