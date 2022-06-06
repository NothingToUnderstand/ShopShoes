# Read Me First

The following was discovered as part of building this project:

* The following dependencies are not known to work with Spring Native: 'Apache Freemarker, Spring Configuration
  Processor, Spring Batch, Spring Boot DevTools, Spring Web Services'. As a result, your application may not work as
  expected.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Apache Freemarker](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Native Reference Guide](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#howto-batch-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#using-boot-devtools)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Validation](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-validation)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-webservices)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)

### Additional Links

These additional references should also help you:

* [Configure the Spring AOT Plugin](https://docs.spring.io/spring-native/docs/0.9.1/reference/htmlsingle/#spring-aot-maven)

## Spring Native

This project has been configured to let you generate a lightweight container running a native executable. Docker should
be installed and configured on your machine prior to creating the image,
see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.9.1/reference/htmlsingle/#getting-started-buildpacks)
.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 web:0.0.1-SNAPSHOT
```
