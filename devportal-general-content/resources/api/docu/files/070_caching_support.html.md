---
title: HTTP Caching Support
---

Enrich responses with **Cache-Control** headers. There is optional support using conditional caching based on an **ETag** (Entity Tag) or **Last-Modified** response headers.

HTTP caching occurs when a client stores local copies of resources for faster retrieval when the resource is requested again. When a service is serving resources, usually triggered by GET requests, it can attach cache headers to the response. These cache headers specify the desired cache behavior.

### Full caching - the **cacheable** trait
When an item is fully cached, the client may not contact the service and uses its own cached copy instead. To indicate that a resource is fully cacheable from the service side, it returns the **Cache-Control** header as part of the GET response. The header turns on the caching from the client side. For instance, when this header is sent and has a value that enables caching, a browser caches the file for as long as specified. Without this header, the browser re-requests the file on each subsequent request. For example, a **Cache-Control** header value looks similar to the following:

``` no-highlight
Cache-Control: public, max-age=3600
```

The **public** value indicates that the resource can be cached, not only by the end-user's client, but also by any intermediate proxies that serve other users. The **max-age** value sets a timespan for how long, in seconds, to cache the resource.

While the **Cache-Control** header turns on client-side caching and sets the **max-age** value of a resource, the **Expires** header is used to specify a specific point in time that the resource is no longer valid. To indicate that a resource is fully cacheable with either the **max-age** value or the **Expires** header, assign the [cacheable](https://pattern.yaas.io/v1/trait-cacheable.yaml) trait to your GET method.

### Conditional caching by time - the **timeCacheable** trait
Conditional requests are those where the client asks the service if the resource is updated. The client sends information about the cached resource it holds, and the service determines whether the updated resource is returned or if the client's copy is the most recent. If the latter, an HTTP status of `304 (not modified)` is returned.

A time-based conditional request is granted only if the requested resource has changed since the client's copy was cached. If the cached copy is the most up-to-date, then the service returns a `304` response code. To enable conditional requests based on time, the service attaches the **Last-Modified** response header to a response. For example:

``` no-highlight
Cache-Control: public, max-age=3600
Last-Modified: Fri, 19 Jun 2015 16:17:54 UTC
```

The next time the client requests this resource, it only asks for the contents of the resource if it is unchanged since the date appearing in the **If-Modified-Since** request header. For example:

``` no-highlight
If-Modified-Since: Fri, 19 Jun 2015 16:17:54 UTC
```

If the resource hasn't changed since `Fri, 19 Jun 2015 16:17:54 UTC`, the server returns with an empty body and the `304` response code. To indicate that a resource is conditional cacheable based on last modification time, assign the [timeCacheable](https://pattern.yaas.io/v1/trait-cacheable-time-based.yaml) trait to your GET method.

### Conditional caching by content - the **contentCacheable** trait
The ETag works in a similar way to the **Last-Modified** header except its value is a digest of the resource's contents, such as an MD5 hash. The Etag enables the service to identify if the cached contents of the resource are different than the most recent version. For example:

``` no-highlight
Cache-Control: public, max-age=31536000
ETag: "15f0fff99ed5aae4edffdd6496d7131f"
```

On subsequent client requests, the **If-None-Match** request header is sent with the **ETag** value of the last requested version of the resource. For example:

``` no-highlight
If-None-Match: "15f0fff99ed5aae4edffdd6496d7131f"
```

As with the **If-Modified-Since** header, if the current version has the same ETag value, indicating its value is the same as the client's cached copy, then an HTTP status of `304` is returned. To indicate that a resource is conditional cacheable based on last modification time, assign the [contentCacheable](https://pattern.yaas.io/v1/trait-cacheable-content-based.yaml) trait to your GET method.
