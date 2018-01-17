---
title: API Proxy
---

All services must use the API proxy on all systems, and all other services that use it. A service must not call another service directly using an internal URL, instead, it must use the external URL and fulfill all the authorization requirements. The proxy is the central security mechanism for YaaS to ensure authentication and authorization when calling resources from services.
