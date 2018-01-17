---
title: Request Headers
---

Always forward **hybris-request-id** header and hybris-hop header to subsequent HTTP calls. The headers allow the monitoring toolset to correlate single HTTP requests. For more information, see the [Hybris Headers](/tools/apiguidelines/index.html#HybrisHeaders) document in the API Guidelines.

### Multi-tenant services
Multi-tenant services are required to assure that the tenant of the request (**hybris-tenant** header) conforms to the resource-owning tenant. Also, the resource-owning tenant is expressed in the resource URL. For an example, refer to the [Multi tenancy](/services/document/latest/index.html#Multitenancy) document in the Document service.
