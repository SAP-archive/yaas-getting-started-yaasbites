---
title: 'YaaS Bites Essentials'
service: 'YaaS Bites Essentials'
---
<style type="text/css">
 button {
 background:none!important;
 border:none;
 padding:0!important;
 font: inherit;
 cursor: pointer;
 color: #0486e0;
 text-align: left;
 }
 code { color: #0486e0; background:none; }
 .panel { padding: 0.5em; }
</style>
<p><a name="Yaasbite0"></a></p>
<p>The <strong>YaaS Bites</strong> enable software developers to go from zero-to-YaaS in a couple of days. They are small, focused, incremental coding exercises. Each bite has code, guidelines, and screencasts to ensure your initial journey into YaaS is successful and productive. The bites on this page are essential for learning how to code YaaS solutions. You can find additional bites in the <a href="../yaasbitesoverview/index.html">YaaS Bites Overview</a>.</p>
<h3>Index</h3>
<ul style="margin-bottom: 0px;">
<li><a href="#Yaasbite0">YaaS Bite: Deploy, call and debug your first web service</a></li>
<li><a href="#Yaasbite1">YaaS Bite: Take a web service to the cloud</a></li>
<li><a href="#Yaasbite2">YaaS Bite: Code a CRUD web service</a></li>
<li><a href="#Yaasbite3">YaaS Bite: Code a multi-tenant CRUD web service</a></li>
<li><a href="#Yaasbite4">YaaS Bite: Understand YaaS security: tokens, scopes, and the API Proxy</a></li>
<li><a href="#Yaasbite5">YaaS Bite: Code RAML files</a></li>
<li><a href="#Yaasbite6">YaaS Bite: Understand the Implicit Grant Flow</a></li>
<li><a href="#Yaasbite7">YaaS Bite: Build Packages and Builder Modules</a></li>
<li><a href="#Yaasbite8">YaaS Bite: Call other web services</a></li>
<li><a href="#Yaasbitepubsub">YaaS Bite: Use the PubSub service</a></li>
<li><a href="#Yaasbite9">YaaS Bite: Use Hystrix for resilience</a></li>
<li><a href="#YaasStoreFront">YaaS Bite: Extend the storefront</a></li>
<li><a href="#Yaasbite10">YaaS Bite: Use the YaaS web service SDK</a></li>
</ul>
<h3>Terminology and Notes</h3>
<ul style="margin-bottom: 0px;">
<li>You should view these bites on large screens as the layout of the bites is suboptimal on small displays.</li>
<li><img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" />: The durations given beside each bite are estimates.</li>
<li><strong>⓪ ① ②</strong>: Encircled numbers are used to label a concept in a bite, that is then referred to later on in the same bite, using the same encircled number.</li>
<li>Terminology: The YaaS Landscape has its own set of terms, including: package, client, service, project, and more. As these terms often mean other things to developers, YaaS entities are prepended with YaaS, for example "YaaS package", "YaaS client", "YaaS service".</li>
<li>The screencasts are silent and show users working through respective bites. They are not stand-alone tutorials, but are there to add clarity, should something be unclear.</li>
<li>These bites assume you are using Chrome, but any modern browser with developer-tool support is appropriate.</li>
<li>The deployment steps in these bites are based upon <a href="https://docs.cloudfoundry.org/" target="_blank">Cloud Foundry</a>, but you are free to use any PaaS offering you like.</li>
<li>Subtle references to the cultural routes of our YaaS engineers, who come from all over the world, are included in the screencasts of these bites contain. At present there is a Bayerisch, or Bavarian theme. But fear not, new content is coming. Double-click the screencasts to expand them to full screen.
<p style="margin: 0px;" align="middle"><img style="margin-top: 0px; margin-bottom: 0px;" src="images/MB-Tracht_paar.jpg" alt="Bayerisch Tracht" width="100"/></p>
</li>
</ul>

<h3>YaaS Bite: Deploy, call, and debug your first web service <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 1h</h3>
<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Compile, run, and debug a small REST web service and accompanying front-end client.</li>
<li>Familiarize yourself with the main tools commonly used for web service development.</li>
</ul>
<br />

<br />Before you start, make sure you meet the prerequisites and have cloned the source code as described in the <a href="../yaasbitesoverview/index.html" target="_blank">Overview</a>.
<h4>Compile and run a web service locally</h4>
<ol>
<li>Navigate to the <code>(yb)/essentials/yaasbite100</code> folder. This folder contains a web service and accompanying front-end client.</li>
<li>Compile and package the code into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Execute the WAR file: <button data-clipboard-text="java -jar target/yaasbite1-1.0-SNAPSHOT.war">java -jar target/yaasbite1-1.0-SNAPSHOT.war [Copy Me]</button></li>
<li>Open the URL: <button data-clipboard-text="http://localhost:8080/greeting">http://localhost:8080/greeting [Copy Me]</button> in a browser window. You see a page with JSON content similar to: <code>{"id":1,"content":"Greetings from Bayern, Most Honorable User!"}</code>.</li>
<li>In the browser, click <strong>View</strong> > <strong>Developer</strong> > <strong>Developer Tools</strong>, to open Chrome's Developer Tools Window, in which you can observe the HTML traffic.</li>
<li>Also call the web service with the <button data-clipboard-text="http://localhost:8080/greeting?name=Lancelot">http://localhost:8080/greeting?name=Lancelot [Copy Me]</button> parameter, and confirm you get the response: <code>{"id":2,"content":"Greetings from Bayern, Most Honorable Lancelot!"}</code>.</li>
<li>Stop the WAR file with the <strong>ctrl-c</strong> command.</li>
</ol>

<table width=100%><tr><td width="33%"></td><td width="33%" align=middle><%- @partial('video-kaltura', {video_id:'1_bqfy6a7v'}) %>
<br><a href="https://www.youtube.com/watch?v=30IkgAe7pFw:" target="new">Bayern Schuhplattler Music</a></td><td></td></tr></table>

<h4>Debug the web service in Eclipse</h4>
<ol>
<li>Prepare this Maven project for Eclipse with the command: <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. In Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import the Maven project.</li>
<li>In Eclipse, select <strong>Window > Show View > Problems</strong> and verify no errors are listed.</li>
<li>Find the file <code>GreetingController.java</code> and place breakpoints in the <code>greeting</code> method.</li>
<li>Find the file <code>SanityTest.java</code> in Eclipse, right-click on it, and select <strong>Debug As a JUnitTest</strong>.</li>
 <li>Debug through the code to understand the following: <ol type="I" style="margin-bottom: 0px;">
 <li>The test uses Spring's <code>TestRestTemplate</code> to call <a href="#" onClick="return false;">http://localhost:8080<strong>/greeting</strong></a>.</li>
<li>Spring routes this call to the method or endpoint annotated with <code>@RequestMapping( "<strong>/greeting</strong>" )</code>.</li>
<li>Spring marshalls and unmarshalls the data between Java and JSON automatically. Read more on Spring, JSON, and Rest on the Spring <a href="https://spring.io/guides/gs/rest-service/" target="new">website</a>. </li>
</ol>
<li>Stop the debugger before proceeding to the next step.</li>
</ol>

<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_q0d6ohat'}) %></td><td></td></tr></table>

<h4>Debug the live web service remotely</h4>
In addition to running and debugging services directly in Eclipse, you can debug "live" web services, running outside of Eclipse.
<ol>
<li>Execute the WAR file in debug mode: <button data-clipboard-text="java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar target/yaasbite1-1.0-SNAPSHOT.war">java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar target/yaasbite1-1.0-SNAPSHOT.war [Copy Me]</button></li>
<li>In Eclipse, select <strong>Run</strong> > <strong>DebugConfigurations</strong> > <strong>RemoteJavaApplication</strong> > <strong>New</strong>.</li>
<li>Specify the same port (5005) and click <strong>Debug</strong>. The debugger for Eclipse launches and attaches to the running web service.</li>
<li>Verify that you hit any breakpoints you place in the web service's code, when you access the live web service's endpoints via the front-end client at <button data-clipboard-text="http://localhost:8080/greeting">http://localhost:8080/greeting [Copy Me]</button> and <button data-clipboard-text="http://localhost:8080/greeting?name=Lancelot">http://localhost:8080/greeting?name=Lancelot [Copy Me]</button>.</li>
</ol>
<p>Keep your Eclipse Debugger session active and your web service running, to also hit break points when you use the cURL command, as described next.</p>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_ss615bgz'}) %></td><td></td></tr></table>
<h4>Using Postman and cURL</h4>
Chrome's <a href="https://www.getpostman.com/" target="_blank">Postman</a> and the command line tool <a href="http://curl.haxx.se/docs/manual.html" target="_blank">cURL</a> are useful for manually sending REST calls to a web service. If you do not yet have cURL installed <a href="http://curl.haxx.se" target="_blank">download it now</a>. Use Chrome's Developer Tools to observe some of the REST requests made from your front-end client to the web service, and replicate some of those calls manually using Postman and cURL. For example:
<ol style="margin-bottom: 0px;">
<li>Run <button data-clipboard-text="curl http://localhost:8080/greeting">curl http://localhost:8080/greeting [Copy Me]</button> to replicate a browser's POST request.</li>
<li>Run <button data-clipboard-text="curl -i http://localhost:8080/greeting?name=Lancelot">curl -i http://localhost:8080/greeting?name=Lancelot [Copy Me]</button> to include the field <code>name</code>, and to request headers in the reply.</li></ol>

<h4>Before going further</h4>
<ol style="margin-bottom: 0px;">
<li>Study and understand the code in this bite.</li>
<li>Understand that this bite has both a web service and a separate front-end client. Often, you deploy a web service separately to the client, but in this case, they are in the same WAR file.</li>
<li>The web service does not "know" that the client is in the same WAR file. For all the web service knows, the client is on another machine, developed by other developers. This topic becomes highly relevant when addressing security in a later bite.</li>
<li>Use Chrome's Developer Tools to watch and understand the HTTP traffic traveling between the front-end client and the web service back end. You need a firm understanding of what is happening here before going further.</li>
</ol>
<hr />
<p><a name="Yaasbite1"></a></p>
<h3>YaaS Bite: Take a web service to the cloud <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 2h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Set up a cloud account.</li>
<li>Use Cloud Foundry's CLI to deploy your web service and to view any logs it generates.</li>
<li>Perform your first successful steps in the cloud.</li>
</ul>
<br /> Should you already be a member of a YaaS organization, and have a cloud-deployment process in place, you can skip straight to <a href="#AdjustDeployRunInTheCloud">Adjust, deploy, and run in the cloud</a>.
The next steps describe how to set up an account on <a href="https://docs.cloudfoundry.org/" target="_blank">Cloud Foundry</a>.
But you can choose which ever <a href="https://en.wikipedia.org/wiki/Platform_as_a_service#Providers" target="_blank">PaaS offering</a> you prefer.

<h4>Set up an account on the SAP Cloud Platform</h4>
Activate a free account on the SAP Cloud Platform, and push a web service to it:

<li>SAP employees can log in to their account at <a href="https://cloudplatform.sap.com">https://cloudplatform.sap.com</a>, and non-employees can sign up for a free account and log in at <a href="https://cloudplatform.sap.com/try.html">https://cloudplatform.sap.com/try.html</a>.</li>
<li>The SAP Cloud Platform offers a number of regions for deployment. When you first access your account, specify the required region.</li>
<li>Each region has a unique API endpoint, described on the<a href="https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/350356d1dc314d3199dca15bd2ab9b0e.html"> Regions and Hosts</a> page. For example, the API Endpoint for Europe (Frankfurt) running on AWS is <code>https://api.cf.eu10.hana.ondemand.com</code>. Next, provide this endpoint when setting up your Cloud Foundry CLI.</li>
<li>The SAP Cloud Platform provides a helpful UI for deploying and monitoring web services. In addition, Cloud Foundry offers a Command Line Interface (CLI). You can use either the UI or the CLI.</li>

<h4>Set up your Cloud Foundry CLI</h4>
Set up your Cloud Foundry CLI to run <code>cf</code> commands from the command line:<br />
<ol>
<li>To download and install the CloudFoundry CLI , follow the <a href="https://docs.cloudfoundry.org/cf-cli/install-go-cli.html" target="_blank">Installing the cf CLI</a> instructions on the website.</li>
<li>Verify a successful installation with the <code>cf -v</code> command, which returns the version number.</li>
<li>Point your Cloud Foundry CLI to the SAP Cloud Platform endpoint with the <code>cf api <i>yourEndpoint</i> </code> command. For example:
<code>cf api https://api.cf.eu10.hana.ondemand.com</code>.
</li>
</ol>

<a name="AdjustDeployRunInTheCloud"></a>
<h4>Adjust, deploy, and run in the cloud</h4>
<ol>
<li>Familiarize yourself with the <strong>manifest.yml</strong> file. This file instructs Cloud Foundry where and how to deploy the WAR file. For more information, see <a href="https://docs.cloudfoundry.org/devguide/deploy-apps/manifest.html" target="_blank">Deploying with Application Manifests</a>.</li>
<li>Specify a unique value for the <strong>name</strong> attribute in the <strong>manifest.yml</strong> file. This appears in the URL to your WAR file in the cloud and should be unique, for example, <code>yourAppName</code> ⓪.</li>
<li>Build and package the WAR file again with this new name: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>To push the WAR file to the cloud, run <button data-clipboard-text="cf push">cf push [Copy Me]</button>. This command uses the information in <strong>manifest.yml</strong> to guide the deployment.</li>
<li>Run <button data-clipboard-text="cf apps">cf apps [Copy Me]</button>, to check that your WAR file is deployed. Take note of the URL ① where the web service is running, such as: <code>yourAppName.cfapps.us10.hana.ondemand.com</code></li>
<li>The default SAP Cloud Platform settings require the secure, HTTPS protocol. Therefore, you must use HTTPS for all calls to your deployed web services, and when accessing your website in the cloud at https://①.</li>
<li>Use the `cf` command <button data-clipboard-text="cf logs yourAppName">cf logs yourAppName [Copy Me]</button> to observe the logs from the cloud. Confirm that you can see the log output as expected from <code>GreetingController.java</code> when you hit the web service in the cloud at https://①.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_6ud6x34d'}) %></td><td></td></tr></table>
<hr />
<p><a name="Yaasbite2"></a></p>
<h3>YaaS Bite: Code a CRUD web service <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 1h</h3>
<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Compile, deploy, and run a small CRUD (create, read, update, and delete) web service and an accompanying front-end client.</li>
<li>Use some basic Angular and Restangular.</li>
<li>Understand the REST traffic.</li>
<li>Verify with a sanity test.</li>
</ul>

<h4>Compile and run the CRUD web service locally</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite200</code> folder.</li>
<li>Compile and package the bite into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button></li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>In Eclipse, select <strong>Window > Show View > Problems</strong> and verify there are no errors listed, and explore the code.</li>
<li>Run the web service locally: <button data-clipboard-text="java -jar target/yaasbite2-1.0-SNAPSHOT.war">java -jar target/yaasbite2-1.0-SNAPSHOT.war [Copy Me]</button></li>
<li>Access the front-end client at <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>, where you can try the ping link and CRUD options.</li>
<li>Use Chrome's Developer Tools to watch and understand the HTTP traffic traveling between the front-end client and the web service back end. You need a firm understanding of what is happening here before going further.</li>
<li>If you are not yet familiar with Angular and Restangular, study the code used in <strong>index.html</strong>, and these excellent <a href="https://www.youtube.com/watch?v=TRrL5j3MIvo" target="_blank">Angular</a> and <a href="https://github.com/mgonto/restangular/blob/master/README.md" target="_blank">Restangular</a> resources.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_ikm6ika2'}) %></td><td></td></tr></table>
<h4>Deploy and run in the cloud</h4>
<ol>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique, for example <code>yourAppName</code> ⓪.</li>
<li>Build the WAR file again with this command: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Push to the cloud with the following command: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>To check that the deployment is successful, and that the web service has started, use the <button data-clipboard-text="cf apps">cf apps [Copy Me]</button> command. Take note of the URL① where the web service is running, such as: <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code></li>
<li>Open the URL① in Chrome and view the REST Traffic that occurs when you perform the CRUD operations.</li>
<li>Use the <button data-clipboard-text="cf logs yourAppName">cf logs yourAppName⓪ [Copy Me]</button> to look at the logs that appear when you perform the CRUD operations.</li>
</ol>
<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" class="img-click-modal" src="images/bite2.png" alt="" width="200" /></p>
<h4>Before going further</h4>
<ol>
<li>Study and have a basic understanding of the code used:<br />
<ul style="margin-bottom: 0px;">
<li>Spring Web (in TipController.java)</li>
<li>Spring Boot (in Application.java)</li>
<li>Restangular calls with success and error handling (in <strong>index.html</strong>)</li>
<li>JUnit (in SanityTest.java)</li>
<li>Spring JPA, H2Datase (in <strong>pom.xml</strong>, <strong>TipsRepo.java</strong>, and <strong>Tip.java</strong>)</li>
<li>Logging (set in <strong>application.properties</strong> and used in <strong>TipController.java</strong>)</li>
</ul>
</li>
<li>Watch and understand the REST traffic requests and responses.</li>
</ol>
<hr />
<p><a name="Yaasbite3"></a></p>
<h3>YaaS Bite: Code a multi-tenant CRUD web service <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 2h</h3>

<h4>Objective</h4>
<ul style="margin-bottom: 0px;">
<li>Understand the meanings of multi-tenancy and multi-tenant.</li>
<li>See an example of a multi-tenant web service in action.</li>
</ul>
<h4>Compile and run the multi-tenant CRUD web service locally</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite300</code> folder.</li>
<li>Compile and package into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>In Eclipse, select <strong>Window > Show View > Problems</strong> and verify that you see no errors listed. Explore the code.</li>
<li>Run the web service locally: <button data-clipboard-text="java -jar target/yaasbite3-1.0-SNAPSHOT.war">java -jar target/yaasbite3-1.0-SNAPSHOT.war [Copy Me]</button></li>
<li>Hit the website at <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>.</li>
<li>Perform the standard CRUD options on the web page, using various tenants (for example, TheAmazingCompany and TheEvenBetterCompany).</li>
<li>Observe how <span style="text-decoration: underline;">each tenant's data is kept separate from other tenants' data</span>. This is the essence of multi-tenant software: One tenant cannot read, modify, or delete tips that belong to another tenant. In this bite, this is implemented by including the tenant name in the URL, which then gets picked up in <code>TipController</code> as the parameter <code>@PathVariable String tenant</code>. This is just one way to support multi-tenancy.</li>
<li>In this example, the <strong>Get all tips</strong> method has a corresponding link in the front end that retrieves data from all tenants.</li>
<li>In a Chrome browser, click <strong>View</strong> > <strong>Developer</strong> > <strong>Developer Tools</strong> and observe the REST calls being made behind the scenes.</li>
</ol>
<h4>Deploy and run in the cloud</h4>
<ol>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique, for example <code>yourAppName</code> ⓪.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Check that the deployment was successful using the following command: <button data-clipboard-text="cf apps">cf apps [Copy Me]</button>. Take a note of the URL① where the web service is running, such as: <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code></li>
<li>In a Chrome browser, access the website URL①.</li>
<li>Look at the REST Traffic that occurs when you perform the CRUD operations and understand how the tenant data is kept separate.</li>
<li>View the logs that appear when you perform the CRUD operations: <button data-clipboard-text="cf logs yourAppName">cf logs yourAppName [Copy Me]</button> ⓪.
</li>
</ol>
<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" class="img-click-modal" src="images/bite3.png" width=200 alt="Log output from the Tips Service" /></p>
<h4>Optional: problem to solve</h4>
<ul style="margin-bottom: 0px;">
<li>Find at which length a tenant name causes SQL errors to occur, and modify the code to avoid these errors.</li>
</ul>
<hr />
<p><a name="Yaasbite4"></a></p>
<h3>YaaS Bite: Understand YaaS security: tokens, scopes, and the API Proxy <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 4h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Understand how Security is enforced and supported in YaaS.</li>
<li>Learn the various YaaS entities related to security including: tokens, scopes, required scopes and API Proxy.</li>
<li>See tokens and scopes in action with your web service.</li>
<li>Understand all elements in this reference diagram:
</li>
</ul>
<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" class="img-click-modal" src="images/YPLSecurity2.png" alt="YaaS entity relationships" width="250px" /></p>
<h4>Compile, run, and deploy the web service and client</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite400</code> folder. The code for this bite has a web service and front-end client, written to demonstrate YaaS security. The first step is to get that web service running.</li>
<li>Compile and package the bite into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>In Eclipse, select <strong>Window > Show View > Problems</strong> and verify there are no errors listed, and explore the code.</li>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique, for example <code>yourAppName</code> ⓪.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>. If you see an "exceeded memory" error, delete one of the other web services you pushed to your cloud space, using the <code>cf delete</code> command, and then push your web service again.</li>
<li>Use the <button data-clipboard-text="cf apps">cf apps [Copy Me]</button> command to verify the web service is running. Take note of the URL① where the web service is running, such as: <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code></li>
<li>Verify that you can reach the URL① in a Chrome browser through HTTPS.</li>
</ol>

<h4>The API Proxy: Identify the caller</h4>
Currently, the web service doesn't know the caller's identity. In many cases, the identity is determined by the front-end web pages, or other web services.
In YaaS, a caller identifies itself with an access token, which is a string similar to 024-ca055b94-7e26-4e82-b0fe-7ea95b98a2a7 that the caller embeds in the header of each HTTP request sent.
The header looks similar to the following:
<code>Authorization: Bearer 024-ca055b94-7e26-4e82-b0fe-7ea95b98a2a7</code>.
<br /><br/>
In YaaS, the <strong>API Proxy</strong> is a proxy server which validates the access token included inside an HTTP request, before either sending the request on to its intended web service (endpoint), or rejecting it.
The API Proxy needs to identify:
<ul>
<li>The address of your web service</li>
<li>Any conditions of the HTTP request it needs to reach its intended endpoint</li>
</ul>
These are addressed in turn next.
<h4>Registering your web service in YaaS</h4>
Register your web service in a <strong>YaaS service</strong> which resides in a <strong>YaaS project</strong>. A YaaS project resides in a <strong>YaaS organization</strong>.

<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" class="img-click-modal" src="images/YaaSEntitiesOPSS.png" alt="YaaS entities" width="125" /></p>
<ol>
<li>Open your YaaS organization and select the YaaS project you created earlier.</li>
<li>Within your YaaS project, click <strong>+Service</strong> to add a YaaS service. </li>
<li>Enter your cloud URL① in the field <strong>Source URL</strong>. You must use the HTTPS protocol.</li>
<li>Click <strong>Deploy</strong> to activate this YaaS service.</li>
<li>Note the Generated Proxy URL ② that the YaaS builder assigns to your YaaS service: such as <a href="https://api.eu.yaas.io/yaasgulps/sdf/v1" href="#" onClick="return false;">https://api.eu.yaas.io/yaasgulps/sdf/v1</a>.</li>
<li>The Generated Proxy URL points to the root of your web service, so call the <strong>yourServiceProxyURL/tips</strong> endpoint. For example: <a href="https://api.yaas.io/yaasgulps/sdf/v1/tips" href="#" onClick="return false;">https://api.yaas.io/yaasgulps/sdf/v1/tips</a> ③.</li>
<li>Wait a few minutes for the proxy address to activate. Then verify that when you access the endpoint ③ in Chrome, you do not get a <code>404</code>, but do get the message <code>Unauthorized: Bearer TOKEN is missing</code>. This shows that the API Proxy is doing its job - blocking any calls intended for your web service that do not have a valid access token in the header. You can still access your web service directly at its own cloud URL ①, but this is not the URL you share with or permit from others.</li>
<li>Next, you acquire an appropriate access token. Then you contact your service both directly, and by using the API Proxy, with and without an access token.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_ad5553kr'}) %></td><td></td></tr></table>
<h4>Adjust the URLs in your client</h4>
For this bite to work, the client <strong>index.html</strong>, needs to know the three locations where it can access your web service: locally, in the cloud, and in the cloud through the API Proxy.
<ol>
<li>Search for "NEEDS_ADJUSTING" in <strong>index.html</strong>, to find the three URLs that need adjusting to match your scenario, and make the adjustments:
<ol style="margin-bottom: 0px;" type="i">
<li><code>LOCAL_URL</code> is okay as-is, unless you changed the ports from the defaults.</li>
<li><code>CLOUD_URL</code> must match your web services URL ①.</li>
<li><code>SERVICES_API_PROXY_URL</code> must match your Generated Proxy URL ②.</li>
</ol>
</li>
<li>Rebuild your WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Redeploy to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
</ol>

<h4>Get an access token for your front-end</h4>
If you want your front-end client to make REST calls that successfully reach your web service via the API Proxy, you need to get an access token. To generate an access token, you need to send a <strong>Client ID</strong> and <strong>Client Secret</strong> to the <a href="https://devportal.yaas.io/services/oauth2/latest/index.html" target="_blank">OAuth2 service</a>. Client IDs and Client Secrets belong to YaaS clients, which you create within a YaaS project.<br />
<ol>
<li>Open your YaaS project.</li>
<li>Click <strong>+Client</strong> to create a new YaaS client. For now, you don't need to select any scopes.</li>
<li>Save your YaaS client.</li>
<li>Click <strong>Show</strong> in the Client Authorization section of your YaaS client page, and copy the values of the <strong>Client ID</strong> and <strong>Client Secret</strong> ④ that belong to, and uniquely identify, this YaaS client.</li>
<li>Your web service should now be running in the cloud. In addition, run your web service and site locally: <button data-clipboard-text="java -jar target/yaasbite4-1.0-SNAPSHOT.war">java -jar target/yaasbite4-1.0-SNAPSHOT.war [Copy Me]</button></li>
<li>Access the local instance of your web service's front-end: <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>.</li>
<li>Enter the <strong>Client ID</strong> and <strong>Client Secret</strong> ④ you retrieved. Leave the <strong>Requested Scope</strong> field empty.</li>
<li>Request a YaaS token using the link in your web service's front-end.</li>
<li>Study the token's details that are then displayed in your front-end, which should contain:
<ol style="margin-bottom: 0px;" type="i">
<li>The access token itself.</li>
<li>A tenant, on <strong>whose behalf</strong> you intend to make calls using the access token. By default, the tenant is the YaaS project to which the identified YaaS client belongs. When you start wiring more web services together, you'll see that you can specify another tenant when requesting the token. Just remember, the tenant does not necessarily identify <strong>who makes</strong> a call, but instead <strong>on whose behalf</strong> you make a call. This is critical to understand.</li>
<li>Scopes, or special permissions, granted to the access token, which in this case is empty.</li>
</ol>
</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_2sogczs0'}) %></td><td></td></tr></table>

<h4>Call the web service with the access token</h4>
<ol style="margin-bottom: 0px;">
<li>Now that you have entered an access token in your front-end, your UI should have expanded to show:
<ol style="margin-bottom: 0px;" type="i">
<li>Details of the token</li>
<li>The CRUD interface</li>
<li>Radio buttons to call the web service: locally, in the cloud, in the cloud via the API Proxy</li>
<li>Radio buttons to include or exclude the token in the HTTP requests</li>
</ol>
</li>
<li>Try to call the web service using all of the variants listed and study the resulting REST Traffic.</li>
<li>When you ping the web service using the API Proxy with a token included in the header, note that the API Proxy converts that token into a set of request headers that the <strong>TipController</strong> picks up. In particular, note <code>hybris-tenant</code>, which the API Proxy sets to the token's tenant.</li>
<li>Understand that the API Proxy does not allow any calls through unless you include the token. Additionally, you cannot access the method <code>sensitiveMethodForVIPsOnly</code> because you are missing a certain restricted scope in your token. These are addressed next.<br />
</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_0wc5bjwo'}) %></td><td></td></tr></table>
<h4>Scopes and required scopes</h4>
When you request a token, you can also specify a list of scopes that you want the token to contain. Scopes are just strings that denote permissions, and each YaaS service is free to create any number of scopes. For example, according to the <a href="https://devportal.yaas.io/services/document/latest/index.html#" target="_blank">Document service</a> documentation, calls to the Document service require a token with one or more of the scopes <strong>hybris.document_view</strong>, <strong>hybris.document_admin</strong>, and <strong>hybris.document_manage</strong>, depending on the call you want to make.<br />
You could not call <code>sensitiveMethodForVIPsOnly</code> because you did not have the required scope in your access token. You can locate the respective method in TipController. Examine that method and you can see that the call was rejected because the token did not contain the scope <strong>hybris.tips_vip</strong>, which is required in the access tokens of callers to this method. <br />
<ol>
<li>To allow your YaaS client to aquire a token with this new scope, you need to do two things:
<ol style="margin-bottom: 0px;" type="i">
<li>Add a new scope <strong>hybris.tips_vip</strong> to the list of the YaaS service's own scopes.</li>
<li>Add this new scope to the YaaS client's list of <strong>Required Scopes</strong>.</li>
</ol>
</li>
<li>Navigate to your front-end and use your YaaS client's credentials to acquire a token with the new scope <strong>hybris.tips_vip</strong>.</li>
<li>Verify that your browser can now call <code>sensitiveMethodForVIPsOnly</code> with this new token, and it does not return an error code as before.</li>
</ol>
<p>Understanding scopes is critical to using YaaS successfully. Read through this section and repeat the instructions until you are confident that you understand the concepts discussed. The Dev Portal also contains a comprehensive discussion of <a href="https://devportal.yaas.io/overview/security/index.html" target="_blank">YaaS' security model</a>.</p>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_izef2ubq'}) %></td><td></td></tr></table>

