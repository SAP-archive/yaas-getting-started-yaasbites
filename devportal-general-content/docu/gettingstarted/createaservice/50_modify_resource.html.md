---
title: Modify Existing Resources
---

As mentioned earlier, the YaaS Service SDK provides reusable [RAML Patterns](../../tools/ramlpatterns/index.html). This section of the guide describes how to enhance the Wishlist service to make even better use of these patterns.

The [paged trait](https://pattern.yaas.io/v2/trait-paged.yaml) is designed to be used with collections that have many elements. With such collections, it is reasonable to browse them page by page, rather than requesting all of them at once. This trait adds two query parameters to the HTTP method, which specifies the desired page size and page number, as well as response headers which contain the links to the current, previous, and next items.

### Modify the RAML definitions

To add paging capability for the collection resources using the paged trait, open the RAML definition and add an `!include` for the paged trait:

`src/main/webapp/meta-data/api.raml`:
``` no-highlight
traits:
  - !include https://pattern.yaas.io/v2/trait-paged.yaml
```

Next, mark the collection type GET methods to be paged. Add `is: [paged]` to both **wishlists** and **wishlistItems**, as shown in these code snippets:

`src/main/webapp/meta-data/api.raml`:
``` no-highlight
/wishlists:
  type: collection
  is: [yaasAware]
  get:
    description: Gets all wishlists
    is : [paged]
    responses:
```
``` no-highlight
    /wishlistItems:
      type: collection
      is: [yaasAware]
      get:
        description: Gets all wishlist items
        is : [paged]
        responses:
```

Trigger the regeneration of the service code by running `mvn servicegenerator:generate-service -Dservice-generator.output.types=DTO,RESOURCE -Dservice-generator.output.overwrite=true`.

### Adjust the Java code

If you try to rebuild the project by running `mvn clean install` at this point, the build fails with the following error messages:

<pre><code>
[ERROR] \Projects\wishlist\src\main\java\com\sample\wishlist\api\impl\DefaultWishlistsResource.java:[18,8] com.sample.wishlist.api.impl.DefaultWishlistsResource is not abstract and does not override abstract method getByWishlistId(com.sample.wishlist.api.param.PagedParameters,com.sample.wishlist.api.param.YaasAwareParameters,java.lang.String) in com.sample.wishlist.api.WishlistsResource
[ERROR] \Projects\wishlist\src\main\java\com\sample\wishlist\api\impl\DefaultWishlistsResource.java:[24,9] method does not override or implement a method from a supertype
</code></pre><br/>

Look at the code again, but first, refresh the project in your IDE so that the newly generated files are included. Then, go to the **DefaultWishlistResource** class, and compare it with its interface, **WishlistsResource**. The GET methods for the collection resources in the interface have an additional parameter:

``` java
..., @javax.ws.rs.BeanParam @javax.validation.Valid final com.sample.wishlist.api.param.PagedParameters paged);
```

Examine the **PagedParameters** class. It has been generated based on the paged trait, and it contains getters and setters for **pageSize** and **pageNumber**. Adjust the methods in the implementing class to also include the paged parameter. That fixes the compilation errors in the **DefaultWishlistResource** class.

<div class="panel warning">Do not copy any annotations that appear in the interface method definition into the implementation class. Due to the way Jersey is implemented, if you include even one annotation, all the annotations on the interface stop having any effect.</div>

Inside the implementations of the updated GET methods, use the paged parameter to get the values of the **pageSize** and **pageNumber** query parameters that are supplied by the user. You can use these values later to limit the number of items returned by doing an appropriate query to the database. This is, however, out of scope of this guide.

Build the project again using the `mvn clean install` command. Run the service `mvn jetty:run` and then look at the API Console, which displays the two new query parameters for **pageSize** and **pageNumber**. You enhanced your API by adding paging capability.
