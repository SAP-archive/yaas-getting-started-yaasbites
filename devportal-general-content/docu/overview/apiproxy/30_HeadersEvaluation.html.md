---
type: API Proxy
title: 'Headers Evaluation'
---

The following parameters are evaluated when granting access to YaaS resources:
<ul>
<li>**hybris-client** and the automatically-created value of **hybris-client-id** – These are the **Identifiers** of a client. In requests within a `$someproject` context such as, **hybris.tenant=$someproject**, this project must be the owner of the client, or be subscribed to any package that contains this client.</li>
<li>**hybris-scopes** – This is the list of the scopes granted to the client. The effective scopes are intersections of the following:
<ul>
<li>Client Credentials Grant flow – The requested scopes and the subscriptions-related scopes granted to the client by the tenant.</li>
<li>Implicit Grant – The requested scopes, the subscriptions-related scopes granted to the client by the tenant, and the scopes defined by roles assigned to the user.</li>
</ul>

If the evaluated scopes don't match the required scopes of the resource, the service returns a code `403` error response . For more information about authorization, see the <a href="/overview/security/index.html">Security</a> documentation.</li>
<li>**hybris-tenant** – This is the tenant (project). Use **hybris.no\_tenant** to obtain an access token outside the context of any tenant.
With the Implicit Grant and Resource Owner Password Credentials Grant, the **hybris-user** parameter also determines the user's permissions.</li>
<li>**hybris-user** – This is the authenticated user's email. The user must be a member of the tenant (project) to perform operations within the context of this tenant.</li>
<li>**hybris-user-id** – This is the authenticated user's identifier. The user must be a member of the tenant (project) to perform operations within the context of this tenant.</li>
</ul>
