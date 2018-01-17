---
type: API Proxy
title: 'Rate Limiting'
---

Every reliable software ecosystem must be able to handle the incoming traffic that is greater than its processing abilities. Generally, when flooded with requests, the proxy performance may degrade. If it cannot immediately deal with the incoming requests, then they are placed in a queue. This increases the latency for requests until the service is completely overloaded. The clients start to receive the error codes of `502` or `503`, and the system requires a restart because it is impossible to send a successful API call.

Our mission is to maintain YaaS as a safe and stable environment. This is why we introduced the **rate limiting** mechanism to prevent the API Proxy from being affected by malicious or accidental requests that may harm the system.

There are two independent rate-limiting values:
+ **per tenant** - This value involves all requests per a specific hybris-tenant, across all tenants. When the limit is exceeded, the client receives an error code of `429` and an `insufficient_resources` error message. This means the client is sending excessive requests, and the number of requests needs to be managed.
+ **global** - This value takes into account all incoming requests. When the limit is exceeded, the client receives the error code of `503` and a `service_temporarily_unavailable` error message. This means that excessive or unnecessary calls are being made to the API Proxy.

When the API Proxy is under heavy load, it rejects some of the incoming requests with these errors. Still, the system processes as many calls as it is capable of, with minimum latency.

The rate-limiting values are not configurable for the users. However, the API Proxy mechanism is fully scalable and can adjust to the increased traffic that is not identified as malicious or harmful.

Make your service development more efficient and reduce the number of calls to the API Proxy with the following actions:
+ Create a test client and monitor the number of requests
+ Implement error handling
+ Cache results from previous calls
+ Plan and batch the requests.
