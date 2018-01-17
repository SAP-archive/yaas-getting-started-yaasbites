---
title: Code Versioning
---

A code versioning schema is optional, but recommended for all services in YaaS to establish consistency and a common understanding of what types of updates to expect in each new code version. This strategy is based on the following established industry standards and best practices:

* [Git-flow Versioning](https://datasift.github.io/gitflow/Versioning.html)
* [Maven Release Plugin](https://maven.apache.org/maven-release/maven-release-plugin/)
* [Semantic Versioning](http://semver.org/)

### Code Versioning Examples

While the released API version only has a MAJOR version number, internally, the code version is composed of three numbers, such as 1.0.0 (MAJOR.MINOR.PATCH). In the examples below, these terms refer to an artifact version defined in the `pom.xml` file in the respective branches:

* **master-ver** – master branch (released version)
* **dev-ver** – develop branch
* **hotfix-ver** – hotfix branch

In these examples, only changes in MINOR and PATCH number are shown. If a change in MAJOR is required, re-start the schema with the next MAJOR number.

### Version update patterns
Follow the rules below to guarantee that version changes in your API follow the required patterns.

**To begin your project:**
master-ver: has the PATCH number set to zero, such as **1.0.0** or **0.1.0**.<br>
dev-ver: has the same number as the **master-ver** with an increased PATCH number, such as **x.y.1-SNAPSHOT**.

**Increase in PATCH version:**
**1.0.0 -> 1.0.1 -> 1.0.2** or **1.4.12 -> 1.4.13 -> 1.4.14**
Gaps in numbering do not occur.

**Increase in MINOR version, with PATCH set to zero:**
**1.4.13 -> 1.5.0**
The following is not expected: 1.4.13 -> 1.5.2.

**Bugfix-only release:**
master-ver: **1.0.0 -> 1.0.1**
dev-ver: **1.0.1-SNAPSHOT -> 1.0.2-SNAPSHOT**

The version increase is done automatically by the **maven release plugin**. If the next release is also a bugfix-only release, the version is already in **dev-ver**. If the next relase is a feature release, see below.

**Feature release, with some bug fixes:**
master-ver: **1.0.1 -> 1.0.1 -> 1.1.0**<br>
dev-ver: **1.0.2-SNAPSHOT -> 1.1.0-SNAPSHOT -> 1.1.1-SNAPSHOT**<br><br>

Since the feature changes are significant enough to raise the MINOR version, the **dev-ver** is raised manually from 1.0.2-SNAPSHOT to 1.1.0-SNAPSHOT, while the **master-ver** stays the same. Then, the **master-ver** increases to 1.1.0, and the **dev-ver** to 1.1.1-SNAPSHOT automatically by the **maven release plugin**. If the next release is a bugfix-only release, the version is already in **dev-ver**. If the next release is a feature release, it will be 1.2.0.

**Hotfix release:**
master-ver: **1.1.0 -> 1.1.1**
dev-ver: **1.1.1-SNAPSHOT -> 1.1.2-SNAPSHOT**
hotfix-ver: **1.1.1**

With a hotfix from the master branch, the **hotfix-ver** version is set to the **master-ver** with an increased PATCH number of 1.1.1, but this collides with our **dev-ver**. To fix that, during the merge of the hotifx branch into the develop branch, the **dev-ver** is manually increased to the next PATCH version of 1.1.2-SNAPSHOT. If the next release is a bugfix-only release, the version is already in **dev-ver**. Several hotfixes can be made between releases without a problem with continuous version numbers, such as 1.1.0 -> 1.1.1 -> 1.1.2. If the next release is a feature release, it will be 1.2.0.

**Hotfix release when **dev-ver** is already at the next MINOR version:**
master-ver: **1.1.2 -> 1.1.3**<br>
dev-ver: **1.2.0-SNAPSHOT -> remains 1.2.0-SNAPSHOT**<br>
hotfix-ver: **1.1.3**<br><br>

Previously, there were two hotfixes which increased the **master-ver** from 1.1.0 to 1.1.2. The next release will be a feature release, so the **dev-version** was already increased to 1.2.0-SNAPSHOT. Before merging the hotfix, the version is set to the **master-ver** with an increased PATCH version of 1.1.3. Since that does not collide with the **dev-ver**, do NOT manually increase the **dev-ver** when merging the hotfix. The **dev-ver** REMAINS to be 1.2.0-SNAPSHOT.

### Automatic versus manual
While the **maven release plugin** supports automatic versioning, these things have to be done manually:
* Decide when to raise the MINOR number in the **dev-version**. It depends on if the next release is a bugfix-only release, or a feature release.
* Increase the PATCH number in the **dev-version** when merging a hotfix into the develop branch, and the version collides.
* Do not increase the PATCH number in the **dev-version** when merging a hotfix into the develop branch, and the **dev-version** has a larger MINOR number.
