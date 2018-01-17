---
title: 'Cloud Foundry domains for Builder Modules hosted on AWS'
type: Deployment
---

If you have credentials, use the APIs of the Cloud Foundry platform on AWS:
- Production: https://api.us-east.cf.yaas.io
- Stage: https://api.us-east.stage.cf.yaas.io

Deploy your Builder modules so that they are accessible using a dedicated `*.modules.yaas.io` domain with a signed SSL certification.

To do so, define the target domain to match the pattern:
 - Production: <b>us-east.modules.yaas.io</b>
 - Stage: <b>us-east.stage.modules.yaas.io</b>

For example, configure your manifest file as shown:

```
applications:
- name: unicorns
	buildpack: https://github.com/cloudfoundry/staticfile-buildpack.git
	instances: 1
	memory: 64M
	domain: us-east.modules.yaas.io
```
After you deploy using the `cf push` command, your module is accessible at https://unicorns.us-east.modules.yaas.io. The module descriptor file is accessible at https://unicorns.us-east.modules.yaas.io/builder/module.json.
