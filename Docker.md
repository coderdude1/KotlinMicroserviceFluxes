#Notes on dockerfying stuff

## Some general notes

### Building from the Dockerfile
Using a shell (WSL or powershell) in the root node of the project
`docker build -t spring-boot-kotlinmicroservice .`

### Running the service and a mongo docker instance (docker-compose.yml)
I did this from powershell since I didn't have the WSL version installed and configured.  From the shell
in the root node of this project
`docker-compose up`

Note this will build the image with a different name compared to the dockerfile (ie if you do both there are now 2 
images in the repo)

#### Creating the volume specified for mongo in docker compose
`docker volume create monogo-volume`

Not sure ATM how to specify stuff like actual location on disk, etc

## Links
Sizing memory so it doesn't runthe container out of mem
    https://medium.com/@yortuc/jvm-memory-allocation-in-docker-container-a26bbce3a3f2 

not sure yet if this will solve the mongo <-> springboot problem both in docker
https://fabianlee.org/2018/05/26/docker-using-docker-compose-and-networking-to-link-a-spring-boot-app-to-an-external-service-dependency/
        
Mongo replicaset problem in docker
    https://stackoverflow.com/questions/47998855/connect-to-mongodb-replica-set-running-inside-docker-with-java-windows
    
###Connecting to locahost mongo from a docker container
Apparently there is a private DNS entry that maps docker containers
to the localhost, ie host.docker.internal.  I was having problems
getting this to work.  I did get a container to connect it just blew up trying
to do stuff.  I had to hardcode the spring.data.mongod.host in the application.yml

Good stuff here https://hub.docker.com/_/mongo/

### Using a Mongo container
I created a dir for the mongo files.  Using this command it appears to start it with a mount
to local storage so restarts won't lose data.  I added a new DB and a collection from a CLI.  It survived
a container restart.
` docker run -d -p 27017:27017 -v ./data:/data/db mongo`

-p maps external port to container port, the -v is the volume.  the ./data
is from the local host (and you run it from the location you want), the /data/db is the container
mongo mount point.  the 'mongo' is the name to use for the container, so our microservice
can talk to it easily

### Docker Compose
I will use this to startup and stitch the two containers.  Looks like docker stack will replace this.  Will get 
this to work, then migrate to swarm.  maybe kubernets.

##TODO
1.  jdk 11 for compiling source, for the base image distro
1.  wiring a docker image to a local mongo
1.  wiring a docker image to a docker mmongo
1.  build the microservice as part of the docker file
1.  gradle command to build and install the image.