---
title: Searching
---

When calling a GET on a collection resource, all entities provided by this endpoint are returned. Limit the size of the result list by filtering the entities with the **attribute** criteria.

To specify the *attribute** criteria for a method returning a collection of entities, provide the **q** query parameter. This parameter specifies the criteria for attributes that the entities must match to be selected. The value is an [ElasticSearch](http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/query-dsl-simple-query-string-query.html) simple query. For example, a product with a name and description for the **sku** field can have a search criteria applied in the following manner:

``` no-highlight
Request:
GET /products?q=name:"Fancy Shoe"

Response:
  {
    [
      {
       "sku": "1",
       "name": "Fancy Shoe",
       "size": "5"
      },
      {
       "sku": "2",
       "name": "Fancy Shoe",
       "size": "3"
      }
    ]
  }
```

``` no-highlight
Request:
GET /products?q=name:"Fancy Shoe" size<4

Response:
  {
    [
      {
       "sku": "2",
       "name": "Fancy Shoe",
       "size": "3"
      }
    ]
  }
```

Requests return a `400` error code if the given criteria is not translated into a proper query. Provide detailed explanations in the error payload on how to fix the query itself. The explanations are also consistent across all APIs.

### RAML modeling
Given a collection resource with a GET method, such as the following:

``` no-highlight
...
/products:
  type: collection
  get:
    description: Gets all products
```

Specify the GET method as searchable by applying a query parameter, such as this public pattern that is already provided:

``` no-highlight
traits:
   - !include https://pattern.yaas.io/v1/trait-queryable.yaml

/products:
  type: collection
  get:
    is: [queryable]
    description: Gets all products
```
