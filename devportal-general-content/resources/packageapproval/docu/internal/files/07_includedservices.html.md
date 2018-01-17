---
title: Include Your Services
---
#### Description

In the Builder, select the services that are included in your package. The services that you include here are automatically listed on your product details page, and are linked to the documentation (which is mandatory) that you create in the Dev Portal.

#### Guidelines

* Minimum: One service per package
* Maximum: Five services. If you need to exceed this limit, then it is possible that your package should be split up, so please contact <a href=mailto:martin.gurney@sap.com>Martin Gurney</a>.
* Give your services a meaningful name and basepath, so that a customer can easily understand just what your service does. The service names are also displayed on the YaaS Market on the product details page. For more information, see [API Guidelines - Naming Services](/tools/apiguidelines/index.html#NamingServices).
* The name should not reveal anything about our department structure.
* Service names are unique within one organization. Be specific about what the services do, and do not use generic terms, such as "item".
* Ensure your services do not pose a security risk or slow down performance on the platform.
* Services should be resilient to failures from underlying components. You can use other services, but even if these fail, yours should not. For more information, see our guiding principles, <a hef=https://devportal.yaas.io/overview/yfactors/y-factors.html>The Y Factors</a>.
* APIs should focus on use cases, not expose the data model. Think about what your users need. For example, your user would probably prefer to show customers that a product is now available in another color, so give them that.
* Although it might be stating the obvious, make sure your service works! Test your services before you send your package for approval.
* Read and follow the [API Guidelines](/tools/apiguidelines).

Remember, you are creating something that will actually be used by others, so keep it simple. Check out our tips in <a href=http://de.slideshare.net/AndreaStubbe/apis-the-pretty-face-of-your-microservices>APIs - the pretty face of your microservices</a>.
