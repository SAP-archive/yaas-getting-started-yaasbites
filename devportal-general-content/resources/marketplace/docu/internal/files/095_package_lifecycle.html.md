---
title: 'Package Lifecycle'
type: 'Package Lifecycle'
---
During its lifecycle, a package has one of a number of states or statuses. These apply to both commercial and non-commercial packages.


<img src="/internal/tools/marketplace/img/package flow-1208-433619c6-0.png">


| State                                   | Phase                                                                                                         | Technical Status                          |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------|--------------------------------------------|
| Draft                                   | Package created but not yet been submitted for approval. <br>Package is a showcase.                                                                                   | `DRAFT`                                    |
| In Review                               | Package has been submitted for approval.                                                                                         | `REVIEW`                                   |
| Declined 	                              | Package declined by approver. State is the same as `DRAFT`, but has a different name for tracking purposes.                                             | `DECLINED `                                |
| Published                               | Approved and available on the YaaS Market. 	                                                                  | `PUBLISHED`                                |
| Published	(with unpublished changes)    | Package already approved and on the YaaS Market, but has changes pending that have not yet been approved and published.								  | `DRAFT`								       |
| Retired                                 | No longer available on the market. <br>Some customers may still use the package as they have 90 days before a package is deprecated.			  | `DRAFT`                                    |
| Deleted                                 | Package has been retired and is no longer used.                          | `DELETED`                                  |


When a package is first created, it has the status `Draft`. When the package is submitted for approval to the market operator (also known as the "approver") the status changes to `In Review`.

A package in the review has two possible routes:

* If it is approved, the status changes to `Published`.
* If it is declined, the status changes to `Declined`. In this case the package owner can make changes to the package and resubmit it for approval.

The market operator can delete a package that has been declined and the status then changes to `Deleted`.

If a package owner decides to withdraw a particular package from the market, he can mark the status as `Retired`. This removes the package from the market, and triggers and e-mail template allowing the package owner to inform all subscribers that the package will not be available after a specified deprecation period. The minimum notice is three months.