<hr />
<p><a name="Yaasbite5"></a></p>
<h3>YaaS Bite: Code RAML files <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 2h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Understand what RAML files are, and how they are used to specify REST APIs.</li>
<li>Understand how to test, and work with RAML files.</li>
</ul>
<h4>Background</h4>
The Dev Portal explains <a href="https://devportal.yaas.io/tools/apiguidelines" target="_blank">why and how YaaS uses RAML</a> to describe web service APIs. In this bite, you explore a small RAML file and accompanying tests that validate the RAML's syntax and confirm that calls from the test methods to the web service match the RAML definition exactly. When you create a web service later using the <a href="#Yaasbite10">YaaS SDK</a>, you learn that the SDK takes a RAML file as its starting point, or source of truth, and generates an entire skeleton Java/Jersey web service (including endpoint, tests, and DTOs), all extrapolated from the <strong>api.raml</strong> file. If you are completely new to RAML, a good resource for learning the basics is the <a href="http://raml.org/developers/raml-100-tutorial" target="_blank">raml.org tutorials</a>.
<h4>Validate the RAML syntax with tests</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite500</code> folder.</li>
<li>Compile and package the WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>In Eclipse, select <strong>Window > Show View > Problems</strong> and verify there are no errors listed, and explore the code.</li>
<li>Find the RAML file <strong>api.raml</strong>, and look at how the RAML content describes the REST API for TipController in a concise syntax.</li>
<li>Use the <a href="https://github.com/mulesoft/api-console" target="_blank">MulesSoft API Console</a> to display the RAML file in a more interactive manner, and to see the API Console embedded as a frame in <strong>index.html</strong>.</li>
<ol style="margin-bottom: 0px;" type="i">
<li>Run the web service locally: <button data-clipboard-text="java -jar target/yaasbite5-1.0-SNAPSHOT.war">java -jar target/yaasbite5-1.0-SNAPSHOT.war [Copy Me]</button>.</li>
<li>Hit the front-end at <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>.</li>
<li>Explore the RAML API Console that appears below the CRUD interface. This API Console is the same as the one used to document the YaaS web service APIs at <a href="https://devportal.yaas.io/services/" target="_blank">devportal.yaas.io/services</a>.</li>
</ol>
<li>Explore the <strong>SpringTest.java</strong> file, which shows one way to validate your RAML, as well as how to validate that the calls you are making in your tests cover all of the API described in the RAML file.<br>
</li>
</ol>
<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" width=200 class="img-click-modal" src="images/yb5raml.png" alt="RAML files" /></p>
<hr />
<p><a name="Yaasbite6"></a></p>
<h3>YaaS Bite: Understand the Implicit Grant Flow <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 4h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Understand what the Implicit Grant Flow is and how it allows you to keep the <strong>Client Secret</strong> secret</li>
<li>Understand the main steps the Implicit Grant Flow follows.</li>
</ul>
<h4>Background</h4>
In previous bites, you retrieved a token by providing <strong>Client ID</strong> and <strong>Client Secret</strong> to the OAuth2 service. This is an important way to request tokens, but it requires using the <strong>Client Secret</strong>, which is supposed to be kept ... secret. So, while you can use this approach to request tokens between web services on the back end/server side, you need a different approach that does not require the <strong>Client Secret</strong> when you request a token from a front-end client (for example a web page). This second approach is called the <strong>Implicit Grant Flow</strong>. <br />Below you can see that when you use the Implicit Grant Flow, you redirect visitors who are accessing your front-end, to a login page where they must enter their YaaS account credentials. Once validated, the flow redirects them again to your front-end, but this time with an access token embedded in the URL. Your front-end code can then extract the access token and use the token for its calls.
<h4>Sign in with Implicit Grant flow</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite600</code> folder.</li>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique.</li>
<li>Compile and package the WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Find and take a note of the URL ① where your front-end is running in the cloud.</li>
<li>Register your web service in YaaS:
<ol style="margin-bottom: 0px;" type="i">
<li>In your YaaS project, create a new YaaS service, and point that YaaS service to your web service's cloud URL ①.</li>
<li>Click <strong>Deploy</strong> to active the proxy address(es) that the API proxy reserves for your service.</li>
<li>Take a note of the proxy URL ② generated for your new YaaS service.</li>
</ol>
</li>
<li>Create a new YaaS client for your YaaS service:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>In the Builder, locate your YaaS service and click <strong>+Client</strong> to add a YaaS client to this.</li>
<li>Take a note of the <strong>Client ID</strong> ③ of this new YaaS client. You do not need the <strong>Client Secret</strong>.</li>
<li>The Implicit Grant Flow logic needs to know the URL to which the user should be redirected upon successful authentication. In this case, that URL should be the <strong>index.html</strong> of your service on the cloud: ①/index.html, such as <a href="https://yaasbite6.cfapps.us10.hana.ondemand.com/index.html" href="#" onClick="return false;">https://yaasbite6.cfapps.us10.hana.ondemand.com/index.html</a>. Add this address to the empty list of Redirect URIs in your YaaS client.</li>
</ol>
<li>Search for "NEEDS_ADJUSTING" in the bite's code, and make these adjustments:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>Adjust the <code>SERVICES_API_PROXY</code> to point to the proxy for your YaaS service ②.</li>
<li>Adjust the <code>SERVICES_CLOUD_URI</code> to point to your web service's address in the cloud ①.</li>
<li>Adjust the <code>Client ID</code> to your YaaS client's <strong>Client ID</strong> ③.</li>
</ol>
<li>Find the Angular method <code>loggingIn</code> and study what that method does:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>When you access <strong>index.html</strong> in a browser for the first time, the <code>loggingIn</code> method redirects to the <a href="https://devportal.yaas.io/services/oauth2/latest/index.html" target="_blank">OAuth2</a> endpoint.</li>
<li>The OAuth2 endpoint opens a pop-up window asking the user to sign in with their YaaS credentials.</li>
<li>You passed a <strong>redirect_uri</strong> parameter to this endpoint that specifies your homepage and your client's <strong>Client ID</strong>. If the parameters passed do not match your client's <strong>redirect_uri</strong> and <strong>Client ID</strong>, the Implicit Grant flow fails, even if the username and password are valid.</li>
<li>Note that you did not include the <strong>Client Secret</strong> parameter anywhere, which is a good thing, and a feature of the Implicit Grant flow.</li>
<li>If the username and password are authenticated, the OAuth2 endpoint redirects to the <strong>redirect_uri</strong> parameter you specified, and appends to that the value of the <strong>access_token</strong></li>
<li>The <code>loggingIn</code> method is invoked again, but this time, because the URL includes the access token, the JavaScript follows a different branch in the source code; the web service retrieves the access token from the URL, and can use the token to make calls to your YaaS service's proxy URL.</li>
</ol>
<li>Try out the Implicit Grant Flow:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>Package the code: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>).</li>
<li>In a Chrome browser window, access your website's URL ①. This invokes the <code>loggingIn</code> javascript method.</li>
<li>Be aware that the browser may be blocking a pop-up window that should now appear. If so, adjust your browser settings to allow pop-ups for the site.</li>
<li>After successfully signing in, you should see a valid access token in the URL</li>
<li>Find the javascript that then extracts that token, to then use to perform basic CRUD operations.</li>
<li>Using the Implicit Grant flow, you received a token with the required scopes, without having to provide the <strong>Client Secret</strong> anywhere.</li>
<li>When you examine the call you made to the OAuth2 service from <strong>index.html</strong>, you can see that the client requests the <strong>hybris.tips_vip</strong> scope, but the token did not acquire the scope. This is because your YaaS web service and client do not list the scope.</li>
</ol>
<li>For more practice, add the <strong>hybris.tips_vip</strong> scope to your YaaS service and to the list of required scopes in your YaaS client. When you sign in again, your token should acquire the scope <strong>hybris.tips_vip</strong>, allowing you to call <code>sensitiveMethodForVIPsOnly</code> from the front-end.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_17rx84fl'}) %></td><td></td></tr></table>

