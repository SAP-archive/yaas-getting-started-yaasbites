---
type: Extensibility
title: 'Extensibility in YaaS'
---

You can extend many YaaS services to meet your own needs in four different ways:

* <a href="#mixins">Mixins</a>
* <a href="#plugins">Plug-ins</a>
* <a href="#mashups">Mashup services</a>
* <a href="#events">Events (managed by the PubSub service)</a>

This extensibility framework allows you to extend and scale the functionality of your services without major rewriting of code or architectural changes.

### Background information about extensibility in YaaS

The following topics give more detailed background information and examples of extensibility in YaaS for data structures, Builder module implementations, and the storefront application.

* <a href="/overview/extensibility/index.html#ExtendingYaaSDataStructures">Extending YaaS Data Structures</a>
* <a href="/overview/extensibility/index.html#ExtendingtheBuilder">Extending the Builder</a>
* <a href="/overview/extensibility/index.html#ExtendingtheStorefront">Extending the Storefront</a>

<a name="mixins"></a>
<h3>Mixins</h3>

The mixins feature can help you to define a set of properties once and then reuse them in many different products.  You can use mixins to add additional data to suit your needs.  For example, you can use mixins to add additional attributes to products that do not exist in the Product service, or add additional custom fields to customer information in the Customer service.  You can assign one mixin to many products, and you can also use many mixins in one product. This approach supports flexibility, re-usability, and structural consistency among all your products.  You can use mixins with numerous YaaS services, including the Product, Order, Customer, and Site services.

Following is a basic process flow for using mixins:

1. Define your schema using JSON and store it in the Document repository.<br>
``` json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
   "type": "object",
   "properties": {
       "message": "text",
       "numberOfCores": "integer"
   }
} ```

1. Add your schema reference as metadata and submit the data with the payload.<br>
``` json
{
     "name": "Smartphone Sony Xperia Z2",
     "description": "The world's best camera and camcorder in a smartphone.",
     "sku": "SonyXperiaZ2_123",
     "metadata":{
        "mixins":{
           "additionalInfo":"https://api.yaas.io/hybris/schema-repository/b1/single/additionalInfo_v1"
        }
     },
     "mixins":{
        "additionalInfo":{
           "message": "Waterproof smartphone",
           "numberOfCores": 4
        }
     }
} ```

#### Mixin tutorials

The following tutorials give step-by-step examples of how to use mixins in various YaaS services.

##### Using mixins in the Document service
* <a href="/services/document/latest/index.html#Mixins">Mixins</a>
* <a href="/services/document/latest/index.html#OperateonMixins">Operate on Mixins</a>

##### Using mixins in the Product service
* <a href="/services/product/latest/#Mixins">Mixins</a>
* <a href="/services/product/latest/#Extensibility">Extensibility</a>
* <a href="/services/product/latest/#PartialUpdate">Partial Update</a>

##### Using mixins in the Order service
* <a href="/services/order/latest/index.html#ExtendingOrderInformation">Extending Order Information</a>

##### Using mixins in the Customer service
* <a href="/services/customer/latest/index.html#ExtendCustomerInformation">Extend Customer Information</a>

##### Using mixins in the Site service
* <a href="/services/site/latest/index.html#ExtendSiteInformation">Extend Site Information</a>
* <a href="/services/site/latest/index.html#CustomSiteConfigurationwithMixins">Custom Site Configuration with Mixins</a>

##### Using mixins in the Cart service
* <a href="/services/cart/latest/index.html#ExtendCartInformation">Extend Cart Information</a>

<a name="plugins"></a>
### Plug-ins

You can customize the cart and checkout flow using plug-ins to seamlessly incorporate external service providers. YaaS supports plug-ins to calculate taxes, find shipping options, and process payments. For example, you can find a Stripe plug-in for payments and an Avalara Tax plug-in for tax calculation on the YaaS Market. To integrate with other third parties or develop your own logic, you can develop plug-ins yourself by implementing the respective REST API/interface and configuring your service using the Site service.

Following is a basic process flow for using plug-ins.  This example shows how to set up a plug-in for the Avalara Tax service:

1. Implement the interface.<br>
<img src="/overview/extensibility/img/ImplementInterface.png">

1. Set up the plug-in in the Site service.<br>
``` json
{
    "id": "AVALARA",
    "name": "Avalara Tax Service",
    "serviceType": "urn:x-yaas:service:tax",
    "serviceUrl": "http://avalara-b1.prod.cf.hybris.com",
    "active": true,
    "configuration": {
      "restricted": {
        "license": "Your Avalara license key",
        "url": "https://development.avalara.net",
        "apiVersion": "1.0",
        "account": "Your Avalara account ID"
      }
    }
}
```

<a name="mashups"></a>
### Mashups

You can use mashups to implement custom logic that spans several services. A mashup combines information from multiple sources into a single new service and/or calls different services for complex operations. For example, a checkout service in a storefront is a mashup service because it utilizes information and uses functionality from multiple services, such as the Product, Price, Cart and Order services.<br>
<img src="/overview/extensibility/img/Extensibility_mashups.png" width="400" class="img-click-modal" alt="Mashup service">

<a name="events"></a>
### Events and the PubSub service

You can orchestrate the behavior of multiple services using messages and events that are managed in YaaS by the PubSub service.

The PubSub service enables applications on the YaaS platform to integrate using asynchronous message-based communication. Messages exchanged through the PubSub service typically represent information about business events, such as an event in which a commerce order is created. The PubSub service serves as a message bus.

Using the PubSub service, multiple messaging channels can be established, one dedicated for each business event type exchanged. Hybris SAP refers to these message channels over which applications communicate as channel-specific events or topics. This enables you to add custom logic to existing services, such as triggering additional actions whenever your service retrieves an **order-created** message.<br>
<img src="/overview/extensibility/img/Extensibility_PubSub.png" width="400" class="img-click-modal" alt="Pub/Sub Service">
