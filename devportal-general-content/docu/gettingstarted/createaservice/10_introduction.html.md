---
title: Introduction
---

In this guide, you learn how to create, adjust, and run a simple REST service using the [YaaS Service SDK](/tools/servicesdk/index.html). The SDK implements Java-based services and makes use of RAML, Maven, Spring, Jersey, and other popular technologies. The resulting service can run locally, or it can be deployed to a cloud environment.

Although this guide uses a Java example, other programming languages or Java technologies are supported. Once you have a REST web service that is deployable on the web, continue to the [last step](/gettingstarted/createaservice#6) of this guide.

### Scope of this guide

This guide explains how to define your service's API using RAML, which generates the boilerplate REST service code with stubs for your application logic. The service can be extended to interact with other services in YaaS.
However, that is out of the scope for this guide, as is implementation of the application logic itself or design of a data storage layer.

<div class="panel tip">
The deployment of the service is not part of this guide. For more information about deploying a service to a cloud environment, see  [Deployment](/overview/deployment/index.html).</div>

### The example Wishlist service

The example service created in this guide is a **Wishlist** service, as featured in many online shops. Its function is to itemize the products a customer wants but is not going to purchase yet. Such products are put on the customer's wishlist, which can be used for gift-giving when shared with other users, or as a reminder to the consumer to purchase the product later.

The Wishlist service initially has two REST resources:

* **/wishlists** — For getting a list of all the wishlists in the service.
* **/wishlists/{wishlistId}** — For getting one particular wishlist and its products.

In the guide, you learn how to add and modify existing resources.

<div class ="panel info"> To learn about using services other than this Wishlist service example, read the Service SDK <a href="/tools/servicesdk/index.html#GeneratingClients">Client Generation</a> documentation.
</div>