<hr />
<p><a name="Yaasbite7"></a></p>
<h3>YaaS Bite: Build Packages and Builder Modules <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 4h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Understand what YaaS Packages and Builder modules are, and how they are created and used.</li>
<li>The following reference diagram, may help you when working through this bite.</li>
</ul>
<p style="margin: 0px;" align=middle><img style="margin-top: 0px; margin-bottom: 0px;" class="img-click-modal" src="images/YPL2.png" alt="YaaS entity relationships" width="250px" /></p>
<h4>Background</h4>
Once you have developed a YaaS service, you might want to share it with your colleagues. To do this, you can wrap the service into a YaaS package. A YaaS package is uniquely identified by its own <code>Version ID</code>, a string similar to <code>57da987b2b1876901dc3e26f</code>. If you supply this <code>Version ID</code> to other developers, they may subscribe their own YaaS projects to your YaaS package by clicking a "+ PRIVATE PACKAGE" button and adding that <code>Version ID</code>. Once subscribed, those developers can call and utilize your YaaS service.<br /><br /> In addition to including YaaS services, a YaaS package can also include Builder modules, which provide a back end, or administrative, user interface for your service. When other users subscribe to your YaaS package, they can access this UI via a new menu entry that appears in their own YaaS builder.<br /> You can create Builder modules from scratch using the YaaS Builder SDK CLI, which you can download on the <a href="https://devportal.yaas.io/tools/buildersdk/index.html#download-the-builder-sdk-cli" target="_blank">Dev Portal</a>. In this bite, you create a Builder module from existing example code instead of from scratch.
<h4>Create a Builder module, register it in YaaS, and add it too to your YaaS package</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite700</code> folder, which features a heavily stripped-down Builder module for the Tips service.</li>
<li>This Builder module simply lists the current tips. Find the corresponding RESTangular call.</li>
<li>Search for and modify all entries marked with "NEEDS_ADJUSTING".</li>
<li>Rebuild the code: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Push it to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Use <button data-clipboard-text="cf apps">cf apps [Copy Me]</button> to show the location where the web service is deployed ①. The location looks similar to: <code>yaasbite7bm.cfapps.us10.hana.ondemand.com</code>.</li>
<li>Confirm that the Tips service from the YaaS Bite <a href="#Yaasbite2">Code a CRUD web service</a> is deployed and running in the cloud, then add some tips to it.</li>
<li>Create and register a Builder module in YaaS that provides a back end user for the Tips service.
<ol style="margin-bottom: 0px;" type="i">
<li>In your YaaS project, select <strong>Builder Modules</strong> from the menu, and create a new one.</li>
<li>In the <strong>Module Location</strong> add the URL of your Builder module's JSON file. This is equal to the URL ① <code>/builder/module.json</code>, similar to: <code>https://yaasbite7bm.cfapps.us10.hana.ondemand.com/builder/module.json</code>.</li>
<li>Enable <strong>Use this Builder Module for my project</strong> by using the toggle button.</li>
<li>Save the Builder module, and a new menu entry appears in your YaaS Project.</li>
<li>Select the new menu entry and <strong>Get all Tips</strong> displays on the right of your YaaS Project.</li>
<li>Select <strong>Get all Tips</strong> and an angular call is made to the Tips service, which lists all the tips in the Builder module's UI.</li>
</ol>
</li>
</ol>
You can view a more detailed tutorial on the Builder SDK and CLI on the <a href="https://devportal.yaas.io/gettingstarted/createabuildermodule/index.html" target="_blank">Dev Portal</a>.

