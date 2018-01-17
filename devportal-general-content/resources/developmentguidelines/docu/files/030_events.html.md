---
title: Events
---

Events must be named and documented consistently according to these guidelines. The message channels used to communicate specific events have topic owners, such as **hybris.customer** for the Customer service. Each topic owner can have multiple event types, described in the past tense, with a hyphen (-), such as **password-updated**. Therefore, name the specific event with a combination of the topic owner and the event type, such as **hybris.customer.password-updated**. The events created be each service are listed in the API Documentation by topic owner, such as <a href="/services/customer/latest/index.html#Events">Customer events</a>. All of the YaaS events are published by the PubSub service so that other services can consume them.

Read the [PubSub](/services/pubsub/latest/index.html) service and [Events](/services/events/latest/index.html) service documentation to learn how to publish, consume, and commit events.

