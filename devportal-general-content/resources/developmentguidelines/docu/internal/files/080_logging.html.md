---
title: Logging
---

All applications must follow the logging standards defined in the [SDK documentation](/tools/servicesdk/index.html#Logging) and expose standard metrics, such as memory, operating system, and threading. Using a standardized format leverages the full-powered monitoring toolset.

Every log will will be enriched with the application name and version. The application name is taken from the cloudfoundry app name sourcing a log. The version will be set to *unknown*.

In many cases you might want to set the application name and version explicit, as the cloudfoundry application name might be different per deplyoment and you want to compare logs of different application versions. When using the [SDK toolset](/tools/servicesdk/index.html#Logging) you can set the name and version by simply setting these environemnt variables: *APP_NAME* and *APP_VERSION*