<hr />
<p><a name="Yaasbite8"></a></p>
<h3>YaaS Bite: Call other web services <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 2h</h3>

<h4>Objectives</h4>
Understand how to make calls to other YaaS services from your own web service.</li>
<h4>Background</h4>
In this bite, you start using services that are available on the <a href="https://market.yaas.io/beta" target="_blank">YaaS Market</a>. In particular, you modify your persistence logic to use the <a href="https://devportal.yaas.io/services/document/latest/index.html" target="_blank">Document service</a>, rather than in-memory logic. You can find the Document service on the YaaS Market, in the <a href="https://market.yaas.io/beta/all/Persistence-(Beta)/9b174e06-9283-4c47-8d16-6eded2ac840a" target="_blank"><strong>Persistence (Beta)</strong> YaaS package</a>.
<h4>Steps</h4>
<ol>
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite800</code> folder.</li>
<li>Explore the code which includes logic to acquire access tokens, and to invoke the Document service, using two new classes:
<ol style="margin-bottom: 0px;" type="i">
<li><code>OAuthWrapper.java</code>, to acquire access tokens by calling the <a href="https://devportal.yaas.io/services/oauth2/latest/index.html" target="_blank">OAuth2 service</a></li>
<li><code>DocuServiceWrapper.java</code>, to make the appropriate REST calls to the Document service</li>
</ol>
</li>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique.</li>
<li>You need to make some adjustments to your web service before it is functional, but one of these changes requires knowing to where your web service is going to be deployed - meaning there is a chicken-and-egg situation. For now you can package, then deploy your not-yet-finalized web service, by skipping its tests during its packaging cycle. Run <button data-clipboard-text="mvn package -DskipTests">mvn package -DskipTests [Copy Me]</button> to do this.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Use <button data-clipboard-text="cf apps">cf apps [Copy Me]</button> to note the URL ① to where your web service was deployed .</li>
<li>In the YaaS Builder, open your YaaS project:
<ol style="margin-bottom: 0px;" type="i">
<li>Subscribe to the <a href="https://market.yaas.io/beta/all/Persistence-(Beta)/9b174e06-9283-4c47-8d16-6eded2ac840a" target="_blank"><strong>Persistence (Beta)</strong> YaaS package</a>.</li>
<li>To wire the web service into YaaS, create a new YaaS service in the Builder and specify the deployed URL ①, using the HTTPS prefix, as the <strong>Source URL</strong>.</li>
<li>Take a note of the proxy address that the Builder assigns to your YaaS service ②.</li>
<li>Add a YaaS client to your YaaS service, and add the three new scopes to the <strong>Required Scopes</strong> list. These scopes are then available to any access token you request using this YaaS client's credentials.</li>
<li>Click <strong>Deploy</strong>, so that your YaaS package is deployed, and its services are available via the API Proxy</li>
</ol>
</li>
<li>In your web service, search for "NEEDS_ADJUSTING" and modify the respective lines to match your YaaS project settings.</li>
<li>Try running the tests now, using <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>. If the tests pass, your web service is successfully calling the Document service.</li>
<li>Debug the tests to find how your web service acquires access tokens and then uses those tokens to call the Document service.</li>
<li>Repackage your web service: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Redeploy: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Open your YaaS client and add your YaaS service's proxy address ② to the <strong>Redirect URIs</strong> list.</li>
<li>Hit your website at <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>. You should see that the front-end works as before but now uses the Document service for its persistence.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_5al00sxy'}) %></td><td></td></tr></table>

