---
type: Extensibility
title: 'Extending YaaS Data Structures'
---

Due to the microservice nature of YaaS, each data item is owned by its specific service. Because you don't have direct influence on the implementation of the service, extending data items is not as simple as changing the service's source code. Instead, you can add individual fields to the data item. For example, to use the Product service and a product data item to store additional data, you can:

* Store the new data along with the actual product data item, managed in the Product service. Select this extension mechanism if the new data is a core product data item, such as a foreign ID field or the dimensions of a given product. In YaaS, there is a <a href="/services/document/latest/index.html#Mixins">mixin</a> concept used to enable those extensions. Therefore, it is important that the microservice with the extensible data supports the mixin feature.
* Store the new data item in a new service and link it to the actual product data item. Select this extension mechanism if the new data is not a core product data item, such as ratings and reviews of a specific product. If the new data serves different usage characteristics, implement a dedicated service dealing with the new data item by following the rules described in the <a href="/overview/extensibility/index.html#ExtendingtheBuilder">Extending the Builder</a> section.

To ensure that your service is compliant with the YaaS mixin concept, you should follow these rules:

* The **metadata** and **mixins** fields at level 1 in your service's API JSON request and response documents are reserved keywords and must follow the rules in the mixins documentation.
* The **metadata** and **mixins** fields must be accepted on every request and stored in your underlying data store, such as the Document Repository.
* The **metadata** and **mixins** fields must be populated on every response your service sends.
