# yaas-getting-started-yaasbites

## YaaS Bites

Welcome to [Yaas Bites](https://yaasbiteindex.cfapps.us10.hana.ondemand.com/)' github repository: small, focused coding exercises that:
- get a developer from 0 to [Yaas](https://www.yaas.io/) in a couple of days
- ensure a Developers' initial journeys into YaaS are successful, so that they come back encouraged and hungry for more.
- assume only that you know Java and basic Spring
- are ready for you to learn right now @ [Yaas Bites](https://yaasbiteindex.cfapps.us10.hana.ondemand.com/)

## What is YaaS?
[Yaas](https://www.yaas.io/) is a powerful framework that promises to change the face of e-commerce software development. YaaS Development is not trivial, and learning it can be confusing and frustrating; you need familiarity with many technologies and terminologies before things start to make sense (REST, Jersey, Cloud CLI, Manifest, RAML, Angular, Restangular, YaaS Builder, YaaS DevPortal, Scopes, Required-Scopes, Tenants, Packages, Subscriptions, Builder Modules, Proxies and more).

[Yaas Bites](https://yaasbiteindex.cfapps.us10.hana.ondemand.com/) explores ways to make learning YaaS easy and even fun, so that new developers immediately experience success in the YaaS landscape.

## YaaS Bites @ TechEd
YaaSBites also forms the basis of this [Tutorial Series](http://go.sap.com/developer/tutorials/yaas-getting-started.html) for SAP's [TechEd](http://events.sap.com/teched-global/en/home), taking place Sep 2016 @ Las Vegas.  This tutorial focuses on introducing developers to YaaS in 2 hours, and is found on the [SAP Tutorial Catolog here](http://go.sap.com/developer/tutorials/yaas-getting-started.html).  It takes you through the process of creating a YaaS project, exploring the default YaaS Storefront, extending the default Yaas Storefront with a micro service, creating your own storefront with your own YaaS project and also deploying a YaaS based microservice to the SAP Hana Cloud Platform. The list of tutorials series in a sequence is available below with a short description for each tutorial.


1. Getting Started with YaaS: You will get started with YaaS and create your own YaaS project. This project will be used for the creation of a storefront. You will learn about the Builder which is the back-office client of YaaS. You will also get familiar with the YaaS Market where you can subscribe to different packages of your choice.

2. Download and Run the Default YaaS Storefront: A YaaS Storefront is a customizable, feature-rich-online Storefront web interface, supported by a backing YaaS Project, whose purpose is to give end-users a smooth online store experience. It is based on Node.js, Angular and Restangular. In this tutorial, you will download and run a default YaaS Storefront.

3. Extend the YaaS Storefront's Functionality with a Micro Service: In this tutorial you will extend the Storefront using a micro service. The aim is to include a button in the Storefront's Product Details page that gives helpful Tips to the undecided purchaser, such as "This would add to your overall coolness", and "One word: No". To achieve this, you will run the Tips micro service locally and connect it to YaaS Storefront. The tips are communicated via REST calls to YaaS Storefront.

4. Use Your Own YaaS Project to Back your Storefront: By default, the StoreFront is wired to the YaaS Project default project (created by the StoreFront's own Team). This default project has already been wired up and populated with the Products (and Services) that you can browse. You will now use our own YaaS Project which you created earlier instead of the default one.

5. Deploy a Micro Service Built on YaaS on the SAP HCP: In this tutorial you will set up a cloud account on the HANA Cloud Platform through your YaaS project. You will then use the Tips micro service from previous tutorials. First you will deploy it and then run it on the cloud.

## Prerequisites
To follow YaaS Bites you need the following installed:
- **Maven 3.0** - so you can build the war files with mvn clean package etc. [here](https://maven.apache.org/index.html)
- **Git** - for cloning code [here](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- **Java 8 JDK** - to write and compile the example code: available at [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/)
- **Eclipse IDE or similar** [here](https://eclipse.org/downloads/)
