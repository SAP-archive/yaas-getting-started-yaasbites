---
title: Batch Processing
---

Batch processing is a standardized way of implementing batch operations in REST. The functionality of the `/categories/categoryId/productsBatch` endpoint enables you to add multiple products to one specific category with a single call. The new endpoint exists in parallel to the `/categories/categoryId/products` endpoint, which enables you to add one single product to one specific category. Batch-enabled REST endpoints follow a specific naming convention. The names are postfixed with the string `Batch`, resulting in the following pattern:

``` no-highlight
/{resource}Batch or /{resource}/{resourceId}/{subresource}Batch
```

For example, a request looks similar to the following:

``` no-highlight
Method: POST
Path: /categories/aCategoryId/productsBatch
Body: ['productId1', 'productId2', 'productId3']
```

The only body carried with the batch request is an array containing single batch items. In the given example, a single batch item is a simple product identifier. But, depending on the scenario, more complex objects are possible. The actual response to a batch request is more complex. For example:

``` no-highlight
Status: 200
Body: [{
  "status": 200,
  "headers": {
    "location": ["/categories/aCategoryId/products/productId1"]
  }
}, {
  "status": 200,
  "headers": {
    "location": ["/categories/aCategoryId/products/productId2"]
  }
}, {
  "status": 200,
  "headers": {
    "location": ["/categories/aCategoryId/products/productId3"]
  }
}]
```

A response can be split into two distinct parts:

- The general HTTP headers describing the overall status.
- The actual body containing the details for each single batch item.

Each single batch item's response follows the same pattern as defined by the non-batch operation, which helps minimize documentation efforts, increases API consistency, and consequently results in improved API usage simplicity.

### API modeling
Each single batch item's response is defined analogous to to the non-batch operation API, seen in the request and response of both carry arrays. The service implementation guarantees that the response for the request array item at position 0 goes into the response array item at position 0. This requirement makes the API easier to use because no unique IDs for each resource need to be contained in either the request or the response.

### Request and response items are linked
After processing the result for a request array, the item at position `i` needs to be written into the response array at position `i`.
The general HTTP headers are influenced by one of two conditions:

- All single batch operations are successful.
- A single, multiple, or all batch operations failed.

The following table summarizes which HTTP status code to return:

| Status | Description |
| - | - |
| `200 OK` | The batch request was well-formed. Each batch item was processed successfully. |
| `400 Bad Request` | The batch request was malformed. The server did not even start processing single batch items. |
| `403 Forbidden` | The batch request was well-formed but did not contain appropriate credentials. The server did not even start processing single batch items. |
| `500 Internal Server Error` | The batch request was well-formed, but an unforeseen issue occurred before any kind of processing started. |
| `500 Internal Server Error` | The server did start processing single batch items but at least one single batch item returned with an error code of `5xx`. |
| `400 Bad Request` | The server did start processing single batch items, but at least one single batch item returned with an error code of `4xx` and no `5xx` error occurred. |

A single batch request is not executed in one transaction. Do not expect any kind of rollback functionality, unless explicitly defined in the specific service documentation.
