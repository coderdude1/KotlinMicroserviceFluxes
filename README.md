# Scope
This is a work in progress experimenting with TDD, kotlin and spring boot 2, spring framework 5 and other stuff that 
strikes my fancy

# TDD book
I've been reading Growing Object-Oriented Software, Guided by Tests to learn how to better apply TDD for a work project. 
This project will offer some freedom to experiement with the concepts better, and to geek on some other fun stuff.  For
now I'm gonna use spcok as thats something I'm familar with, but at some point may dabble with Spek, and other kotlin
based testing frameworks

# Some other resources
Kotlin spring demo.  lots of stuff to learn here
http://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way
https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html
https://medium.com/@oscdj113/spring-boot-2-with-webflux-kotlin-bb1a71167591

React, kotlin and spring 
https://amarszalek.net/blog/2018/04/02/reactive-web-services-kotlin-spring-boot-2/

testing with WebClient and WebTestClient
https://www.baeldung.com/spring-5-webclient
https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/

This has some nice examples of WebClient, including the GET, PUT, POST, and DELETE
https://dzone.com/articles/doing-stuff-with-spring-webflux

Very good overview of webflux/reactive, mentions kotlin 
http://projectreactor.io/docs/core/release/reference/


# Swagger
https://github.com/springfox/springfox/tree/master/springfox-spring-webflux

swagger with webfluxes
https://github.com/springfox/springfox/issues/1773  check the last entry for 3 pom entries, and one config
    No operations defined in spec!


I think it does not recognize the spring kotlin DSL for routes.  it looks for @RestController

This https://dzone.com/articles/build-rest-apis-with-kotlin-spring-5-webflux-and-p looks like it shows a way to make
it work.  He uses the OpenAPI swagger stuff
## Issues
not sure if kotlin or my base url path for the only controller.  I get the swagger ui page but not the controller
and it's endpoints.  It doesn't see it for some reason


#Interesting notes
1. spring has a kotlin Bean definition DSL, router DSL
1. kotlin reactive extensions are part of the spring stuff, it adds a toMono() and toFlux() methods to all objects, ie "blah string".toMono(), etc
1. in the gradle file note the jsr305.  spring5 uses these now, and kotlin can use them to interact with java safer

# Issues
1. finding issues testing spring webflux with spock

## Swagger, Kotlin, and webfluxes
1.  had to load the 3.0 snapshot version of springfox, most articles/issue trackers mention it makes it easier
to use webfluxes
1.  I got it to work, mostly.  It seems to not be able to read the new spring kotlin DSL for controllers.
a solution I've read about seems to be using the openAPI api.json stuff
    * https://github.com/springfox/springfox/issues/1773
    * https://github.com/springfox/springfox/issues/2360
    * https://springfox.github.io/springfox/docs/snapshot/#docket-spring-java-configuration 6.8.2
    * Check this out?  https://piotrminkowski.wordpress.com/2019/01/15/kotlin-microservice-with-spring-boot/
# TODOS
1. add actuator endpoints
1. add some non-webflux endpoints
1. add swagger.  had issues with kotlin webflux dsl (it started, didn't see the endpoints)
1. refactor into a mono repo for several services
1. add reactive mongo.
    1. local mongo
    1. embedded mongo for test, maybe running
1. dockerfy each service, and mongo
1. mongo transactions
    1 https://www.baeldung.com/spring-data-mongodb-transactions
    
    
## Testing goals
1.  basic spock unit tests
1.  spock integration tests (not testing endpoints, just handlers down.)
1.  spock acceptance tests - ie tests that can hit a running service at a known port/address
1.  spring and spock controller tests (with and without running service instances)


#Docker
some resources. 
    [Memory management in a container](https://medium.com/@yortuc/jvm-memory-allocation-in-docker-container-a26bbce3a3f2)
    [spring boot block for docker](https://spring.io/blog/2018/11/08/spring-boot-in-a-container)
##TODO: 
1.  versioning jdk.  upgrade to 11
1.  connect to external mongo instance
    1. connect to local mongo instance
    1. connect to mongo container
1.  build from gradle task.

