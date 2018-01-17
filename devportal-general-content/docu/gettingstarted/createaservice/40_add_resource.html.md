---
title: Add a New Resource
---

A wishlist service is not functional without the ability to add or view the products in the wishlist. This section describes how to add a resource representing a list of wishlist items to the Wishlist service.

``` no-highlight
/wishlists/{wishlistId}/wishlistItems
```

### Add the new resource in RAML

The Service SDK provides an API Generator that makes it easy to implement application logic for the service's API definitions. Once the RAML definition is modified, the necessary classes and code snippets are generated. The new resource represents the list of **wishlistItems** that are saved on a wishlist. For example, add the following lines at the bottom of the RAML definition:

`src/main/webapp/meta-data/api.raml`:
``` no-highlight

    /wishlistItems:
      type: collection
      is: [yaasAware]
      get:
        description: Gets all wishlist items
        responses:
          200:
            body:
              application/json:
      post:
        description: Adds a wishlist item
        responses:
          201:
            body:
              application/json:
```

<div class="panel note">
The number of spaces in front of the resource is important, as it represents where the resource is in the hierarchy of resources. In this case, it is a child of the **{wishlistId}** resource, so the indent is two spaces more than its parent. The use of the Tab key is not permitted inside a RAML file, so use spaces instead.
</div>

When you add the **/wishlistItems** sub-resource to the **/wishlists/{wishlistId}** resource, you can also define the GET and POST methods with responses 200 and 201. These responses are explained in the RAML specification.

In the example above, the [`type: collection`](https://pattern.yaas.io/v1/resource-type-collection.yaml) refers to the **collection** resource-type, as defined in the [YaaS RAML Patterns](/tools/ramlpatterns/index.html). Adding this type to a resource adds default definitions of the HTTP methods. They are usually applied to a collection REST resource, such as getting a list of items, and posting one single item. This resource also automatically searches for JSON Schemas that have a name matching the resource name. With the GET method, it is the singularized resource name, and with a name such as **wishlistItem**, the resource applies the schema on the POST method. There is already a `wishlistItem.json` file defined, but not a `wishlistItems.json` file.

### Define the corresponding JSON Schema type

The new JSON Schema is very similar to the existing `wishlists.json` file. Create a new file in the `src/main/webapp/meta-data/schemas` directory and name it `wishlistItems.json`. Paste the following content inside:

`src/main/webapp/meta-data/schemas/wishlistItems.json`:
``` json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title":"Collection of Wishlist items",
  "type":"array",
  "items":
  {
    "$ref":"wishlistItem"
  }
}
```

Include the new JSON file in your RAML file definition. Open the `api.raml` file and input the following line, along with the other **!includes**, below the schemas keyword:

`src/main/webapp/meta-data/api.raml`:
``` no-highlight
  - wishlistItems: !include schemas/wishlistItems.json
```

<div class="panel note">
There are two spaces at the beginning of the line in the code line above. This is required for the RAML to compile.
</div>


### Adjust the Java code

After you finish extending the RAML API definition, go to the project directory and run `mvn servicegenerator:generate-service -Dservice-generator.output.types=DTO,RESOURCE -Dservice-generator.output.overwrite=true` to trigger the generation of new resource interfaces and DTOs. If you try to build the project `mvn clean install`, it fails to build due to compilation errors, displaying the following error messages:

<pre><code>
[ERROR] \Projects\wishlist\src\main\java\com\sample\wishlist\api\impl\DefaultWishlistsResource.java:[14,8] com.sample.wishlist.api.impl.DefaultWishlistsResource is not abstract and does not override abstract method postByWishlistIdWishlistItems(com.sample.wishlist.api.param.YaasAwareParameters,java.lang.String,com.sample.wishlist.api.dto.WishlistItem) in com.sample.wishlist.api.WishlistsResource
</code></pre><br/>

<div class="panel tip">
If you get an error stating <code>Address is already in use</code>, make sure you have stopped the local Jetty instance. If you get other errors during compilation, ensure that your RAML file does not have extra spaces or tabs in it.
</div>

The errors above are expected. To resolve them, investigate the code by opening your IDE. Refresh your project to pick up new versions of the generated files, and view the content of the **DefaultWishlistsResource** class. Notice that the interface is extended by the two methods that you just defined in the RAML file, but the implementation class is not updated accordingly. This is what is causing the errors.

This behavior is expected. You have not instructed the API Generator to replace the implementation classes, such as the **DefaultWishlistsResource**. The same goes for the corresponding **jUnit** test class, such as **DefaultWishlistResourceTest**. You would typically not want to overwrite them as they contain your business and testing logic. If the API definition of a service ever changes, you must do **one** of the following:

* **Option A:** Manually adjust the resource implementation to match the changed Java interface that it implements.
* **Option B:** Force the regeneration of all the files, by executing the `generate-service` goal without the output types specified: `mvn servicegenerator:generate-service -Dservice-generator.output.overwrite=true`.

<div class="panel warning">
If you use **Option B**, you lose all of your code, so make sure you back up that files first.
</div>

To fix the compilation errors in the example, let us follow **Option A**, and add the following two methods:

`src/main/java/com/sample/wishlist/api/impl/DefaultWishlistsResource.java`:
``` java
public Response getByWishlistIdWishlistItems(
          YaasAwareParameters yaasAware,
     String wishlistId) {
            return Response.ok()
                .entity(new java.util.ArrayList<>()).build();
}

public Response postByWishlistIdWishlistItems(
          YaasAwareParameters yaasAware,
      String wishlistId,
     WishlistItem wishlistItem) {
            return Response.created(uriInfo.getAbsolutePath())
                .build();
}
```

Remember to extend the tests, and add new test cases. Ideally, you also provide an implementation to these tests, and the remaining tests:

`src/test/java/com/sample/wishlist/api/impl/DefaultWishlistResourceTest.java`:
``` java
@Test
public void testGetByWishlistIdWishlistItems()
{
    final WebTarget target = getRootTarget(ROOT_RESOURCE_PATH).path("/wishlistId/wishlistItems");
    final Response response = target.request().get();
    Assert.assertNotNull("Response must not be null", response);
    Assert.assertEquals("Response does not have expected response code", Status.OK.getStatusCode(), response.getStatus());
}

@Test
public void testPostByWishlistIdWishlistItemsWithWishlistItem()
{
    final WebTarget target = getRootTarget(ROOT_RESOURCE_PATH).path("/wishlistId/wishlistItems");
    final com.sample.wishlist.api.dto.WishlistItem entityBody =
    new com.sample.wishlist.api.dto.WishlistItem();
    final javax.ws.rs.client.Entity<com.sample.wishlist.api.dto.WishlistItem> entity =
    javax.ws.rs.client.Entity.entity(entityBody,"application/json");
    final Response response = target.request().post(entity);
    Assert.assertNotNull("Response must not be null", response);
    Assert.assertEquals("Response does not have expected response code", Status.CREATED.getStatusCode(), response.getStatus());
}
```

Now, build your project again. You can also start up the service with the `mvn jetty:run` command and check that the new resource is visible. You have now finished adding a new REST resource to the API.
