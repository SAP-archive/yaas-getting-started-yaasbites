---
title: Configure Maven
---
These configuration steps are only necessary if you don't have direct access to the Internet from your network and need to use a proxy server for HTTP and HTTPS connections instead.

To use the YaaS Service SDK from behind an HTTP(S) proxy, configure Maven by setting the following <a href="https://docs.oracle.com/javase/8/docs/api/java/net/doc-files/net-properties.html" target="_blank">Java system properties</a>:

<table class="table table-striped table-glossary techne-table">
  <tr>
    <th>System Property</th>
    <th>Description</th>
    <th>Example</th>
  </tr>
  <tr>
    <td><code>http.proxyHost</code></td>
    <td>The host name (DNS name or IP address) of the proxy server to use for HTTP.</td>
    <td>proxy-server.our-awesome-office.net</td>
  </tr>
  <tr>
    <td><code>http.proxyPort</code></td>
    <td>The IP port on which to connect to the proxy server for HTTP.</td>
    <td>8080</td>
  </tr>
  <tr>
    <td><code>https.proxyHost</code></td>
    <td>The host name (DNS name or IP address) of the proxy server to use for HTTP over SSL.</td>
    <td>proxy-server.our-awesome-office.net</td>
  </tr>
  <tr>
    <td><code>https.proxyPort</code></td>
    <td>The IP port on which to connect to the proxy server for HTTP over SSL.</td>
    <td>8443</td>
  </tr>
</table>

<br>
To set these properties, use the `-D` command-line arguments every time you invoke Maven.  Alternatively, you can add the properties to the **MAVEN_OPTS** environment variable, which is used by Maven automatically on every invocation. To set the **MAVEN_OPTS** environment variable on the command line for different operating systems, use these options:

<h4>Windows</h4>
``` no-highlight
SET MAVEN_OPTS=-Dhttp.proxyHost=proxy-server.our-awesome-office.net -Dhttp.proxyPort=8080 -Dhttps.proxyHost=proxy-server.our-awesome-office.net -Dhttps.proxyPort=8443
```

<h4>Mac OS X, Linux, and other UNIX systems using bash</h4>
``` no-highlight
export MAVEN_OPTS="-Dhttp.proxyHost=proxy-server.our-awesome-office.net -Dhttp.proxyPort=8080 -Dhttps.proxyHost=proxy-server.our-awesome-office.net -Dhttps.proxyPort=8443"
```

Once you have verified these settings, set the **MAVEN_OPTS** variable permanently, if desired. In Windows, set it in the System Settings. On Mac OS X, Linux, and other UNIX systems, add a corresponding line to the `.profile` file in the user's home directory.
