# About

This repository is intended to store generic content that is needed for the DevPortal, such as:

* Getting started guides
* General documents about hybris as a Service delivery
* Blogs that are not for a specific service, but for a general idea or topic
* Partials - content that is reused in multiple areas of the DevPortal, such as generic text that is the same in each service API document. To read more about Partials, go to https://devportal.yaas.io/internal/docu_guide/tips/index.html#ReuseContent

# Project Structure

* **docu/gettingstarted** for getting starting guides (**gettingstarted**)
* **docu/partials** for content reuse
* **docu/files** for documentation visible in the 'Overview' section in the Dev Portal

# Making Changes

New content or updates to existing documents should be done by the feature branch workflow that is respected by most of the teams that develop services. In short, to make changes, create a feature branch out of the develop branch. After introducing changes in the feature branch, make a pull request to merge the changes into the develop branch. Because the repository is open to everybody contributing to yaaS, the pull request should be handled by somebody from your team. If you don't have a team, ask team Flyspeck for a review.

More info about the feature branch workflow is located here: https://www.atlassian.com/git/workflows#!workflow-feature-branch (the difference is that we work also with develop and not only master).

# Document Deployment Process

When you work on documentation in the feature branch, you can't see your changes on the DevPortal until you merge to the **develop** branch. This is why we prepared an online editor that you can use to write documentation. This editor is uses the exact same styles as the DevPortal, so you know exactly how your document is rendered: https://devportal.yaas.io/internal/docu_guide/markdown_editor.html

## Development

Changes introduced in the develop branch are immediately reflected in https://devportal-dev.stage.yaas.io/, because the https://bamboo.hybris.com/browse/PAAS-DEVPDEV plan is trigged automatically.

## Production

To push changes to production:

1. Merge changes from the **develop** branch to the **master** branch.
2. Manually trigger the https://bamboo.hybris.com/browse/PAAS-DEVPORSTAGE plan, and see the changes here: https://devportal.stage.yaas.io/gettingstarted/index.html.
3. If the above step is fine, manually trigger the  https://bamboo.hybris.com/browse/PAAS-DEVPP plan, and see the changes here: https://devportal.yaas.io/gettingstarted/index.html.
