---
title: 'Creating and Binding Services'
type: Postgresql
---

### Show service plans
To show available service plans:
``` no-highlight
cf m -s rdspostgres
```
For development plans, substitute rdspostgres with rdspostgres-dev.

### Create a service
To provision a new database service:
``` no-highlight
cf create-service rdspostgres 5-GB my-db
```

<div class="panel note">Service creation is an asynchronous operation, and can take time. To see the current deployment status, run `cf service my-db`.</div>

`create-service` creates a new database service with default settings. Use the following parameters to create a customized service:

| Option                       | Type    | Description |
|------------------------------|---------|--------------|
| backup_retention_period      | Integer | The number of days between 0 and 35 that Amazon RDS should retain automatic backups |
| character_set_name           | String  | The character set used to create the database |
| dbname                       | String  | The name of the database within the database service. If this is not set, the name will be random |
| preferred_backup_window      | String  | The daily time range during which the system creates automated backups |
| preferred_maintenance_window | String  | The weekly time range during which system maintenance occurs |

Refer to the [Amazon RDS documentation](https://docs.aws.amazon.com/AmazonRDS/latest/APIReference/Welcome.html) for more details about these parameters.

Parameters can be specified in JSON format:

``` no-highlight
cf create-service rdspostgres 5-GB my-db -c '{"dbname": "whatever", "backup_retention_period": 1}'
```

### Update a service
You can change the service plan and certain parameters at runtime. The system generally applies changes inside the maintenance window, but applies them immediately if you use the `apply_immediately` parameter.

``` no-highlight
cf update-service my-db -p 10-GB -c '{"apply_immediately": true}'
```

<div class="panel note">Service creation is an asynchronous operation, and can take time. To see the current deployment status, run `cf service my-db`.</div>

Use the following parameters:

| Option                       | Type    | Description |
|------------------------------|---------|-------------|
| apply_immediately            | Boolean | Specifies whether any modifications in this request and any previously pending ones should be applied as soon as possible, regardless of the preferred maintenance window |
| backup_retention_period      | Integer | The number of days between 0 and 35 that Amazon RDS should retain automatic backups |
| preferred_backup_window      | String  | The daily time range during which the system creates automated backups |
| preferred_maintenance_window | String  | The weekly time range during which system maintenance occurs |

### Bind a service to an application
The service can be bound to an app as soon as the system successfully creates it. The `bind-service` command creates a new set of credentials and injects it into the environment variables of the app.

``` no-highlight
cf bind-service my-app my-db
cf restage my-app
```

Use the `restage` command to update the app's environment variables. CloudFoundry stores the following JSON structure inside the `VCAP_SERVICES` environment variable:

``` json
{
  "rdspostgres": [
   {
    "credentials": {
     "host": "cf-e0163fe4-f0a6-4b07-b4a2-a7f15ce20e03.cpggq5dgiosj.us-east-1.rds.amazonaws.com",
     "jdbcUrl": "jdbc:postgresql://cf-e0163fe4-f0a6-4b07-b4a2-a7f15ce20e03.cpggq5dgiosj.us-east-1.rds.amazonaws.com:5432/whatever?user=MTcyYTYwZWQtODJm\u0026password=WoQ2S2j0vxdlGjcoiB35elt5O7l8hyVg",
     "name": "whatever",
     "password": "WoQ2S2j0vxdlGjcoiB35elt5O7l8hyVg",
     "port": 5432,
     "uri": "postgres://MTcyYTYwZWQtODJm:WoQ2S2j0vxdlGjcoiB35elt5O7l8hyVg@cf-e0163fe4-f0a6-4b07-b4a2-a7f15ce20e03.cpggq5dgiosj.us-east-1.rds.amazonaws.com:5432/whatever?reconnect=true",
     "username": "MTcyYTYwZWQtODJm"
    },
    "label": "rdspostgres",
    "name": "my-db",
    "plan": "5-GB",
    "provider": null,
    "syslog_drain_url": null,
    "tags": [
     "postgres",
     "relational"
    ],
    "volume_mounts": []
   }
  ]
}
```

### Unbind a service
To unbind a service from an app, use the `unbind-service` command.

``` no-highlight
cf unbind-service rds-test my-db
```

<div class="panel warning">**Attention:** When you unbind a database within the instance, the system removes the database unless it is also bound to other apps.</div>

This revokes all access rights and deletes the user.

### Delete a service
`delete-service` deprovisions a service. This is only allowed if no apps are bound to the service.

``` no-highlight
cf delete-service my-db
```

<div class="panel note">Service creation is an asynchronous operation, and can take time. To see the current deployment status, run `cf service my-db`.</div>
