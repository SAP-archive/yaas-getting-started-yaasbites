---
title: 'Commerce Packages'
type: Developers Guide
---

The commerce packages consists of services that lets you:

-  configure and set up your storefront
-  add products to your store
-  have customers add products to their shopping cart,
-  have customers checkout their order
-  ship the order to the customer.

The services are divided into the following packages:

- Product Content
- Customer Accounts
- Order Management
- Coupon Management
- Site Management
- Cart
- Checkout

Each commerce package is accompanied by a corresponding Builder module which lets you use those services within Builder. The <a href="../gettingstarted/setupastorefront/index.html">Storefront template</a> can be your starting point for some front-end development.

You can use the Builder and the Storefront user interface to set up majority of the site, but as a developer you can use the list of APIs which accompanies each service and can be found in each service's documentation in the **API Docs** section.

The following tables describe the services in each of packages and the includes information on the services that they interact with.

### Product Content package

The Product Content package consists of the Algolia Search, Product, Product Details, Category, Price, and PCM services.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Algolia Search|Use this service to create a product index of all your active products and allow your customer to search for them in your storefront.|Category, Product, Account, and Configuration|
|Category|Creates groups to help you organize the products in your store.|Product, Product Details, and Algolia Search|
|PCM|The Product Content Management (PCM) service supports the user interface application to manage the product content.|Algolia Search, Category, and Schema |
|Price|To assign a price value in different currency to a specific product.|Product|
|Product|To add products and their corresponding images to the store.|Algolia Search, Cart, Category, Price, Product Details, and PubSub|
|Product Details|A mashup service that takes the information from the Product, Price, and Category service to show you the details of all of the products in your inventory.|Product, Price, and Category |

### Customer Accounts package

The Customer Accounts package consists only of the Customer service.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Customer|Manages the customer's account and details about the customer.|Cart, Coupon, Order, Site, Schema, and PubSub|

### Order Management package

The Order Management package consists only of the Order service.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Order|Manages the order flow. It serves as the primary order storage system and exposes events and notifications for connected fulfillment systems.|Checkout and Email|

### Coupon Management package

The Coupon Management package consists only of the Coupon service.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Coupon|Issues a discount code to the customer that they can apply on their order. This discount is applied to the order calculation during checkout.|Cart, Checkout, and Customer|

### Site Management package

The Site Management package consists of the Site, Tax, Avalara Tax, Stripe Payment, and Shipping services.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Avalara Tax|This is a combination of the third party Avalara product with the Avalara Tax service which calculates the taxes based on the shipping address and the list of items in the cart. This service can only be used for customers in Canada or the United States.|Site, Cart, and Checkout|
|Shipping|A rate charged to the customer to have their order shipped to them.|Checkout and Site |
|Site|A collection of language, currency, shipment, payment, and tax configurations. For shipping, payment, and tax, you can have a third party provider and use the corresponding service to extend it to your site.|Avalara Tax, Cart, Shipping, Stripe Payment, and Tax |
|Stripe Payment|Works with the API from Stripe to charge the customer's credit card when they make a purchase. The Stripe Payment service needs to be configured in the Site service for it to be used.|Checkout and Site |
|Tax|A default single rate tax that is applied on the items in an order. Other tax codes can override the default rate. The tax is configured in the Site service.|Cart, Checkout and Site|

### Cart package

The Cart package consists only of the Cart service.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Cart|Manages the customer's cart and the items within the cart.|Checkout, Coupon, Customer, Product, Price, Tax, Avalara Tax, Shipping, and Site|

### Checkout package

The Checkout package consists only of the Checkout service.

|Service|Description|Interacts With|
|-------|-----------|--------------|
|Checkout|A mashup service that takes the items in the customer's cart, calculates the order, captures the payment, and create an order for the seller.|Configuration, Cart, Email, Site, Shipping, Stripe Payment, Tax, Avalara Tax, Coupon, PubSub, and Order.|


<!--### Overview of available functionality

*Combination of step-by-step of using the service to create a storefront along with a diagram to show how the commerce services interact with one another. Klaus said I could get input from the developers here. This portion combines a bit of the service to setup the storefront section.*

### Using service to setup the storefront

*Step-by-step process of how to set up the storefront using the commerce services. This could be generic information that could be useful to both devs and people who are just using the Builder.* -->
