---
title: Pagination
---

A collection resource manages a collection of resources, usually retrieved by a GET method. The collection might be too big to return it efficiently, or the whole collection may not be needed at all, and only a subset. It is common practice to provide a way to page through a collection to limit the size of a response, and request only a subset, or page, explicitly.

To specify a paged request, specify the maximal size of the response with the **pageSize** parameter, and the subset number with the **pageNumber** parameter:

``` no-highlight
Request:
GET /products?pageNumber=2&pageSize=30

Response:
 Headers:
    Link: <http://foo/?page=2>; rel='self'
    Link: <http://foo/?page=3>; rel='next'
    Link: <http://foo/?page=1>; rel='prev'

 Body:
  [
    {
        "sku": "52f56f583004a2d134ddb393",
    },
    {
        "sku": "52f56f583004a2d134ddb394",
    },
    ...
]
```

The call does not fail if the **pageNumber** or **pageSize** parameters are not defined for a given request. Instead, default values are applied that are specific for the domain and do not cause performance issues. When marking a method as paged, provide paging information as part of the response. This is not specified in detail yet. The call returns a `400` response code if the arguments are syntactically incorrect. For example, if the **pageNumber** or **pageSize** values are negative or non-numeric. If the call is successful, a `200` response is returned with a Link header that includes a link to the current page, the next page, and the previous page.

The pages are marked accordingly with `rel self`, `rel next`, and `rel prev`. The link to the current page `self` is required and must be provided. The link to the next page `next` is optional. If not present, the next page is not available, and the current page is the last page of the result. The link to the previous page `prev` is also optional. If not present, the previous page is not available, and the current page is the first page of the result.

### RAML modeling
Given a collection resource with a GET method, such as the following:

``` no-highlight
...
/products:
  type: collection
  get:
    description: Gets all products
  post:
    description: Creates a new product
```

Specify the GET method as pageable by using the header, such as this public pattern already provided:

``` no-highlight
traits:
   - !include https://pattern.yaas.io/v2/trait-paged.yaml

/products:
  type: collection
  get:
    is: [paged]
    description: Gets all products
  post:
    description: Creates a new product
```
