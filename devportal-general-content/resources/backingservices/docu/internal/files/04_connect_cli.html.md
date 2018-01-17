---
title: 'Direct Connection to PostgreSQL'
type: PostgreSQL
---

Use the `cf ssh` command to connect a local database client to the CF-deployed service in the cloud. Use the `-L` parameter to forward the database port to the local machine.

The `VCAP_SERVICES` environment variable of a bound app contains all the information necessary to establish a connection. Find the important fields `host`, `port`, `username`, `password`, and `name`, within the `credentials` block.

1. Build an SSH tunnel and forward the database port:

    ``` no-highlight
    cf ssh rds-test -L <localport>:<host>:<port>
    ```

    The `localport` is any port number not in use on the local system, and can be the same as `port`.

2. Connect to the database on the local port with your PostgreSQL client of choice. A `psql` example:

    ``` no-highlight
    $ psql -U <username> -W -p <localport> -h localhost <name>
    ```

4. When finished, close the SSH connection with the `exit` command.
