---
title: Resource Reference
---

There are different ways to reference resources in payloads, as described in this document. Most often you find referenced resources that are managed by the same service. They imply the kind of resource they are by the attribute name, such as `"product":"5"`. Sometimes, more general references are used, such as references to items in a cart, and you cannot imply the resource type easily.

### Typed local references
When referencing a resource managed by the same service, usually the referenced resource type uses an explicit attribute name. For example:

``` json
"wishlist" :
{
  "id" : "5",
  "wishlistItems" : ["34","67"]
}
```

The referenced resources have an explicit attribute name of **wishlist items**. If these wishlist items are a resource of the same service, the association is automatically explicit. This scenario is sufficient in most cases, however, the referenced resource type _should_ be specified in the related RAML definition, and always be explicit as possible. For example:

``` json
"properties" :
{
  "wishlistItems" :
  {
     "type" : "string",
	 "description" : "array of 'wishlistItem' resources managed by the 'wishlist' service identified by 'id'.
  }
}
```

### Typed global references
When referencing resources with well-formulated attribute names _not_ managed by the same service, then you usually use an attribute name explicitly indicating the referenced resource type. For example:

``` json
"wishlist" :
{
  "id" : "5",
  "products" : ["34","67"]
}
```

Because it is not clear where the referenced resource can be found,  you _must_ specify the referenced resource in the RAML definition. For example:

``` json
"properties" :
{
  "products" :
  {
     "type" : "string",
	 "description" : "array of 'product' resources managed by the 'product' service identified by 'code'.
  }
}
```

It is expected that the products referenced by the **products** attribute are managed by the SAP Hybris Products service and the references are resolved within that service. However, to be explicit, you could use the `[YRN](#ResourceIdentifier)` to establish the references. For example:

``` json
"wishlist" :
{
  "id" : "5",
  "products" : ["urn:yaas:hybris:product:product:myShop;34","urn:yaas:hybris:product:product:myShop;67"]
}
```

The referenced resource turns into a generic reference that the user can interpret, so products listed here could be managed by different product services.

### Untyped references
In some scenarios, you want to establish references where the referenced resource type can not be fixed, because it can be of different types. For example, given an authorization service with authorization rules assigned to the resources:

``` json
"authorizationRule" :
{
  "id" : "5",
  "resource" : "XX"
}
```

The definition does not specify what resource type is referenced, by design. All the information must be encoded in the reference value. For this scenario, the `[YRN](#ResourceIdentifier)` is the perfect choice and _must_ be used because it contains all the required data in a concise way. For example:

``` json
"authorizationRule" :
{
  "id" : "5",
  "resource" : "urn:yaas:hybris:product:product:myShop;67"
}
```
