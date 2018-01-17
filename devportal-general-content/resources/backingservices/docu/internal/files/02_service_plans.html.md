---
title: 'Service Plans'
type: PostgreSQL
---

The following database plans are available. If you notice any missing or incorrect information, contact us.

| Plan        | vCPU  | Memory  | Disk  | HA  | Price       |
|-------------|------:|--------:|------:|----:|------------:|
| 5-GB-dev    | 1     | 1GB     | 5GB   | no  | 0.03 $/hour |
| 5-GB        | 1     | 1GB     | 5GB   | yes | 0.10 $/hour |
| 10-GB       | 2     | 4GB     | 10GB  | yes | 0.30 $/hour |
| 100-GB      | 4     | 16GB    | 100GB | yes | 1.60 $/hour |

For cost reasons, you should always start with the lowest feasible plan and upgrade when necessary. Even though plans can be downgraded, the storage allocation cannot. For example, a 10-GB instance downgraded to 5-GB retains 10 gigabytes of storage. This is a limitation of AWS and might change in the future.

You can make plan changes online without any service disruption, apart from a temporary reduction in performance.

Do not use the -dev plan in production; use it for development purposes only, because we cannot guarantee the availability of data. You cannot switch between -dev and non-dev plans.
