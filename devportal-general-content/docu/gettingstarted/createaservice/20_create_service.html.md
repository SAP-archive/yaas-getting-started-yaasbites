---
title: Create a New Service
---

The YaaS Service SDK provides a [Maven archetype](/tools/servicesdk/index.html#TheArchetype) that easily generates a development project for your new REST service. The new service is based on JEE, Spring, and the Jersey framework, which is the reference implementation of the JAX-RS standard.

<div class="panel note">
Before you create a service, make sure your environment is set up correctly. For more information, see [Set Up the Environment](/gettingstarted/setuptheenvironment/index.html).</div>

### Generate the project

The following steps generate a new development project for the Wishlist service example. You can choose any name for the new project, but using the suggested names makes it easier to follow the rest of this guide.

1. In a terminal window, make or choose a directory to work in, and then set your working directory to there. The following steps create a separate sub-directory for the new service.
2. Run the following Maven command to create an initial project directory for the new service:
  ``` no-highlight
  mvn archetype:generate -U -DarchetypeGroupId="com.sap.cloud.yaas.service-sdk" -DarchetypeArtifactId=service-sdk-jersey-spring-archetype -DarchetypeVersion=RELEASE
  ```
3. When prompted, enter a **groupId** and an **artifactId** for the new project, such as the following:
  ``` no-highlight
  Define value for property 'groupId': : com.sample.wishlist
  Define value for property 'artifactId': : wishlist
  ```
4. When prompted for further input, press Enter to accept the defaults:
  ``` no-highlight
  Define value for property 'version':  1.0-SNAPSHOT
  Define value for property 'package':  com.sample.wishlist
  ```
<div class="panel note">
The following line in the final output: `[INFO] Using property: asyncApi = false` specifies that the generated service processes the requests synchronously, which is the default. To generate a service that processes the requests asynchronously, decline the default settings and specify `asyncApi = true` instead. Alternatively, invoke the archetype generation by adding the `-DasyncApi=true` option to the Maven call.</div>
5. Verify the summary of the input, and then press Enter to accept the input.

When the Maven execution completes, a confirmation message displays in the output:
<pre>
[INFO] ------------------------------------------------------------------------<br/>
[INFO] BUILD SUCCESS<br/>
[INFO] ------------------------------------------------------------------------<br/>
</pre><br/>

The Maven tool creates a project directory named `wishlist` for your new service. It also generates a structure of sub-directories and adds the initial configuration and code to them.

Now change to the project directory:
``` no-highlight
cd wishlist
```

<div class="panel note">
All subsequent command-line examples assume that you are working in the project directory.</div>

### Generate service code

In the source code, you can see the file **src/main/webapp/meta-data/api.raml**. It contains a sample
definition of the Wishlist service REST API. The next section explains more about RAML. To generate the
boilerplate Jersey code for the endpoints defined there, run the following:

``` no-highlight
mvn servicegenerator:generate-service
```

After performing this step, the folder **src/main/java** contains the generated resource interfaces as well as the DTOs. They belong to the **com.sample.wishlist.api** package and its subpackages. You do not have to change most of those files. The business logic goes inside the resource implementation classes located in the **com.sample.wishlist.api.impl** package. In this case, it is the **DefaultWishlistsResource**.

### Build and run the service

The newly generated project directory contains an example API definition for the Wishlist service. After you have generated the skeleton code, you can build and run the Wishlist service. The API service contains two endpoints, which serve as the basis for generating Jersey resources for the Wishlist service. To see what the newly generated code does, follow these steps:

1. To build the project, including compiling all sources, processing resources, and bundling the project, use the following Maven command:
  ``` no-highlight
  mvn clean install
  ```
2. The project is preconfigured for execution in an embedded Jetty Servlet container. To run the new service, run the
 following command:
  ``` no-highlight
  mvn jetty:run
  ```
3. To access the new service through a web browser, enter the service's default URL: <a href="http://localhost:8080" target="_blank">http://localhost:8080</a>.

  You are redirected to the built-in API Console of the service, which you can use to explore the service API. It is easy to send your own requests to the service right from your web browser.
  <br>
  <img src="img/New Service API Console.png" class="img-click-modal" vspace="20" alt="Screenshot of an API console of a service newly created from the hybris archetype.">

Congratulations, you just wrote and ran your first service in only a few minutes! Read on to find out on how you can examine the initial code of your new service, and also how to extend and modify your service's API.
