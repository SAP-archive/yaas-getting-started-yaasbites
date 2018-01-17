---
title: Rate Plan
---
#### Description

The rate plan defines the pricing and/or usage limits as it will appear for your package on the YaaS Market. It also forms part of the legal agreement when a customer subscribes to your package.

For commercial packages the rate plan defines what your customers pay for the services that are included in your package. You select one of the approved metrics and specify a price for the usage of that metric. You can also specify that a certain amount of usage is free.

Non-commercial packages (also known as *Beta*, or *Trial* packages) are always free of charge, so instead of prices, they contain limits. Here you also select one of the approved metrics, and define the amount of free usage for that metric. If a user exceeds the defined limit, the service will be blocked by the proxy.

#### Guidelines
The general model for pricing is pay as you go.
* Select one metric for each package (multiple metrics are not allowed).
* Billing occurs once a month in arrears and is based on usage. There are no upfront costs.
* Define a rate plan for each market, that is, one for the U.S., one for Germany, and so on.
* The services of your package should not have any dependency on the metrics. That is, it should be possible to change the metric and pricing of your rate plan, without having to redeploy the service.


When setting the price for your package, consider the following:
* Does your price for the chosen metric represent value to the customer?
* Will they be able to easily predict their monthly costs?
* Does your pricing scale with increased usage? Meaning, will your pricing still represent value to customers, even if the amount that they use dramatically increases?
* Is your pricing competitive? Are there similar packages on the YaaS Market that are cheaper, or more expensive?

For more information on pricing, see <a href="/internal/tools/billing/index.html">Setting Up Billing for a Package</a>.