<hr />
<p><a name="Yaasbitepubsub"></a></p>
<h3>YaaS Bite: Use the PubSub service <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 1h</h3>

<h4>Objectives</h4>
<ul style="margin-bottom: 0px;">
<li>Understand what the <a target="_blank" href="https://devportal.yaas.io/services/pubsub/latest">PubSub service</a> is and how to use it.</li>
<li>Explore and understand a test that illustrates the key PubSub use cases.</li>
</ul>
<h4>Background</h4>
The PubSub service enables services on the YaaS platform to integrate using asynchronous message-based communication.
This bite includes a Java-based client and a test that demonstrates and tests the following PubSub use case:<ol>
<li>Create a PubSub topic.</li>
<li>Post a message to the PubSub topic.</li>
<li>Read a message from the PubSub topic, then commit that message.</li>
<li>Repeat steps 2 and 3 as required.</li>
<li>Close the PubSub topic.</li>
</ol>
<h4>Set up your YaaS Client</h4>
The API Proxy blocks any unauthorized calls to the PubSub service.
The <a href="../yaasbitesmini/index.html">YaaS Bites First Steps</a> describes how to adjust your YaaS Project to make authorized calls to the <strong>Product</strong> service.
Follow a similar approach to call the PubSub service:
<ol>
<li>Subscribe your YaaS Project to the <strong>Events</strong> package that includes the PubSub service.</li>
<li>In your YaaS Project, note the YaaS Client's Identifier ①, Client ID ②, and Client Secret ③.</li>
</ol>
<h4>Step through the code using the PubSub test</h4>
<ol>
<li>Navigate to the <code>(yb)/essentials/yaasbitepubsub</code> folder.</li>
<li>Search for any "NEEDS_ADJUSTING" text, and adjust these values to match the YaaS Client you set up:<ul style="margin-bottom: 0px;">
<li>Set the <strong>yaaSClientsIdentifier</strong> to your YaaS Client's Identifier ①</li>
<li>Set the <strong>yaaSClientsClient_ID</strong> to your Client ID ②</li>
<li>Set the <strong>yaaSClientsClient_Secret</strong> to your Client Secret ③</li>
<li>Provide a name for the Pubsub <strong>topic</strong> the test creates.</li>
</ul></li>
<li>Verify that the test <strong>SanityTest.java</strong> succeeds. If not, ensure that you have followed the preceding steps correctly.</li>
<li>Debug through the test and the logic it calls in <strong>PubSubClient.java</strong>.</li>
<li>Examine and learn how the following actions are performed for later use:</li> <ul>
<li>How the <strong>getOAuthToken</strong> method calls the OAuth2 service and returns an access token.</li>
<li>How the Builder pattern is used to create the calls to the PubSub service</li>
</ul></li>
</ol>
This bite features a java-based client that calls the PubSub service. The YaaS Service SDK generates a java-based client for a YaaS service, by using the service's RAML. Familiarize yourself with this bite and the <a href="#Yaasbite10"> Use the YaaS web service SDK</a>bite, before you use the <a target="_blank" href="https://devportal.yaas.io/tools/servicesdk/index.html#GeneratingClients">Client SDK</a>.<hr />
<p><a name="Yaasbite9"></a></p>
<h3>YaaS Bite: Use Hystrix for resilience <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 1h</h3>

