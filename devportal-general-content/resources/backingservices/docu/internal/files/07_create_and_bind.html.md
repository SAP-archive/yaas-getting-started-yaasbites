---
title: 'Creating and Binding Services'
type: SQS
---

### Create a service
To provision a new database service:
``` no-highlight
cf create-service sqs queue myqueue
```

`create-service` creates a new queue with default settings. Use the following parameters to create a customized service:

| Option                       | Type    | Description |
|------------------------------|---------|--------------|
| delay_seconds                | String | The time in seconds that the delivery of all messages in the queue is delayed |
| maximum_message_size         | String  | The limit of how many bytes a message can contain before Amazon SQS rejects it |
| message_retention_period     | String  | The number of seconds Amazon SQS retains a message |
| receive_message_wait_time_seconds      | String  | The time which a ReceiveMessage call waits for a message to arrive |
| visibility_timeout | String  | The visibility timeout for the queue |

Refer to the [Amazon SQS documentation](https://aws.amazon.com/documentation/sqs/) for more details about these parameters.

Parameters can be specified in JSON format:

``` no-highlight
cf create-service rdspostgres 5-GB my-db -c '{"delay_seconds": "20", "message_retention_period": "10"}'
```

### Update a service
You can change the service plan and certain parameters at runtime.

Use the following parameters:

| Option                       | Type    | Description |
|------------------------------|---------|--------------|
| delay_seconds                | String | The time in seconds that the delivery of all messages in the queue is delayed |
| maximum_message_size         | String  | The limit of how many bytes a message can contain before Amazon SQS rejects it |
| message_retention_period     | String  | The number of seconds Amazon SQS retains a message |
| receive_message_wait_time_seconds      | String  | The time which a ReceiveMessage call waits for a message to arrive |
| visibility_timeout | String  | The visibility timeout for the queue |

``` no-highlight
cf update-service myqueue -c '{"delay_seconds": "5", "message_retention_period": "3"}'
```

### Bind a service to an application
The service can be bound to an app as soon as the system successfully creates it. The `bind-service` command creates a new user and injects the credentials into the environment variables of the app.

``` no-highlight
cf bind-service my-app myqueue
cf restage my-app
```

Use the `restage` command to update the app's environment variables. CloudFoundry stores the following JSON structure inside the `VCAP_SERVICES` environment variable:

``` json
{
  "sqs": [
   {
    "credentials": {
     "password": "gymJgJesKK+PmrXnE4gU/lnxG+JHt5+2RqhIUddm",
     "uri": "https://sqs.us-east-1.amazonaws.com/177458849667/cf-aab77da3-0337-4b5c-b3bc-1a5591cb063c",
     "username": "AKIAIN47TJJHYHG7QBIA"
    },
    "label": "sqs",
    "name": "myqueue",
    "plan": "queue",
    "provider": null,
    "syslog_drain_url": null,
    "tags": [
     "sqs",
     "jms"
    ],
    "volume_mounts": []
   }
  ]
 }
}

```

### Unbind a service
To unbind a service from an app, use the `unbind-service` command.

``` no-highlight
cf unbind-service my-app myqueue
```

This revokes all access rights and deletes the user.

### Delete a Service
`delete-service` deprovisions a service. This is only allowed if no apps are bound to the service.

``` no-highlight
cf delete-service myqueue
```
