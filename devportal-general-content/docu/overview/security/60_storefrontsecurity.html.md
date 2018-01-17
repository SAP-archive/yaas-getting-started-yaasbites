---
type: Security
title: 'Storefront Security'
---

The storefront is a retail shop web application with products for purchase as a working demonstration of the YaaS APIs. Storefront security plays a substantial role in ensuring secure transactions. The following items are the key security features in storefront:

### Cross-site scripting (XSS)

**Helmet** is a code module loaded from the Node Package Manager (NPM). It includes these security settings which can be added to your site:
* **frameguard** – Denies a malicious attacker the ability to put site information into an iframe that could contain extra execution logic.
* **xssFilter** – Adds an **X-XSS-Protection** HTTP header to protect against cross-site scripting (XSS).

A custom wrapper with regular expression validators is also provided to ensure that certain input types have limited character sets in a directive called **y-input**. Those input types are URL, email, ID, name, password, description, date, and keys.

#### Angular security

Angular version 1.2 has <a href="https://docs.angularjs.org/api/ng/service/$sce">Strict Contextual Escaping (SCE)</a> enabled by default to help defeat XSS. SCE ensures that no HTML is executed as JavaScript. Therefore, use Angular version 1.2 or greater.

Angular also provides a mechanism to combat cross-site request forgery (CSRF or XSRF). For more information, see the <a href="https://docs.angularjs.org/api/ng/service/$http">AngularJS website</a>.

#### Headers

The **x-powered-by** header is disabled to hide the stack implementation details, which limits the exploitation of known vulnerabilities. For more information, see the <a href="https://www.npmjs.com/package/helmet">NPM website</a>.

#### Clickjacking

Configure your deployment HTTP server to send the **X-FRAME-OPTIONS** header to restrict others from hosting your site inside an iframe. For more information, see <a href="https://www.owasp.org/index.php/Clickjacking">OWASP Clickjacking</a>.

This is an example of a header that disallows the ability to inject the storefront into an iframe:
```
X-Frame-Options: DENY
```
This is an example of the header that blocks injection into inputs:
```
X-XSS-Protection:1; mode=block
```

### HTTPS and SSL certificates

YaaS APIs use HTTPS to encrypt HTTP transactions. Therefore, configure your domain to allow HTTP to be encrypted into a HTTPS session. Also, a forced redirect is applied for any HTTP request to be converted to a Secure Socket Layer (SSL) HTTPS session. To gain the chain of trust that is required for a browser to verify your website identity, purchase a certificate from an online certificate authority.
