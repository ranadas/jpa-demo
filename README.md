# Overview

**JPA 2.1 has introduced the Entity Graph feature as a more sophisticated method of dealing with performance loading.**

It allows defining a template by grouping the related persistence fields which we want to retrieve and lets us choose the graph type at runtime.

**The main goal of the JPA Entity Graph is then to improve the runtime performance when loading the entity's related associations and basic fields.**

Briefly put, the JPA provider loads all the graph in one select query and then avoids fetching association with more SELECT queries. This is considered a good approach for improving application performance.

https://medium.com/swlh/jpa-entity-graphs-with-spring-boot-30cb110ba4f8

