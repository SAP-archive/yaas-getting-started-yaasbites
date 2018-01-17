---
title: Response Filtering
---

The API of an SAP Hybris service is designed to meet the most common requirements. Therefore, the payload of a response might not fit every use case because it contains too much data. A single resource entity contains many data fields, and the horizontal filtering is addressed, but the payload still contains too many entities.

A common approach is to limit the attributes returned for a single entity by applying some kind of filter specifying only the attributes of interest. Filtering can highly decrease network traffic, as well as decrease server and client-side marshalling and unmarshalling of data.

To specify a filter to influence the size of a single entity, provide the field's query parameter. It specifies the attributes as a comma-separated list that an entity must contain.  For example, a product with **code**, **name**, and **description** fields can have a filter applied in the following manner:

``` no-highlight
Request:
GET /products/123?fields=code

Response:
  {
    "code": "52f56f583004a2d134ddb393"
  }
```

Another request having a list of fields specified looks similar to the following:

``` no-highlight
Request:
GET /products/123?fields=code,name

Response:
  {
    "code": "52f56f583004a2d134ddb393",
    "name": "fancy shoe"
  }
```

A request returns a response code of `400` if the value of the limited request attribute is not provided or is empty. The request does not fail when limiting to non-existing fields. Instead, it returns a minimal, essential set of fields to identify the entities clearly, such as the ID.

### RAML modeling
Given a collection resource with a GET method, such as the following:

``` no-highlight
...
/products:
  type: collection
  get:
    description: Gets all products
```

Specify the GET method as filterable by applying a query parameter, such as this public pattern already provided:

``` no-highlight
traits:
   - !include http://pattern.yaas.io/v1/trait-limited.yaml

/products:
  type: collection
  get:
    is: [limited]
    description: Gets all products
```
