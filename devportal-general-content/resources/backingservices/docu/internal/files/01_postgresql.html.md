---
title: 'PostgreSQL'
type: PostgreSQL
---

The rdspostgres service creates a standard PostgreSQL database. After the CF CLI creates the service, it provisions a new database cluster which can be bound to Cloud Foundry apps. The system encrypts the data inside the database, and backs the data up every day with a retention period of 5 days. Backups can be restored on request via [jira](https://jira.hybris.com/secure/RapidBoard.jspa?rapidView=907&projectKey=ATX&view=planning&selectedIssue=ATX-3&epics=visible).

DB clients connect using SSL. Obtain the [root certificate](https://s3.amazonaws.com/rds-downloads/rds-combined-ca-bundle.pem) from Amazon.

The current database version is 9.5. AWS automatically deploys minor updates.
