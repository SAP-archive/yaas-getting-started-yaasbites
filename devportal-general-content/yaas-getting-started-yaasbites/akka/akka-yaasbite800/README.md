# Goal

In this Bite we want to **start using micro-services** that are available from the [Yaas Market Place](https://market.yaas.io/beta). In particular we will modify our persistence logic to use the Document Service, rather than in-memory logic with slick and h2. If you browse the [Yaas Market Place](https://market.yaas.io/beta), you will find the [Document Service](https://devportal.yaas.io/services/document/latest/index.html) in the [Persistence (Beta) Package](https://market.yaas.io/beta/all/Persistence-(Beta)/9b174e06-9283-4c47-8d16-6eded2ac840a). Its documentation will tell you that callers to this service **require** 3 scopes to successfully invoke it: hybris.document<sub>view</sub>, hybris.document<sub>manage</sub>, and hybris.document<sub>admin</sub>.

# Changes needed in the Yaas Project and the microservice

## in the new yaas project

### subscribe to the [Persistence (Beta) Package](https://market.yaas.io/beta/all/Persistence-(Beta)/9b174e06-9283-4c47-8d16-6eded2ac840a), so that the API Proxy will (once we have a suitable access-token), allow us to call the Document Service

### register our microservice in YaaS

### add a YaaS Client to our YaaS Service, that has the rights to request and be given the new scopes.

## add logic in the microservice to accquire **access-tokens** and invoke **Document Service**

### we will have 3 new Actors

1.  actors.OAuthActor

    1.  for getting the oauth token

2.  actors.TipsDocumentActor

    1.  for doing document operations

3.  actors.TipsRepoActor

    1.  a parent actor that triggers various operations and sends messages to **TipsDocumentActor**

# Steps

## cd to project-directory **akka-yaasbite800**

## adjust the name in the **manifest.yml** to make it unique

## Determine where the microervice will be deployed in the cloud: We need the location of our microservice in the cloud, but it is not yet ajusted and ready to be deployed.

### to get around this, we will skip running tests for now

### run the following commands

1.  activator dist

2.  cf push

3.  **cf apps** take a note of the URL(1) to where the package has been deployed

## in your Yaas project

### Subscribe to the Persistence Package: Add a subscription to the [Persistence (Beta) Package](https://market.yaas.io/beta/all/Persistence-(Beta)/9b174e06-9283-4c47-8d16-6eded2ac840a)

### Wire the microservice into YaaS

1.  Create a new Service in the YaaS Builder and specify the deployed URL (1) in the service's Source URL field

2.  Be sure you use the **https** prefix (protocol) in the Source URL field

### Add a YaaS Client to your YaaS Service, and add the three new scopes to its list of Required Scopes.

1.  This will allow us to use it's credentials to acquire access-tokens with those scopes

### Be sure to **deploy** the YaaS Service with the Deploy Button in the YaaS Service's page(it is not enough just to wire it up)

## change configurations

### In your Micro-service: Search for **NEEDS<sub>ADJUSTING</sub>** and modify those entries to match your YaaS Project settings

## try running the tests

### activator clean compile test

### If these pass, your service is succesfully calling the Deployent Service.

### Debug through the tests to find how the access-tokens are acquired, and how the document service is being called.

## Repackage and redeploy

### activator dist

### cf push

## Try hitting your website. You should see that the application works as before but is now using the Document Service for its persistence.