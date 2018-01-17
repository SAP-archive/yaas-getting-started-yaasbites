---
title: Date and Time Attributes
---

As shown in the following audit, shopping, and administration use cases, it is important to maintain the creation and modification time of a resource:

- A product manager wants to see when a product was last modified.
- An audit person wants to see when a user was created.
- A customer wants to see when a product review was created.

### Naming date and time attributes
Since the date and time attributes of a resource affects different business roles, the data is part of the response body when querying a resource without the use of headers. The naming of the attributes is always in the following format:

- A verb in past tense and camel case, plus the word "At", such as: **createdAt**, **modifiedAt**, and **subscribedAt**.
- The value must be a UTC date and time in [ISO 8601](http://www.iso.org/iso/home/standards/iso8601.htm) format, such as: **yyyy-MM-dd'T'HH:mm:ss.SSSZ**. For example:
``` no-highlight
"2001-09-11T14:00:00.000Z"
```

A response body looks similar to the following:

``` no-highlight
Request:
  /products/{sku}

Response:
 Body:
  {
      "sku": "52f56f583004a2d134ddb393",
      "name": "blubb",
      "createdAt": "2014-08-18T15:43:00.000Z",
      "modifiedAt": "2014-08-18T15:44:00.000Z"
  }
```

### RAML modeling
If you have a product schema defined in a JSON file and referenced in a RAML definition, you can specify the product schema definition containing the creation and modification time. The following is an example RAML definition before adding the attributes to the product schema:

``` no-highlight
...
schemas:
  - product: !include product.json

/products:
  type: collection
  /{id}
    type: element
      get:
        description: Gets product
```

The following is an example RAML definition after adding the attributes to the product schema:

``` no-highlight
...
schemas:
  - product: !include product.json
  - createdAt: !include https://pattern.yaas.io/v1/schema-createdAt.json
  - modifiedAt: !include https://pattern.yaas.io/v1/schema-modifiedAt.json

 /products:
  type: collection
  /{id}
    type: element
      get:
        description: Gets product
```

The following is an example product schema after adding the attributes:

``` json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title":"Product",
  "type":"object",
  "properties":
  {
    "sku":
    {
      "type":"string"
    },
    "name":
    {
      "type":"string"
    },
    "createdAt":
    {
      "$ref":"createdAt"
    },
    "modifiedAt":
    {
      "$ref":"modifiedAt"
    }
  },
  "required":["sku"]
}
```

<div class="panel note">
Do not use <code>"type":"date"</code> in a JSON schema, as this is not supported by the specification. Instead, use <code>"type":"string","format":"date-time"</code>.
</div>
