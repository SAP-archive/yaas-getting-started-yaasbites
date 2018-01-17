---
title: Localization
---

The name or description of a product has multiple translations for different languages so that an Italian customer sees different product data than an English customer. Therefore, the client requests product data for a specific language and, optionally, has fallbacks applied in case a certain translation is not available. For instance, English is used as a fallback if the Italian translation is not available. Furthermore, the product data must be retrievable in multiple or all available languages, for example, if you want to display or edit all translations at once. Therefore, a non-existing translation is explicitly indicated instead of applying a fallback. These localized language values are managed in the API of a YaaS service in a specific way. In the following example, the resource **Product** has a localized attribute **name**. It first gets retrieved and modified by a front end displaying it in the current language, and then it is retrieved and modified by a back end operating on all available languages.

### Front-end retrieval
Always request a **Product** to have the value of a localized attribute in the best-matching language. Values in other languages are not needed. When calling the GET method without any additional headers, all translations are returned unless the service supports the default language concept. Then, it returns the data in a predefined language. For example, a GET request with no language specified looks similar to the following:

``` no-highlight
Request:
GET /products/123

Response:
  {
   "name" : "English Name"
  }
```

To specify a specific language, use the **Accept-Language** header with the desired language. For example, a GET request with a single language specified looks similar to the following:

``` no-highlight
Request:
  Accept-Language: pl
  GET /products/123

Response:
  {
   "name" : "Polska nazwa"
  }
```

To have fallback languages applied, specify a language list using the **Accept-Language** header, sorted by priority. Optionally, you can specify weightings. As a result, the best-matching language is determined separately for each attribute. A localized product description might be returned in a different language than the product name. There are two types of fallbacks, as described below:

**Automatic fallback** – Given the **Accept-Language** header is specified as: `en_US`.
If there are no translations for this specific language within the language family, then the fallback language is `en`. For example:
``` no-highlight
Request:
  Accept-Language: en_US
  GET /products/123

Response:
  {
   "name" : "en name translation"
  }
```

**Explicit fallback** – If more languages are specified that are not from the same language family, then the fallback language can be explicitly stated. For example:
``` no-highlight
Request:
  Accept-Language: en, pl
  GET /products/123

Response:
  {
   "name" : "Polska nazwa"
  }
```

### Front-end modification
When working with a storefront, a customer might create or edit a product review. Since this is always done with one language, there is no need to support multiple languages. For instance, modify a product name and call the PUT method without any additional headers. If no translations are available for a localizable field, the request is rejected unless a service implements a default language concept. With the default language ability, the PUT method replaces all the current localizations with the submitted value by default. When the PUT method is partially updated, then the additional language is added to the language map. It is also updated if the language code already exists. For example, a PUT request with no language specified looks similar to the following:

``` no-highlight
Request:
  PUT /products/123
  {
   "name" : "English Name"  // validation
    or service-specific handling such as assignment to a default language
  }
```

To specify a specific language, specify the **Content-Language* header with the desired language. For example, a PUT request with a single language specified looks similar to the following:

``` no-highlight
Request:
  Content-Language: pl
  PUT /products/123
  {
   "name" : "English Name"
  }
```

The same applies for POST requests.

### Back-end retrieval
Here,  you can operate on multiple languages with no fallbacks ever applied. It is common to pass values in multiple languages within a request and response. To operate on multiple languages, set the **hybris-languages** header. This indicates the data structure of the body is in a multi-language structure. The attribute type is a mapping of each language to a string instead of just a string. The **hybris-languages** header specifies a list of languages, or you can specify one language. No language fallback is applied for the header, so the response contains data only in the required language. For example, a GET request with one language specified looks similar to the following:

``` no-highlight
Request:
  hybris-languages: en
  GET /products/123

Response:
  {
   "name" : { "en": "English name" }
  }
```

A GET request with a list of languages specified looks similar to the following:

``` no-highlight
Request:
  hybris-languages: en, pl, it
  GET /products/123

Response:
  {
   "name" : { "en": "English name", "pl": "Polska nazwa", "it": null }
  }
```

To get the data in all languages, use the asterisk `*` character for the header value. Do not list the asterisk in combination with any languages, as that is not supported. For example:

``` no-highlight
Request:
  hybris-languages: *
  GET /products/123

Response:
  {
   "name" : { "en": "English name", "pl": "Polska nazwa" }
  }
```

### Back-end modification
When an administrator updates a product, the language map for a given attribute is provided in the request body. No additional headers are needed. If a PUT request replaces all languages, then all localizations for the provided attribute are replaced. If the request is a partial PUT, the new localized values are added to the localization map, or the values are updated. For example:

``` no-highlight
Request:
  PUT /products/123
  {
   "name" : { "en": "English name", "pl": "Polska nazwa" }
  }
```

The example above also works for a POST request that replaces all languages.

### RAML modeling
To specify that an endpoint supports localization as described in this document, first look at the original product API definition:

``` no-highlight
...
traits:
  - !include https://pattern.yaas.io/v2/trait-app-aware.yaml

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

Next, look at the related product JSON schema:

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
    }
  },
  "required":["sku"]
}
```

To have the product **name** localized to provide a different value depending on the language, indicate that it is using the **localized** schema. For example:

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
      "$ref":"localized"
    }
  },
  "required":["sku"]
}
```

Next, import the **localized** schema, and also define the endpoints as localized. For example:

``` no-highlight
...
schemas:
 - localized: !include https://pattern.yaas.io/v1/schema-localized.json

traits:
  - !include https://pattern.yaas.io/v1/trait-localized-retrieval.yaml
  - !include https://pattern.yaas.io/v1/trait-localized-modification.yaml
  - !include https://pattern.yaas.io/v2/trait-app-aware.yaml

resourceTypes:
  - !include https://pattern.yaas.io/v2/resource-type-element.yaml
  - !include https://pattern.yaas.io/v1/resource-type-collection.yaml

/products:
  type: collection
  is: [appAware]
  get:
    is: [localizedRetrieval]
    description: Gets all products
  post:
    is: [languageModificationAware]
    description: Creates a new product

  /{productId}:
    type: element
    is: [appAware]
    get:
      is: [localizedRetrieval]
      description: Gets a product
    put:
      is: [languageModificationAware]
      description: Updates a product
    delete:
      description: Deletes a product
```
