---
title: Composite Key Resource
---

In a scenario where the client needs to access the resource directly by the composite key, it becomes part of the URL. For example, when `{code}` is the business ID of the resource and `{app}` is a key for the namespacing element of the business ID, the URLs look similar to the following:

``` no-highlight
POST /{tenant}/resource
GET /{tenant}/resource
GET /{tenant}/resource/{app}
GET /{tenant}/resource/{app}/{code}
PUT /{tenant}/resource/{app}/{code}
DELETE /{tenant}/resource/{app}/{code}
```

In a scenario where the client does not know the composite key of an entity, the **artificial ID** approach is used. For example, when `{id}` is a unique artificial identifier generated at resource creation, the URLs look similar to the following:

``` no-highlight
POST /{tenant}/resource
GET /{tenant}/resource
GET /{tenant}/resource?app=myApp&code=myCode
GET /{tenant}/resource/{id}
PUT /{tenant}/resource/{id}
DELETE /{tenant}/resource/{id}
```
