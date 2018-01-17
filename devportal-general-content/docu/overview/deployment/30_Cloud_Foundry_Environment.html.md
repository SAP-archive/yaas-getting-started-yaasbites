---
title: 'Cloud Foundry Environment'
---

This procedure uses the Cloud Foundry command line interface (cf CLI). To work with the Cloud Foundry environment, you can download and set up the cf CLI.
<br>See [Download and Install Cloud Foundry Command Line Interface](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/4ef907afb1254e8286882a2bdef0edf4.html).

1. Log on to the Cloud Foundry instance:
  ```
  cf api https://api.cf.<HOST>
  cf login
  cf target -o <ORG> -s <SPACE>
  ```
  To check the available hosts, see [Regions and Hosts](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/350356d1dc314d3199dca15bd2ab9b0e.html).

  See also [Log On to the Cloud Foundry Instance Using the Console Client](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/7a37d66c2e7d401db4980db0cd74aa6b.html).

2. Navigate to the project directory that you want to deploy.

3. Use the [cf push](https://docs.cloudfoundry.org/devguide/deploy-apps/deploy-app.html) command from the cf CLI.
  ```
  cf push
  ```

4. (Optional) Check the service URL.
  ```
  cf apps
  ```

For more options and detailed description of the cf CLI commands, see the [Cloud Foundry Documentation](https://docs.cloudfoundry.org/cf-cli/).

<div class="panel tip">
You can also use the Cloud Foundry Eclipse Plugin to deploy and manage Java and Spring applications on a Cloud Foundry instance. See https://docs.run.pivotal.io/buildpacks/java/sts.html
</div>

#### Deploy a Builder UI module

To deploy a Builder UI module with Cloud Foundry, use the **manifest.yml** file in the root directory of the Builder UI module:
```
applications:
- name: {your module name}
  buildpack: https://github.com/cloudfoundry/staticfile-buildpack.git
  instances: 1
  memory: 128M
  domain: {target cf api domain}
```
Configure the **manifest.yml** file for your module, then deploy the Builder UI module with the `cf push` command.

<div class="panel note">
A Builder UI module is typically static content, such as HTML and Javascript. Therefore, it is recommended you use the **staticfile-buildpack** buildback.
</div>
