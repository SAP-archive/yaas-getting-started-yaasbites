---
title: Clone and Run the Storefront Locally
---

The source code for the YaaS storefront application is available on GitHub at https://github.com/sap/yaas-storefront. If you do not already have a GitHub account, you will need to sign up for one. Fork the repository to your GitHub account by clicking the **Fork** button, and then clone the **yaas-storefront** repository by executing the following command:

```
git clone https://github.com/SAP/yaas-storefront.git
```

Ensure that you are on the **master** branch. From within the directory above, download the dependencies and install the build tools using the following command:

```
npm install
```

After the installations for the `npm` and `bower` dependencies are successful, and the installation of the build tools complete, find your project ID in the project administration settings in the <a href="https://builder.yaas.io/">Builder</a> under **Projects** > **_Project Name_** > **Administration**. In the `gruntfile.js` file, replace the **PROJECT_ID** with your own project ID. When you build the project, the default project ID in the `bootstrap.js` file is replaced with your project ID. Also, configure the **CLIENT_ID** and the **REDIRECT_URI** variables in the `gruntfile.js` file with the values set in the application associated with your project.

<div class="panel info">For more information about project IDs, see <a href="/gettingstarted/createaclient/index.html">Create a Client</a>.
</div>

Run your storefront by executing the following command:

```
npm start
```

In your web browser, navigate to the URL http://localhost:9000/#!/ to see your store running on your local machine.
