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


Swagger
https://github.com/springfox/springfox/tree/master/springfox-spring-webflux

#Interesting notes
1. spring has a kotlin Bean definition DSL, router DSL
1. kotlin reactive extensions are part of the spring stuff, it adds a toMono() and toFlux() methods to all objects, ie "blah string".toMono(), etc
1. in the gradle file note the jsr305.  spring5 uses these now, and kotlin can use them to interact with java safer

# Issues
1. finding issues testing spring webflux with spock

# TODOS
1. add actuator endpoints
1. reactive - started
1. refactor into a mono repo for several services
1. add reactive mongo.
    1. local mongo
    1. embedded mongo for test, maybe running
1. dockerfy each service, and mongo
1. mongo transactions
    1 https://www.baeldung.com/spring-data-mongodb-transactions
