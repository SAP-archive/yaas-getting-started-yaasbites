---
title: Operations
---

To move items, use [action links](http://bill.burkecentral.com/2010/03/25/modeling-operations-in-rest/). For example, to move an item from one wishlist to another:

``` no-highlight
Request:
  POST /wishlists/1/items/2/moveToWishlist

Response:
  {
    "wishlistId" : 3
  }
```
