---
title: View the Generated Code
---


The YaaS Service SDK makes use of [RAML](http://raml.org/) to define and document APIs. You can add or modify resources and methods simply by modifying the RAML file. Read the [RAML 6-Step Tutorial](http://raml.org/docs.html) in order to familiarize yourself with the concept of RAML API modeling before continuing to the next section.

RAML offers several alternatives for defining data types. This guide focuses on the [JSON Schema](http://json-schema.org/), which is a great choice for lightweight service APIs. The above RAML tutorial contains an example of a JSON Schema in RAML, as does this guide.


#### RAML resource definition

By default, the API definition of your service is in the RAML file `src/main/webapp/meta-data/api.raml`.

You can modify this file to modify or manipulate the API of your service. Initially, it contains a starting point for the API of the Wishlist service, which is extended later on in this guide:

`src/main/webapp/meta-data/api.raml`:
``` no-highlight
#%RAML 0.8
title: hybris Sample Service - API for Wishlists
baseUri: http://localhost/
version: v1
mediaType:  application/json

schemas:
  - wishlists: !include schemas/wishlists.json
  - wishlist: !include schemas/wishlist.json
  - wishlistItem: !include schemas/wishlistItem.json
  - error: !include https://pattern.yaas.io/v1/schema-error-message.json

traits:
  - !include https://pattern.yaas.io/v2/trait-yaas-aware.yaml

resourceTypes:
  - !include https://pattern.yaas.io/v2/resource-type-element.yaml
  - !include https://pattern.yaas.io/v1/resource-type-collection.yaml

/wishlists:
  type: collection
  is: [yaasAware]
  get:
    description: Gets all wishlists
    responses:
      200:
        body:
          application/json:
            example: !include examples/wishlists-example.json
  post:
    description: Creates a new wishlist
    body:
      application/json:
        example: !include examples/wishlist-example.json

  /{wishlistId}:
    type: element
    is: [yaasAware]
    uriParameters:
      wishlistId:
        description: the id of the wishlist to work on
        example: 784hti8ey
        type: string
    get:
      description: Gets a wishlist
      responses:
        200:
          body:
            application/json:
              example: !include examples/wishlist-example.json

    put:
      description: Updates a wishlist
      body:
        application/json:
          example: !include examples/wishlist-example.json

    delete:
      description: Deletes a wishlist

```

This RAML file defines a **/wishlists** resource, which accepts GET and POST requests for retrieving the collection of all wishlists and adding new wishlists. It also defines the **/wishlists/{wishlistId}** resource, which defines GET, PUT, and DELETE methods that manipulate a single wishlist.

You may have noticed the references to the `*.json` and `*.yaml` files in the above RAML file. These files include JSON Schemas, resource types, and traits from the [RAML Pattern library](/tools/ramlpatterns/index.html). The RAML patterns are part of the SDK, and they serve the purpose of externalizing common parts of the RAML definition. This makes your RAML files shorter and more readable.

#### JSON Schema data type definitions

The following example JSON Schema defines a single wishlist and is a project-specific include.

`src/main/webapp/meta-data/schemas/wishlist.json`:
``` json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title":"Wishlist",
  "type":"object",
  "properties":
  {
    "id":
    {
      "type":"string"
    },
    "url":
    {
      "type":"string",
      "format":"uri"
    },
    "owner":
    {
      "type":"string",
      "pattern":"^.+"
    },
    "title":
    {
      "type":"string",
      "pattern":"^.+"
    },
    "description":
    {
      "type":"string"
    },
    "createdAt":
    {
      "type":"string",
      "format":"date-time"
    },
    "items":
    {
      "type":"array",
      "items":
      {
        "$ref":"wishlistItem"
      }
    }
  },
  "required":["id","owner","title"]
}
```

A wishlist has an ID and an owner, as well as list of **wishlistItems**. The following is a definition of a wishlist item:

`src/main/webapp/meta-data/wishlistItem.json`:
``` json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title": "Wishlist Item",
  "type" : "object",
  "properties":
  {
    "product":
    {
      "type":"string",
      "pattern":"^.+"
    },
    "amount":
    {
      "type":"integer",
      "minimum":1,
      "default":1
    },
    "note":
    {
      "type":"string"
    },
    "createdAt":
    {
      "type":"string",
      "format":"date-time"
    }
  },
  "required":["product", "amount"]
}
```
A wishlist item is a reference to the desired product. It also contains the meta information, such as the desired product quantity or the date the product was added to the wishlist.

### Overview of the generated Java code

In the project directory, open the **src/main/java** folder and view the **com.sample.wishlist** package. The **JerseyApplication** class contains the configuration of the Jersey Servlet. The following line registers all of the Jersey resources and providers that exist in the same package as **JerseyApplication** or its subpackages, so it takes care of registering the newly-generated Jersey resources:

``` java
packages(getClass().getPackage().toString());
```

Other lines register various Jersey features that help with consistent logging, JSON marshalling and unmarshalling and Basic Authorization.

### Resource-implementation stubs

The package **com.sample.wishlist.api** contains files generated from the RAML API definition. The **DefaultWishlistResource** class inside the **com.sample.wishlist.api.impl** package is the only available resource implementation, because it is the only resource currently defined. The methods of this class are merely stubs. Add your implementation and connect directly to the data persistence layer, or introduce a service layer. You can decide how to implement the service. Afterward, the API is exposed to the world, as defined by the RAML.

Open the **src/test/java** folder to locate **DefaultWishlistResourceTest** , a generated JUnit test class, inside the **com.sample.wishlist.api.impl** package. Similar to the class above, this class contains dummy tests only. They are filled in and extended during the implementation of the service.

#### Resource interfaces and DTOs

The parent interface of the **DefaultWishlistResource** also resides in the **com.sample.wishlist.api.impl** package, in the **src/main/java** folder. This directory contains numerous other files, which consist mainly of resource interfaces and Data Transfer Objects (DTOs).

The **WishlistResource** interface has all the annotations that are necessary for the Jersey REST framework and the Bean Validation framework. These annotations are inherited by the implementing resource class. They are never copied into the resource class itself.
