---
title: Partial Update
---

With the standard HTTP methods, the only way to update a resource is by using the PUT method. This usage is idempotent, and replaces the resource with the entity specified in the payload of the PUT request.

There are scenarios when a replacement of the resource is not desired, nor efficient, such as when you GET a minimal view of a resource, because only the minimal attribute set is required to display. After attributes are modified, you apply the changes. In theory, you GET the full resource data first, apply your modifications, and then PUT the modified resource. Another scenario is when a cart resource contains existing items and you want to add an item to the cart without having to send the entire cart, just to add one item.

A solution is to use the PATCH method for applying a partial update. Unfortunately, this HTTP method is not part of the [HTTP specification](https://tools.ietf.org/html/rfc7231). Instead, a dedicated specification is defined for the [PATCH](http://tools.ietf.org/html/rfc5789) method. Since it is a well-defined standard but not covered by the current HTTP specification, not all software accepts it. However, big API providers use it already, and it is a better solution than overloading other methods with additional merge semantics.

YaaS API's *should* use the PATCH method to apply partial updates to an existing resource. The default media type to accept *should* be the [JSON merge patch format](https://tools.ietf.org/html/rfc7386): `application/merge-patch+json`. However, an API *should* be tolerant by accepting requests annotated with the format: `application/json`.  

The [PATCH method](http://tools.ietf.org/html/rfc5789) specifies the following:
- Apply a set of changes described in the request entity to the resource identified by the Request-URI.
- If the Request-URI does not point to an existing resource, the server *may* create a new resource.
- A PATCH request can be issued in an idempotent way, which helps prevent bad outcomes from collisions between two PATCH requests on the same resource in a similar time frame.
- The server *must* apply the entire set of changes atomically, and it never provides a partially-modified representation. If the entire patch document cannot be successfully applied, the server *must not* apply any of the changes.

The [JSON merge patch format](https://tools.ietf.org/html/rfc7386) defines rules for merging a given JSON document to an existing server-side resource. The rules are simplified as:
- Include the properties to be updated with their new values.
- Don't include properties that are not to be updated.
- Set properties to be 'deleted' to null.

Given a product resource with an ID of `123`, such as the following:

``` json
{
   "id": "123",
   "color": "blue",
   "taste": "bitter",
   "reviews": [
     {"rating": 4, "comment":"great"},
     {"rating": 3, "comment":"average thingy"}
   ],
   "dimensions":{
     "height": 6,
     "width": 7
   },
   "size": 4
}
```

The results of the request looks similar to the following:

``` no-highlight
Request:
PATCH /products/123
  Content-Type: application/merge-patch+json
  {
     "color": null,
     "reviews": [
       {"rating": 4, "comment":"great"}
     ],
     "dimensions":{
       "width": 2
     },
    „size": 5
  }

Resulting state:
  {
   "id": "123",
   "taste": "bitter",
   "reviews": [
     {"rating": 4, "comment":"great"}
   ],
   "dimensions":{
     "height": 6,
     "width": 2
   },
   "size": 5
  }
```

### RAML Modeling
Given a product resource, such as the following:

``` no-highlight
...
 resourceTypes:
   - !include https://pattern.yaas.io/v3/resource-type-element.yaml
...
  /{productId}:
    type: element
    is: [appAware]
    get:
      description: Gets a product
    put:
      description: Replaces a product
    delete:
      description: Deletes a product
```

To support partial updates, add the PATCH method to your API resource in the RAML definition. For example:

``` no-highlight
...
 resourceTypes:
   - !include https://pattern.yaas.io/v3/resource-type-element.yaml

...
  /{productId}:
    type: element
    get:
      description: Gets a product
    put:
      description: Replaces a product
    patch:
      description: Updates a product
    delete:
      description: Deletes a product
```

See the [resource type](https://pattern.yaas.io/v3/resource-type-element.yaml) definition on how to define a PATCH method in detail.
