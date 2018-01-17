---
title: Currencies and Units
---

There are two schemas to express a value of a certain unit described below.

1. **The monetary amount schema**:
The purpose of the monetary amount schema is to express monetary value, such as a price, in a certain currency. The schema requires that both the amount and the currency attributes are set. The currency should conform to the [ISO 4217](http://www.iso.org/iso/home/standards/currency_codes.htm) standard, such as a JSON object that follows the schema:
``` json
"price" : {
    "amount": 40,
    "currency": "EUR"
}
```
2. **The quantitative value schema**:
The quantitative value expresses the amount of items, or the amount of a product in a given unit, such as kilograms or liters. The schema requires that only the value attribute is set. If the unit attribute is missing, it means that the value describes the number of items of the given product. This is equivalent of setting the **unit** to the value `C62`.
``` json
"quantity": {
    "value": 1
}
```
If the unit attribute is present, it expresses the unit in which the value is given. The value of the unit attribute should be one of the [UN/CEFACT Common Codes](http://www.unece.org/cefact/codesfortrade/codes_index.html). Below is an example JSON object that follows the schema:
``` json
"quantity": {
    "value": 1,
    "unit": "KGM"
}
```

### Referring to the JSON schemas
Use the above JSON schemas inside your own custom schemas. There are predefined URLs for the schema definitions:

1. [The monetary amount type](https://pattern.yaas.io/v2/schema-monetary-amount.json)
2. [The quantitative value type](https://pattern.yaas.io/v2/schema-quantitative-value.json)

Use the related URL in the JSON Schema to define an attribute to be of the **monetary amount type**:

``` json
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "type" : "object" ,
    "properties": {
        "price": {
            "description": "The price of the product",
            "$ref": "https://pattern.yaas.io/v2/schema-monetary-amount.json"
        },
        ...
   }
}
```

### RAML Modeling

To specify that an endpoint for products in the RAML definition supports these monetized amounts, see the following original product API definition:

``` no-highlight
...
traits:
  - !include https://pattern.yaas.io/v2/trait-yaas-aware.yaml

resourceTypes:
  - !include https://pattern.yaas.io/v2/resource-type-element.yaml
  - !include https://pattern.yaas.io/v1/resource-type-collection.yaml

/products:
  type: collection
  is: [appAware]
  get:
    description: Gets all products
  post:
    description: Creates a new product

  /{productId}:
    type: element
    is: [appAware]
    get:
      description: Gets a product
    put:
      description: Updates a product
    delete:
      description: Deletes a product
```

Also, the related product JSON schema:

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
    "price":
    {
      "type":"number"
    }

  },
  "required":["sku"]
}
```

To attach a currency to the product price, adjust the product schema by indicating that the **price** is using the **monetary amount** schema. For example:

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
    "price":
    {
      "$ref":"monetaryAmount"
    }
  },
  "required":["sku"]
}
```

To introduce the monetary amount schema to the RAML file, import the `schema-monetary-amount.json` schema, with the **monetaryAmount** name. For example:

``` no-highlight
...
schemas:
 - monetaryAmount: !include https://pattern.yaas.io/v2/schema-monetary-amount.json

traits:
  - !include https://pattern.yaas.io/v2/trait-yaas-aware.yaml

resourceTypes:
  - !include https://pattern.yaas.io/v2/resource-type-element.yaml
  - !include https://pattern.yaas.io/v1/resource-type-collection.yaml
```