<h4>Objective</h4>
Understand how Hystrix can help you ensure your web services remain responsive even when they or their dependent services block.
<h4>Background</h4>
It is critical that web services remain responsive. If your own web service, or any web service you depend on degrades or blocks, the user experience should <strong>degrade gracefully</strong> &mdash; perhaps offering less functionality, but <strong>staying responsive</strong>. A non-responsive UI, one that returns a <code>404</code> or similar error is <strong>bad news</strong>. Use <a href="https://github.com/Netflix/Hystrix" target="_blank">Hystrix</a> to help ensure your web service remains responsive. There are many excellent tutorials on Hystrix, including <a href="https://spring.io/guides/gs/circuit-breaker/" target="_blank">Spring</a> and <a href="https://www.javacodegeeks.com/2014/09/defend-your-application-with-hystrix.html" target="_blank">JavaGeeks</a>. <br /> In this bite, you can see Hystrix in action, protecting a simple web service. The web service has two methods of interest: <code>riskyMethod</code> and <code>veryRiskyMethod</code>. These sample methods represent functions that could take unacceptably long to perform. Perhaps they call another web service that hangs, or they hit an overloaded database. In these steps, you simulate a performance problem in the web service, and compare how a method wrapped with Hystrix stays responsive, while a similar method, not wrapped with Hystrix, becomes unacceptably slow. You can then study the code to see how simple the solution is.
<h4>Simulate performance issues with and without Hystrix</h4>
<ol>
<li>Deploy the risky web service:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>For this bite, navigate to the <code>(yb)/essentials/yaasbite900</code> folder.</li>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique.</li>
<li>Compile and package into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Verify that the deployment was successful and that your web service is running: <button data-clipboard-text="cf apps">cf apps [Copy Me]</button>.</li>
<li>Take note of the URL ① where the web service is running, which looks like: <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code>.</li>
</ol>
<li>Compare the responsiveness of methods that are protected and unprotected by Hystrix:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>In a Chrome browser, access the website URL ①.</li>
<li>Activate the "problem" by clicking the "problem" link on that website, then compare the time taken to call a "risky" method that is protected with Hystrix, with the time taken to call an unprotected method.</li>
</ol>
<li>Explore the code that enables Hystrix support in Java:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>Include a Hystrix dependency in the <strong>pom</strong> file.</li>
<li>Supply a fallback method for <code>riskyMethod</code>, using the @HystrixCommand annotation, which tells Hystrix to trigger the fallback method if the original method takes longer than two seconds.</li>
<li>Activate Hystrix with the @EnableCircuitBreaker annotation.</li>
</ol>
</ol>
<p>With carefully-placed and carefully-designed fallback methods, you can ensure that your web service degrades gracefully when overloaded or when dependencies are unavailable.</p>
<table width=100%><tr><td width="33%"></td><td width="33%"><%- @partial('video-kaltura', {video_id:'1_zfcfl9s1'}) %></td><td></td></tr></table>

<hr />
<p><a name="YaasStoreFront"></a></p>
<h3>YaaS Bite: Extend the storefront <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 2h</h3>

<h4>Objectives</h4>
Understand what the YaaS Storefront is, and how to customize and extend its functionality.</li>

<h4>Background</h4>
A YaaS storefront is a customizable, feature-rich, e-commerce web interface, supported by a backing YaaS project designed to give consumers a smooth online shopping experience. The storefront is based on Node.js, Angular, and Restangular. If you are unfamiliar with these technologies, see these excellent tutorials: <a href="https://www.youtube.com/watch?v=pU9Q6oiQNd0" target="_blank">Node</a>, <a href="https://www.youtube.com/watch?v=TRrL5j3MIvo" target="_blank">Angular</a>, and <a href="https://github.com/mgonto/Restangular#starter-guide" target="_blank">Restangular</a>.<br /> For more info, see the comprehensive <a href="https://devportal.yaas.io/gettingstarted/setupastorefront/index.html" target="_blank">Storefront tutorial</a> in the Dev Portal.

<h4>Download and run the default storefront</h4>
<ol>
<li>Download Node.js from <a href="https://nodejs.org/en/download/" target="_blank">Nodejs.org</a>.</li>
<li>Verify that you can run the command <button data-clipboard-text="node -v">node -v [Copy Me]</button>.</li>
<li>Clone the storefront code <button data-clipboard-text="git clone https://github.com/SAP/yaas-storefront.git">git clone https://github.com/SAP/yaas-storefront.git [Copy Me]</button> to some folder ① and <code>cd</code> into it.</li>
<li>Node.js contains a package manager, <code>npm</code>, for downloading dependent packages (a bit like mvn for Java). Run <button data-clipboard-text="npm install">npm install [Copy Me]</button> in the folder ① to download all the packages the storefront depends on. The storefront's <code>package.json</code> file specifies the package dependencies.</li>
<li>Run <button data-clipboard-text="npm start">npm start [Copy Me]</button> to start a local web server.</li>
<li>Open the default storefront at <button data-clipboard-text="http://localhost:9000">http://localhost:9000 [Copy Me]</button>.</li>
<li>Verify that you can see and browse the default set of products in the default storefront.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td width="33%" align=middle><%- @partial('video-kaltura', {video_id:'1_4s0itd87'}) %><br>
<a href="https://www.youtube.com/watch?v=WaZUAANUPrk" target="new">Bayern Yodelling</a></td><td></td></tr></table>
<h4>Extend the storefront's functionality</h4>
The architecture of the storefront is built around <a href="https://docs.angularjs.org/guide/module" target="_blank">Angular modules</a>, with each module typically covering a business unit of functionality, such as products, customers, or product reviews. You can view the Angular modules that come with the default storefront at <code>/public/js/app</code>. To extend the storefront's functionality, you can add your own Angular modules. In this bite, you extend the storefront to include a button on the product details page. The button gives helpful tips to undecided shoppers, such as "This would add to your overall coolness," and "One word: No." You do this first with a hard-wired Proof of Concept (PoC), and then replace that PoC with a real-world implementation.

<br /><br /><strong>Proof of Concept: Deliver tips to the undecided shopper</strong><br /><br />
<ol>
<li>For this bite, navigate to the <code>(yb)/storefront/yaasbitestorefrontdeltas</code> folder. Three folders within the directory, "one", "two", and "three", contain the changed code to copy in to the default storefront ①.</li>
<li>Copy the two folders from the directory <code>yaasbitestorefrontdeltas/one</code> into their respective locations in the folder ①, overwriting the originals.</li>
<li>Compile your changes using the JavaScript builder tool <code>Grunt</code>. If you don't yet have that, you can can download Grunt using the command: <button data-clipboard-text="npm install -g grunt-cli">npm install -g grunt-cli [Copy Me]</button>.</li>
<li>In folder ①, compile the changes with the command: <button data-clipboard-text="grunt build">grunt build [Copy Me]</button>.</li>
<li>Restart your local server <button data-clipboard-text="npm start">npm start [Copy Me]</button>.</li>
<li>Open the storefront at <button data-clipboard-text="http://localhost:9000">http://localhost:9000 [Copy Me]</button> and browse to a product.</li>
<li>Find and click the new <strong>What do you think, Mr Tip?</strong> button to see the new functionality.</li>
<li>Search for "ADJUSTED_AS_NEEDED", to see how the extra logic was applied. You can see that this is currently a hard-wired PoC.</li>
</ol>
<table width=100%><tr><td width="33%"></td><td align=middle width="33%"><%- @partial('video-kaltura', {video_id:'1_hx8efoo1'}) %><br>
<a href="https://www.youtube.com/results?search_query=bayern+tanzmusik" target="new">Bayerisch Tanzmusik</a>
</td><td></td></tr></table>

