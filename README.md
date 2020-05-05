# Overview

**JPA 2.1 has introduced the Entity Graph feature as a more sophisticated method of dealing with performance loading.**

It allows defining a template by grouping the related persistence fields which we want to retrieve and lets us choose the graph type at runtime.

**The main goal of the JPA Entity Graph is then to improve the runtime performance when loading the entity's related associations and basic fields.**

Briefly put, the JPA provider loads all the graph in one select query and then avoids fetching association with more SELECT queries. This is considered a good approach for improving application performance.


## Links to refer -
* [Original Article](https://medium.com/swlh/jpa-entity-graphs-with-spring-boot-30cb110ba4f8) 
* [Integration Testing Spring Boot Microservices](https://medium.com/@mohamadalaloush/integration-testing-spring-boot-microservices-docker-compose-2a8313361dab)
  
  [Code of link above](https://github.com/MemoAlfa/integration-test-demo)
  
* [JPA Entity Graph Baledung](https://www.baeldung.com/jpa-entity-graph)

* [Test Containers](https://www.testcontainers.org/)
* [Test Containers - eg.1](https://github.com/drjunior90/testcontainers-demo)
* [Test Containers - eg.2](https://github.com/eugenp/tutorials/tree/master/testing-modules/test-containers)

*[Docker Compose Rule](https://blog.codecentric.de/en/2017/03/writing-integration-tests-docker-compose-junit/)
