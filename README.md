# tomcat-docker
An example of an docker image containing tomcat 8 and oracle jdk 8

## To run with tomcat maven plugin on local env

* mvn clean install
* mvn tomcat7:run

## To run dockerfile

### MAVEN
mvn clean install -P docker

### DOCKER COMMAND LINES
* First copy paste the war from target directory to target/docker directory
```sh
$ cp target/tomcat-docker-1.0.0-SNAPSHOT.war target/docker/
```

* Then build the docker image (it can take some time as it's curling oracle jdk and tomcat 8)
```sh
docker build -t tomcat-docker target/docker/
```

* Finally you're good to start the image in the background
```sh
docker run -d -p 8080:8080 tomcat-docker
```
* To connect in ssh to docker image with bash
```sh
docker exec -it ${IMAGE_ID_GENERATED_BY_DOCKER} bash
```
* To stop the container
```sh
docker stop $(docker ps -q --filter ancestor=tomcat-docker:1.0.0-SNAPSHOT)
```
## Check that the app is running

http://localhost:8080/tomcatDocker/index.xhtml

## About logs
Logs are written in docker image. /log/ for the application and /opt/tomcat/logs for tomcat.
This is not a good practice, it is better to use a mount point to write logs on a NAS (check
docker directive VOLUME to do that).
