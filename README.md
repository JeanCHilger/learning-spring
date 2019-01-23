# learning-spring
A repository made with the intention to learn some functionalities of the **Spring Framework** with a little bit of **Angular**. The files are not ordered by complexity. To see more details about an specific project click on the link. 

The list of "resources" or "features" used shows what is described and explained (in a introductory manner) inside the code, in form of comments. To see more complex information about them or about the concepts involved please see the [Spring documentation](https://spring.io/docs). You also may find very useful guides and tutorials in [Spring guides](http://spring.io/guides).

1. [hello-world](https://github.com/JeanCHilger/learning-spring/tree/master/hello-world):
    * Simple spring boot starter project created with **spring initializr** to manage simple **GET requests**.
    * Uses **Spring Web** module, and explores the **@RestController**, **@RequestParams** and other basic annotations.

1. [spring-jpa-hw](https://github.com/JeanCHilger/learning-spring/tree/master/spring-jpa-hw):
   * Simple project using **Spring Data JPA** and the in memory data base H2.
   * Uses **CrudRepository** interface and explores the **save** and **find** features of it.
   
1. [inventory-api](https://github.com/JeanCHilger/learning-spring/tree/master/invetory-api):
   * Project to control an inventory implementing a simple **CRUD with MySQL**.
   * Uses an **phpMyAdmin MySQL database** and the Spring Data JPA's **CrudRepository**.
   * Uses the **Spring Web** module to provide URL for CRUD the data.
   
1. [rest-service](https://github.com/JeanCHilger/learning-spring/tree/master/rest-service):
   * A RESTful payroll management system using some of Spring features that help with the REST stuff.
   * Uses the **Spring Web**, **Spring Data JPA**, **Spring HATEOAS** and the **Lombok** modules.
   
1. [security-example](https://github.com/JeanCHilger/learning-spring/tree/master/security-example):
   * A simple application with a public "home" page and a secret "hello user" page. When trying to access the "hello" page an authentication will be requested. Only authenticated users can see the "hello" page.
   * Uses the **Spring Security** and **Spring Web** modules.

Feel free to contribute with more material about Spring.
