---
title: 'Extension Point and Custom Logic'
type: Developers Guide
---

Apart from the basic service functionality, you have the ability to customize or extend the service to suit your needs. This section of the guide shows you:

- How to use events as alerts between services to indicate when an action needs to be taken.
- How to use mixins to create additional attributes. The extra attributes can let you add more details to your services where it is needed.
- How third party services are integrated with some of the commerce services to provide you with ease for payment capture, tax calculation, and applying a search engine to your store.

<!--*Should also show here how developers can create their own services and upload it to the App Exchange. This leads to links to the How to build and Deploy a custom service.* -->

### Events

Events are instances that occur in one service that another service needs to be aware of. One service is known as the publisher or the creator of the event. The other service is known as the consumer or the service that is interested in the event. In order for the consumer service to know that an event has occurred, you need the <a href="../pubsub/latest/index.html">PubSub service</a> to act as the messaging service.

To publish, read, and commit events, you need to ensure the following:

1. Create a topic. Topics are triggers for events. Topics have already been created for services that have events. To create new topics for your own service, see <a href="/services/pubsub/latest/index.html#RESTAPIOverview">Create a new topic</a>.
2. Ensure the events are being published. See <a href="/services/pubsub/latest/index.html#RESTAPIOverview">Publish an event</a>.
3. The consuming service reads the event. See <a href="/services/pubsub/latest/index.html#RESTAPIOverview">Read the event</a>.
4. Once the events are read, they need to be commited as proof of delivery to the client. See <a href="/services/pubsub/latest/index.html#RESTAPIOverview">Commit the event</a>.

Currently the following services have events:

- <a href="/services/category/latest/index.html#Events">Category</a>
- <a href="/services/checkout/latest/index.html#Events">Checkout</a>
- <a href="/services/coupon/latest/index.html#Events">Coupon</a>
- <a href="/services/customer/latest/index.html#Events">Customer</a>
- <a href="/services/order/latest/index.html#Events">Order</a>
- <a href="/services/product/latest/index.html#Events">Product</a>

For a list of all the available events, see <a href="/tools/developmentguidelines/index.html#Events">Events</a>.

<div class="panel note"> Currently you cannot create new events for the services in the commerce packages. </div>

### Mixins

Mixins let you add additional attributes to your service. Once you define a set of attributes for a service, you can reuse them again. For example, in Product service you can use mixins to create an additional category to group your products.

The following are the services where you can use mixins. Each service describes how mixins can enhance your service:

- <a href="/services/cart/latest/index.html#ExtendCartInformation">Cart</a>
- <a href="/services/customer/latest/index.html#ExtendCustomerInformation">Customer</a>
- <a href="/services/order/latest/index.html#ExtendOrderInformation">Order</a>
- <a href="/services/product/latest/index.html#ExtendProductInformation">Product</a>
- <a href="/services/site/latest/index.html#ExtendSiteInformation">Site</a>

### Third party providers

The Site Management and Product Content packages provide you with services that use a third party service to search the store's products, calculate the tax, and complete the payment for a customer's order. Currently, the following third party services are provided:

- Algolia Search
- Avalara Tax
- Stripe Payment

<!--You are not limited to these provided services. You can create your own application to use a different search, tax, or payment provider and upload the new application to the App Exchange. For more information on creating an application, see <a href="/gettingstarted/createaservice/index.html">Create a Service</a>.

<div class="panel note"> Your new application needs to be configured in the Site service before you can use it. </div> -->

#### Algolia Search

Algolia Search uses a third party service (Algolia) to enable customers to search through a store's merchandise. Algolia applies data indexing to the active store products that lets customers find products with ease.

To use this service you need to create an account with Algolia, set your configurations, and create the data indexing information for your products.

For more details, see <a href="/services/algoliasearch/latest/index.html">Algolia Search</a>.

#### Avalara Tax

There are currently two tax services in the Site Management package:

1. Tax
2. Avalara Tax

Avalara Tax applies tax on the items in an order. This service works with the Avalara API to calculate the taxes on the order. It uses the shipping destination address	to calculate the tax. For this reason you can only calculate taxes for Canada and the United States.

For Avalara Tax to work you need to create an account with the third party provider (Avalara) before you confirgure the service in the Site service.

For more details, see <a href="/services/avalaratax/latest/index.html">Avalara Tax</a>.

#### Stripe Payment

The Stripe Payment service lets you complete an order by charging the customer's credit card. The Checkout service uses the Stripe Payment service along with the Stripe API to create an order. As with the other third party services, you need to create an account with Stripe and configure the service in Site service before you can use it.

For more details, see <a href="/services/stripepayment/latest/index.html">Stripe Payment</a>.
