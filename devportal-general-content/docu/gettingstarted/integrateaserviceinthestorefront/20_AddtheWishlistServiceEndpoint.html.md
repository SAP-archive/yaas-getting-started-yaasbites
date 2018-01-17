---
title: Add the Wishlist Service Endpoint
---

The **site-config.js** file configures the endpoints for the services that the storefront consumes. The endpoints are properties of the **this.apis** object. The URLs are configured dynamically depending on the deployment. For the purpose of this tutorial, the Wishlist service does not need a dynamic endpoint.

With the Wishlist service running on your local machine at port `8080`, insert the following code sample at the bottom of the endpoint list to add the Wishlist service endpoint:

```
wishlist: {
    baseUrl: 'http://localhost:8080/'
}
```
