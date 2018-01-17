---
title: Sorting
---

When calling a GET on a collection resource, a list of entities is returned, but the order of the collection is not defined by default. For an option to guarantee the order, specify an order criteria.

To specify an order criteria for a method returning a collection of entities, provide the sort query parameter. It specifies the attributes to sort as a comma-separated list. The default order direction is ascending, but this can be changed with the **desc** sort criteria. For example, a product with a name and description for the **sku** field can have a sort criteria applied in the following manner:

``` no-highlight
Request:
GET /products?sort=name

Response:
  {
    [
      {
       "sku": "2",
       "name": "a",
       "description": "text",
      },
      {
       "sku": "1",
       "name": "b",
       "description": "text"
      }
    ]
  }
```

``` no-highlight
Request:
GET /products?sort=description,name

Response:
  {
    [
      {
       "sku": "2",
       "name": "a",
       "description": "text",
      },
      {
       "sku": "1",
       "name": "b",
       "description": "text"
      }
    ]
  }
```

``` no-highlight
Request:
GET /products?sort=sku:desc,name

Response:
  {
    [
      {
       "sku": "2",
       "name": "a",
       "description": "text",
      },
      {
       "sku": "1",
       "name": "b",
       "description": "text"
      }
    ]
  }
```

The call ignores non-existing fields, as opposed to failing. Sorting for the same kind of data, such as literal, numeric, and domain specific types, works consistently across the API. For example, dates are not ordered the same way as textual fields. Sort the final result starting with the first column and ending with the last column, with decreasing priority.

### RAML modeling
Given a collection resource with a GET method, such as the following:

``` no-highlight
...
/products:
  type: collection
  get:
    description: Gets all products
```

Specify the GET method as sortable by applying a query parameter, such as this public pattern already provided:

``` no-highlight
traits:
   - !include https://pattern.yaas.io/v1/trait-sortable.yaml

/products:
  type: collection
  get:
    is: [sortable]
    description: Gets all products
```
