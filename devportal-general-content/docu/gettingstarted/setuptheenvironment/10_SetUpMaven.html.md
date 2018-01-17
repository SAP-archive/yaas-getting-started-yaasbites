---
title: Set Up Maven
---

The YaaS Service SDK uses Maven to resolve all additional software dependencies that are necessary to create,
build, test, run, and debug your new service. Install Maven before you develop services using
the YaaS Service SDK.

Before you continue to the next steps of this tutorial, verify that your Java and Maven installations are operational by executing this command:
``` no-highlight
mvn help:system
```
The output displays information about the Maven installation and the operating system environment.
At the end of the output, you see this information:
``` no-highlight
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
<br>
<div class="panel note">
If you encounter errors instead, verify that your **JAVA_HOME** environment variable is set up correctly. If you encounter any network-related issues, follow the instructions in the next step.
</div>
