---
title: 'Example of API Calls'
type: Developers Guide
---

There are a couple of ways that you can maintain your storefront; you can use the Builder, or you can use the APIs. To show you how easy it is to use the APIs, here's an example of how to checkout a cart and create an order using API calls. For this example to work you need:

- Your website is set up and has been deployed.
- The products and their prices are set up for the website.
- Tax, payment provider, and shipping cost have been configured in the Site Settings service.

This process adds an item to the cart, completes the checkout, and creates an order.

1. Create a token.
2. Create a cart.
3. Add an item to the cart.
4. Checkout the cart. This includes:
	- Validating the cart information.
	- Validating the coupon (if applicable).
	- Redeem the coupon (if applicalble).
	- Capture the payment.
	- Place the order and complete the checkout process.

### Create a token

Before you can start to make any API calls, you need to create an authentication (OAuth2) token. This token is only active for 1 hour once it is created. To create an authentication token see, <a href="/gettingstarted/getanaccesstoken/index.html">Get an Access Token</a>. This token is needed to complete the remaining calls.

For more information on OAuth2 and tokens, see <a href="/services/oauth2/latest/index.html">OAuth2</a>.

### Create a cart

When a customer begins shopping on the website, a cart needs to be created before they can add an item to the cart. The customer can shop as an anonymous shopper or they can create/log in to their account. In this example we are going to assume this is a registered customer with an account.

**Method**: POST
**Request URL**: `https://api.yaas.io/hybris/cart/b1/{tenant}/carts`
**Example**:
```
{
    "customerId": "C12345",
    "currency": "USD"
}
```

### Add item to the cart

The customer found a dress she wants to add to her cart. This call adds the dress to her cart.

**Method**: POST
**Request URL**: `https://api.yaas.io/hybris/cart/b1/{tenant}/carts/{cartId}/items`
**Example**:
```
{
    "product": {
        "id": "555dfac159ce92ce759c2f2c",
        "sku": "sfd12324",
        "name": "Polka Dot Dress",
        "description": "Short sleeved, black and white polka dot dress",
        "images": [
            {
                "url": "http://img.sheinside.com/images/sheinside.com/201309/1379214988143179502.jpg"
            }
        ]
    },
    "quantity": 1,
    "price": {
        "originalAmount": 40.0,
        "effectiveAmount": 40.0,
        "currency": "USD",
        "priceId": "555dfac2acce048c99ca890c"
    }
}
```

### Checkout process

Completing a checkout is an intricate and complex process where the Checkout service needs to go through numerous validations before an order is created. Though you only execute one call, the call completes the following:

1. Validate the cart information.
2. Validate the coupon (if applicable).
3. Redeem the coupon (if applicable).
3. Capture the payment.
5. Place the order.

**Method**: POST
**Request URL**: `https://api.yaas.io/hybris/checkout-mashup/b1/{tenant}/checkouts/order`
**Example**:
```
{
    "payment": {
        "paymentId": "stripe",
        "customAttributes": {
            "token": "tok_162ksr4xLYxmKjcz2rybVBQ8"
        }
    },
    "addresses": [
        {
            "zipCode": "77043",
            "country": "US",
            "streetNumber": "10615",
            "city": "Houston",
            "contactName": "Peter Priceless",
            "street": "Shadow Wood Drive",
            "companyName": "Hybris",
            "state": "TX",
            "contactPhone": "1-976-338-9922",
            "type": "BILLING"
        },
        {
            "zipCode": "77043",
            "country": "US",
            "streetNumber": "10615",
            "city": "Houston",
            "contactName": "Peter Priceless",
            "street": "Shadow Wood Drive",
            "companyName": "Hybris",
            "state": "TX",
            "contactPhone": "1-976-338-9922",
            "type": "SHIPPING"
        }
    ],
    "shippingCost": 20,
    "totalPrice": 60,
    "cartId": "555dff7deb0e108b167244aa",
    "currency": "USD",
    "customer": {
        "id": "jane.doe@email.com",
        "name": "Jane Doe",
        "title": "Miss.",
        "firstName": "Jane",
        "middleName": "D.",
        "lastName": "Jane",
        "email": "jane.doe@email.com",
        "company": "SAP"
    }
}
```

Once you make a checkout request, the following events are executed.

**1. Validate the cart information**

There are several checks the Checkout service executes as it validates the cart information. The following information is checked in this order:

1. Checkout service checks to see if the cart is empty or not. If it is empty, an error is returned. If it is not, it proceeds to the next step.
2. It validates that the product exists and is available by calling the Product service.
3. It validates that the price is current. It checks that the price and product matches and verifies that all the price attributes are valid.
4. It ensures that the item is in stock.
5. It verifies that the shipping cost is valid.
6. It calls the customer service to verify that the customer information is valid.

Once these validations are passed, it continues to the coupon validation.

**2. Validate the coupon**

If the seller has a promoting that needs to be applied to the cart or the customer has a coupon that they want to redeem, a call is made to the Coupon service to validate this coupon information. Of course this is an optional service that can be configured in the Coupon service.

**3. Redeem the coupon**

If the customer uses a coupon, the coupon must be redeemed so the customer cannot use it again. A call is made to the Coupon service to redeem the coupon.

**4. Capture the payment**

Checkout service's next step is to capture the payment. Currently there is only one payment provider that comes with the Site Management package (Stripe Payment). <!--You can customize another payment provider (see Extension points and custom logic).-->

**5. Place the order**

To complete the checkout, the order must be placed. The cart information is sent to the Order service and an order ID is created. The Order service then sends the order ID back to the Checkout service. If the order is successfully created, the Checkout service calls Cart service and the cart status goes from open to closed. This cart ID can no longer be used. If the order is unsuccessfully created, Checkout service calls the Configuration service to get the customer's email address. Then the Email service is called and an email with a checkout ID is sent to the customer to let them know that the order was unsuccessfully created.