<br /><strong>Real-world implementation: Deliver tips to the undecided shopper</strong><br />
<ol>
<li>Create a web service that stores and delivers tips to undecided shoppers:
<ol style="margin-bottom: 0px;" type="i">
<li>Navigate to the <code>(yb)/essentials/yaasbite200</code> folder. This directory contains both a web service and accompanying front-end client.</li>
<li>Compile and package into a WAR file: <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Execute locally: <button data-clipboard-text="java -jar target/yaasbite2-1.0-SNAPSHOT.war">java -jar target/yaasbite2-1.0-SNAPSHOT.war [Copy Me]</button>.</li>
<li>Access the front-end client at <button data-clipboard-text="http://localhost:8080">http://localhost:8080 [Copy Me]</button>.</li>
<li>Add some tips, such as, "OMG YES!!!", "One word: No!", and "For your Granny, perhaps?".</li>
</ol>
</li>
<li>Copy the files from the <code>yaasbitestorefrontdeltas/two</code> folder into their respective locations in folder ①, overwriting the originals.</li>
<li>Search for "NEEDS_ADJUSTING" to find the <strong>baseUrl</strong> that you need to change to point to your web service using the line: <code>baseUrl: 'http://localhost:8080'</code>.</li>
<li>In folder ①, compile the changes: <button data-clipboard-text="grunt build">grunt build [Copy Me]</button>.</li>
<li>Restart your local server <button data-clipboard-text="npm start">npm start [Copy Me]</button>.</li>
<li>In a Chrome browser window, open the storefront at <button data-clipboard-text="http://localhost:9000">http://localhost:9000 [Copy Me]</button> and browse to a product.</li>
<li>Find and click the new button <strong>What do you think, Mr Tip?</strong> to see the new functionality. The "Mr Tip" button should now be getting its tips from your own locally-running web service.</li>
<li>In Chrome, click <strong>View</strong> > <strong>Developer</strong> > <strong>Developer Tools</strong>.</li>
<li>Observe the REST communication that happens between your storefront on the front-end client (web page) and your web service.</li>
<li>Search for "ADJUSTED_AS_NEEDED" to see how this extra logic is applied.</li>
</ol>
<h4>Create your own YaaS project to back your storefront</h4>
By default, the storefront is already wired to the project <strong>defaultProj</strong>, created by the YaaS storefront team. The storefront is also pre-populated with products and web services that you can browse. In this section, you create your own YaaS project to use in place of the default one, giving you complete control over the behavior, contents and appearance of your storefront.<br />
<ol>
<li>Create a new project.</li>
<li>Subscribe the project to all of the YaaS packages the Storefront needs: Cart, Checkout, Coupon Management, Customer Accounts, Order Management, Product Content, Site Management</li>
<li>Create a new YaaS client and select all possible required scopes that are listed.</li>
<li>Populate your storefront with some contents:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>Add some products.</li>
<li>Define some images.</li>
</ol>
<li>Take note of the YaaS project identifier and the YaaS client ID. Enter these values in the <strong>PROJECT_ID</strong> and <strong>Client ID</strong> parameters in the file ① <code>/three/gruntfile.js</code>.</li>
<li>Copy the file ① <code>/three/gruntfile.js</code> into your storefront folder to overwrite the existing one.</li>
<li>Rebuild: <button data-clipboard-text="grunt build">grunt build [Copy Me]</button>.</li>
<li>Run your storefront: <button data-clipboard-text="npm start">npm start [Copy Me]</button>. Now the storefront can talk to your own YaaS project.</li>
<li>Visit your storefront and confirm that you can see the products and images you created in your YaaS project, rather than those from the default Storefront project.</li>
<li>For further practice, you can try updating the Tips web service to call the API Proxy instead of directly calling your deployed solution.</li>
</ol>

<hr />
<p><a name="Yaasbite10"></a></p>
<h3>YaaS Bite: Use the YaaS Service SDK <img style="height: 1em; margin: 0px;" src="images/icon_clock.png" alt="" /> ~ 8h</h3>

<h4>Objectives</h4>
Understand what the YaaS Service SDK is and how you can use it to build web services.</li>
<h4>Background</h4>
Until now, each YaaS bite featured web services based on Spring Boot. This bite introduces a web service built with the YaaS <a href="https://devportal.yaas.io/tools/servicesdk/index.html" target="_blank">Service SDK</a>. A widely adopted design pattern in web service development is "API First." The YaaS Service SDK strictly follows this philosophy. With API First, the main input or "source of truth" is the web service's API as defined in a <strong>api.raml</strong> file. The YaaS Service SDK takes this API and creates a skeletal Java/Jersey-based web service complete with: <br />
<ul style="margin-bottom: 0px;">
<li>A skeletal endpoint matching the API, to which you can add your specific business logic</li>
<li>DTOs to support data transfer of JSON and Java objects</li>
<li>A skeletal test framework also matching the API, to which you can add your tests logic</li>
<li>More goodies, described in the YaaS <a href="https://devportal.yaas.io/tools/servicesdk/index.html" target="_blank">Service SDK</a> documentation</li>
</ul>
<h4>Create a web service</h4>
<ol>
<li>Run the YaaS Service SDK's Maven archetype to generate a skeletal web service into some folder ①: <button data-clipboard-text="mvn archetype:generate -U -DarchetypeGroupId=com.sap.cloud.yaas.service-sdk -DarchetypeArtifactId=service-sdk-jersey-spring-archetype -DarchetypeVersion=4.9.1 -DgroupId=com.hybris.bites -DartifactId=yaasbite10 -Dversion=1.0-SNAPSHOT -Dpackage=com.hybris.bites -DasyncApi=false">mvn archetype:generate -U -DarchetypeGroupId=com.sap.cloud.yaas.service-sdk -DarchetypeArtifactId=service-sdk-jersey-spring-archetype -DarchetypeVersion=4.9.1 -DgroupId=com.hybris.bites -DartifactId=yaasbite10 -Dversion=1.0-SNAPSHOT -Dpackage=com.hybris.bites -DasyncApi=false[Copy Me]</button></li>
<li>Rather than providing a completely empty template, this mvn archetype creates, an API and supporting classes for a skeletal "wishlist web service," which is the foundation for the <a href="../createaservice/index.html" target="_blank">Wishlist tutorial</a>, available in the Dev Portal, that you can use to practice working with YaaS.</li>
<li>Explore the code the maven archetype generates. You can view:</li>
<ol style="margin-bottom: 0px;" type="i">
<li>A Wishlist API with accompanying JSON schema and examples at <code>①/src/main/webapp/meta-data/api.raml</code></li>
<li>An endpoint at <code>①/src/main/java/com/hybris/bites/api/generated/DefaultWishlistsResource.java</code></li>
<li>A skeletal test at <code>①/src/test/java/com/hybris/bites/api/generated/DefaultWishlistsResourceTest.java</code>.</li>
</ol>
<li>This bite requires a tip service rather than a wishlist service, so you need to replace the API and logic that relates to Wishlists, with an API and logic for Tips. This has been done in the code for this bite at <code>(yb)/yaassdk/yaasbite1000</code></li>
<li>Navigate to <code>(yb)/yaassdk/yaasbite1000</code>.</li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>Explore the code, in particular:
<ol style="margin-bottom: 0px;" type="i">
<li>the API in <strong>api.raml</strong></li>
<li>the endpoint in <strong>DefaultTipsResource.java</strong></li>
<li>the tests in <strong>DefaultTipsResourceTest.java</strong></li>
</ol>
</li>
<li>Adjust the <strong>name</strong> attribute in the <strong>manifest.yml</strong> to be unique.</li>
<li>Verify that you can run <button data-clipboard-text="mvn clean package">mvn clean package [Copy Me]</button>.</li>
<li>Debug the code through <code>DefaultTipsResourceTest::testSanityPath()</code>. The logic is very similar to earlier bites, but this time you are using JAX-based annotation and a web service built by the web service SDK.</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button></li>
<li>Check that the deployment was successful using the following command: <button data-clipboard-text="cf apps">cf apps [Copy Me]</button>.</li>
<li>Take note of the URL ② where the web service was deployed to, which looks like: <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code>.</li>
</ol>
<h4>Steps to create a front-end</h4>
In this bite, the WAR file does not include a front-end, so you need to deploy one separately.<br />
<ol>
<li>Navigate to the folder for this front-end at <code>(yb)/yaassdk/YaaSbite1000FrontEnd</code>.</li>
<li>Prepare this Maven project for Eclipse with the command <button data-clipboard-text="mvn eclipse:eclipse">mvn eclipse:eclipse [Copy Me]</button>. Then in Eclipse, select <strong>File > Import > Maven > Existing Maven Project</strong> to import this as a Maven project.</li>
<li>Search for "NEEDS_ADJUSTING" and
<ol style="margin-bottom: 0px;" type="i">
<li>Adjust the name in the <strong>manifest.yml</strong> file to be unique.</li>
<li>Adjust the URL in <strong>index.html</strong> to point to your deployed web service ②.</li>
</ol>
</li>
<li>Push to the cloud: <button data-clipboard-text="cf push">cf push [Copy Me]</button>.</li>
<li>Check that the deployment was successful using the following command: <button data-clipboard-text="cf apps">cf apps [Copy Me]</button>.</li>
<li>Take note of the URL ③ where the front-end is running, which looks like <code>https://yourAppName.cfapps.us10.hana.ondemand.com</code>.</li>
<li>In a Chrome browser, open the front-end and verify that you can call the web service ③ you created.</li>
</ol>
<h4>The web service SDK in more detail</h4>
To learn more, go to the <a href="../createaservice/index.html" target="_blank">Wishlist tutorial</a>.
<br><br>
<p align=middle>On Github @ <a href="https://github.com/SAP/yaas-getting-started-yaasbites" target="_blank">https://github.com/SAP/yaas-getting-started-yaasbites</a></p>
<script>
/*!
 * clipboard.js v1.5.12
 * https://zenorocha.github.io/clipboard.js
 *
 * Licensed MIT © Zeno Rocha
 */
