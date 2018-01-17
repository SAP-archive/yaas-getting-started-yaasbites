---
title: Start with Docker
---

If you do not have Java 8 or Maven, install the [Docker](https://docs.docker.com/) as explained in the following section. In the console window, navigate to the project folder and execute the following command:

``` no-highlight
docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -v "$HOME/.m2:/root/.m2" -w /usr/src/mymaven -p 8080:8080 maven:alpine /bin/bash
```

Once inside the Docker container with your current directory mounted as `/usr/src/mymaven`, execute the Maven and Java commands from the console window. To exit the interactive mode, enter `exit`.


<div class="panel note">
On the Windows platform, mount local directories on a Docker Linux image using the -v parameter, by specifying paths in Linux format since they are used in the container. For example, this code:
Example:
```
C:\Users\Resource7400123456\.m2
```
Is written as:
```
//C/Users/Resource7400123456/.m2
```
</div>



### Mount your local Maven repository

The `-v $HOME/.m2:/root/.m2` part in the previous command creates and mounts your local `HOME/.m2` directory to use as a local repository. Maven downloads all the needed artifacts to this directory. It is possible to have the artifacts downloaded inside the container, but the download takes time, and all the content of the container is lost when the directory is unmounted. Instead, store the repository outside of the container if you intend to use it more than once. When you no longer need to run Maven, delete the directory.


### Run your service locally

The `-p 8080:8080` parameter in the previous command allows a web service to be visible outside of the Docker image while running it locally.

### Run Maven in non-interactive mode

You do not have to use the Docker in interactive mode to execute Java or Maven commands. Replace the /bin/bash part of the previous command with a single command to be executed. For example, this is the command to execute mvn clean install inside the container:

``` no-highlight
docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -v "$HOME/.m2:/root/.m2" -w /usr/src/mymaven -p 8080:8080 maven:alpine mvn clean install
```

### Run Maven using helper scripts

To simplify the execution of commands in your maven Docker container, a helper [Linux bash script](img/mvnDockerRun.sh) and a [Windows batch script](img/mvnDockerRun.bat) are provided.

Running these scripts in a directory will run the maven:alpine Docker container, mount that directory as '/usr/src/mymaven', the "$HOME/.m2" directory as '/root/.m2', expose port 8080 and opens the bash shell of the Docker container.

You can also use the scripts to run any command that is executed with Maven and Java while using the docker container. Simply pass the command as the script parameters. For example, this command runs mvn clean install inside the maven:alpine Docker container, but affects the files in your current directory:

``` no-highlight
mvnDockerRun mvn clean install
```

To run the helper scripts in any directory on Linux-based machines, add the script files to the `/usr/local/bin/` directory. For Windows machines include the scripts' containing folder in the PATH environment variable.

### Troubleshooting

If you run a Linux Docker image and the mounting of local directories does not work make sure that you have activated sharing for the drive you're using. (on Windows see `Settings -> Shared Drives`, in Linux `Preferences->File Sharing`). Also make sure that your firewall or Intrusion Prevention System (IPS) does not prevent the sharing of your drive.