!function(t){if("object"==typeof exports&&"undefined"!=typeof module)module.exports=t();else if("function"==typeof define&&define.amd)define([],t);else{var e;e="undefined"!=typeof window?window:"undefined"!=typeof global?global:"undefined"!=typeof self?self:this,e.Clipboard=t()}}(function(){var t,e,n;return function t(e,n,o){function i(a,c){if(!n[a]){if(!e[a]){var s="function"==typeof require&&require;if(!c&&s)return s(a,!0);if(r)return r(a,!0);var l=new Error("Cannot find module '"+a+"'");throw l.code="MODULE_NOT_FOUND",l}var u=n[a]={exports:{}};e[a][0].call(u.exports,function(t){var n=e[a][1][t];return i(n?n:t)},u,u.exports,t,e,n,o)}return n[a].exports}for(var r="function"==typeof require&&require,a=0;a<o.length;a++)i(o[a]);return i}({1:[function(t,e,n){var o=t("matches-selector");e.exports=function(t,e,n){for(var i=n?t:t.parentNode;i&&i!==document;){if(o(i,e))return i;i=i.parentNode}}},{"matches-selector":5}],2:[function(t,e,n){function o(t,e,n,o,r){var a=i.apply(this,arguments);return t.addEventListener(n,a,r),{destroy:function(){t.removeEventListener(n,a,r)}}}function i(t,e,n,o){return function(n){n.delegateTarget=r(n.target,e,!0),n.delegateTarget&&o.call(t,n)}}var r=t("closest");e.exports=o},{closest:1}],3:[function(t,e,n){n.node=function(t){return void 0!==t&&t instanceof HTMLElement&&1===t.nodeType},n.nodeList=function(t){var e=Object.prototype.toString.call(t);return void 0!==t&&("[object NodeList]"===e||"[object HTMLCollection]"===e)&&"length"in t&&(0===t.length||n.node(t[0]))},n.string=function(t){return"string"==typeof t||t instanceof String},n.fn=function(t){var e=Object.prototype.toString.call(t);return"[object Function]"===e}},{}],4:[function(t,e,n){function o(t,e,n){if(!t&&!e&&!n)throw new Error("Missing required arguments");if(!c.string(e))throw new TypeError("Second argument must be a String");if(!c.fn(n))throw new TypeError("Third argument must be a Function");if(c.node(t))return i(t,e,n);if(c.nodeList(t))return r(t,e,n);if(c.string(t))return a(t,e,n);throw new TypeError("First argument must be a String, HTMLElement, HTMLCollection, or NodeList")}function i(t,e,n){return t.addEventListener(e,n),{destroy:function(){t.removeEventListener(e,n)}}}function r(t,e,n){return Array.prototype.forEach.call(t,function(t){t.addEventListener(e,n)}),{destroy:function(){Array.prototype.forEach.call(t,function(t){t.removeEventListener(e,n)})}}}function a(t,e,n){return s(document.body,t,e,n)}var c=t("./is"),s=t("delegate");e.exports=o},{"./is":3,delegate:2}],5:[function(t,e,n){function o(t,e){if(r)return r.call(t,e);for(var n=t.parentNode.querySelectorAll(e),o=0;o<n.length;++o)if(n[o]==t)return!0;return!1}var i=Element.prototype,r=i.matchesSelector||i.webkitMatchesSelector||i.mozMatchesSelector||i.msMatchesSelector||i.oMatchesSelector;e.exports=o},{}],6:[function(t,e,n){function o(t){var e;if("INPUT"===t.nodeName||"TEXTAREA"===t.nodeName)t.focus(),t.setSelectionRange(0,t.value.length),e=t.value;else{t.hasAttribute("contenteditable")&&t.focus();var n=window.getSelection(),o=document.createRange();o.selectNodeContents(t),n.removeAllRanges(),n.addRange(o),e=n.toString()}return e}e.exports=o},{}],7:[function(t,e,n){function o(){}o.prototype={on:function(t,e,n){var o=this.e||(this.e={});return(o[t]||(o[t]=[])).push({fn:e,ctx:n}),this},once:function(t,e,n){function o(){i.off(t,o),e.apply(n,arguments)}var i=this;return o._=e,this.on(t,o,n)},emit:function(t){var e=[].slice.call(arguments,1),n=((this.e||(this.e={}))[t]||[]).slice(),o=0,i=n.length;for(o;i>o;o++)n[o].fn.apply(n[o].ctx,e);return this},off:function(t,e){var n=this.e||(this.e={}),o=n[t],i=[];if(o&&e)for(var r=0,a=o.length;a>r;r++)o[r].fn!==e&&o[r].fn._!==e&&i.push(o[r]);return i.length?n[t]=i:delete n[t],this}},e.exports=o},{}],8:[function(e,n,o){!function(i,r){if("function"==typeof t&&t.amd)t(["module","select"],r);else if("undefined"!=typeof o)r(n,e("select"));else{var a={exports:{}};r(a,i.select),i.clipboardAction=a.exports}}(this,function(t,e){"use strict";function n(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}var i=n(e),r="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol?"symbol":typeof t},a=function(){function t(t,e){for(var n=0;n<e.length;n++){var o=e[n];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(t,o.key,o)}}return function(e,n,o){return n&&t(e.prototype,n),o&&t(e,o),e}}(),c=function(){function t(e){o(this,t),this.resolveOptions(e),this.initSelection()}return t.prototype.resolveOptions=function t(){var e=arguments.length<=0||void 0===arguments[0]?{}:arguments[0];this.action=e.action,this.emitter=e.emitter,this.target=e.target,this.text=e.text,this.trigger=e.trigger,this.selectedText=""},t.prototype.initSelection=function t(){this.text?this.selectFake():this.target&&this.selectTarget()},t.prototype.selectFake=function t(){var e=this,n="rtl"==document.documentElement.getAttribute("dir");this.removeFake(),this.fakeHandlerCallback=function(){return e.removeFake()},this.fakeHandler=document.body.addEventListener("click",this.fakeHandlerCallback)||!0,this.fakeElem=document.createElement("textarea"),this.fakeElem.style.fontSize="12pt",this.fakeElem.style.border="0",this.fakeElem.style.padding="0",this.fakeElem.style.margin="0",this.fakeElem.style.position="absolute",this.fakeElem.style[n?"right":"left"]="-9999px",this.fakeElem.style.top=(window.pageYOffset||document.documentElement.scrollTop)+"px",this.fakeElem.setAttribute("readonly",""),this.fakeElem.value=this.text,document.body.appendChild(this.fakeElem),this.selectedText=(0,i.default)(this.fakeElem),this.copyText()},t.prototype.removeFake=function t(){this.fakeHandler&&(document.body.removeEventListener("click",this.fakeHandlerCallback),this.fakeHandler=null,this.fakeHandlerCallback=null),this.fakeElem&&(document.body.removeChild(this.fakeElem),this.fakeElem=null)},t.prototype.selectTarget=function t(){this.selectedText=(0,i.default)(this.target),this.copyText()},t.prototype.copyText=function t(){var e=void 0;try{e=document.execCommand(this.action)}catch(n){e=!1}this.handleResult(e)},t.prototype.handleResult=function t(e){e?this.emitter.emit("success",{action:this.action,text:this.selectedText,trigger:this.trigger,clearSelection:this.clearSelection.bind(this)}):this.emitter.emit("error",{action:this.action,trigger:this.trigger,clearSelection:this.clearSelection.bind(this)})},t.prototype.clearSelection=function t(){this.target&&this.target.blur(),window.getSelection().removeAllRanges()},t.prototype.destroy=function t(){this.removeFake()},a(t,[{key:"action",set:function t(){var e=arguments.length<=0||void 0===arguments[0]?"copy":arguments[0];if(this._action=e,"copy"!==this._action&&"cut"!==this._action)throw new Error('Invalid "action" value, use either "copy" or "cut"')},get:function t(){return this._action}},{key:"target",set:function t(e){if(void 0!==e){if(!e||"object"!==("undefined"==typeof e?"undefined":r(e))||1!==e.nodeType)throw new Error('Invalid "target" value, use a valid Element');if("copy"===this.action&&e.hasAttribute("disabled"))throw new Error('Invalid "target" attribute. Please use "readonly" instead of "disabled" attribute');if("cut"===this.action&&(e.hasAttribute("readonly")||e.hasAttribute("disabled")))throw new Error('Invalid "target" attribute. You can\'t cut text from elements with "readonly" or "disabled" attributes');this._target=e}},get:function t(){return this._target}}]),t}();t.exports=c})},{select:6}],9:[function(e,n,o){!function(i,r){if("function"==typeof t&&t.amd)t(["module","./clipboard-action","tiny-emitter","good-listener"],r);else if("undefined"!=typeof o)r(n,e("./clipboard-action"),e("tiny-emitter"),e("good-listener"));else{var a={exports:{}};r(a,i.clipboardAction,i.tinyEmitter,i.goodListener),i.clipboard=a.exports}}(this,function(t,e,n,o){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function a(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function c(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}function s(t,e){var n="data-clipboard-"+t;if(e.hasAttribute(n))return e.getAttribute(n)}var l=i(e),u=i(n),f=i(o),d=function(t){function e(n,o){r(this,e);var i=a(this,t.call(this));return i.resolveOptions(o),i.listenClick(n),i}return c(e,t),e.prototype.resolveOptions=function t(){var e=arguments.length<=0||void 0===arguments[0]?{}:arguments[0];this.action="function"==typeof e.action?e.action:this.defaultAction,this.target="function"==typeof e.target?e.target:this.defaultTarget,this.text="function"==typeof e.text?e.text:this.defaultText},e.prototype.listenClick=function t(e){var n=this;this.listener=(0,f.default)(e,"click",function(t){return n.onClick(t)})},e.prototype.onClick=function t(e){var n=e.delegateTarget||e.currentTarget;this.clipboardAction&&(this.clipboardAction=null),this.clipboardAction=new l.default({action:this.action(n),target:this.target(n),text:this.text(n),trigger:n,emitter:this})},e.prototype.defaultAction=function t(e){return s("action",e)},e.prototype.defaultTarget=function t(e){var n=s("target",e);return n?document.querySelector(n):void 0},e.prototype.defaultText=function t(e){return s("text",e)},e.prototype.destroy=function t(){this.listener.destroy(),this.clipboardAction&&(this.clipboardAction.destroy(),this.clipboardAction=null)},e}(u.default);t.exports=d})},{"./clipboard-action":8,"good-listener":4,"tiny-emitter":7}]},{},[9])(9)});</script>
<script>// <![CDATA[
 var btns = document.querySelectorAll('button');
 var clipboard = new Clipboard(btns);
 clipboard.on('success',
 function(event) {
 event.clearSelection();
 event.trigger.textContent = event.trigger.textContent.toString().replace('Copy Me','Copied!');
 window.setTimeout(function() {
 event.trigger.textContent = event.trigger.textContent.toString().replace('Copied!','Copy Me');
 }, 2000);
 })
// ]]></script>
