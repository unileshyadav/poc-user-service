# cymmetri-microservices-sample

This is a sample application for developing cymmetri-microservice.

#### System Requirements

- OpenJDK 11
- Maven 3.3+

 Before begin, you should check your current Java installation by using the following command:

``
$ java -version
``

and also check current maven installation using following command:

``
$ mvn -v
``

#### Configuring Sample Service

Cymmetri-microservice application dependencies use the ``com.cymmetri`` groupId. Typically, your Maven POM file inherits from the ``cymmetri-microservices-bom`` project and declares dependencies.

The following listing shows a typical ``pom.xml`` file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	
	<parent>
		<groupId>com.cymmetri</groupId>
		<artifactId>cymmetri-microservices-bom</artifactId>
		<version>${revision}</version>
		<relativePath>../cymmetri-microservices-bom</relativePath> <!-- lookup parent from repository -->
	</parent>

	<artifactId>user</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<packaging>war</packaging>

	<name>user</name>
	<description>User Microservice</description>

	<properties></properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
        ...

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

Clone or download following cymmetri repositories -

- [cymmetri-microservices-bom](https://github.com/Unotechsoftware/cymmetri-microservices-bom)
- [cymmetri-microservices-sample](https://github.com/Unotechsoftware/cymmetri-microservices-sample)


> Place both repositories at adjustant location, following is the basic directory structure:

```language
├── cymmetri-microservices-bom
│   ├── .gitignore
│   ├── pom.xml
│   └── README.md
└── cymmetri-microservices-sample
    ├── .gitignore
    ├── pom.xml
    ├── README.md
    └── src
        ├── main
        │   ├── java.com.cymmetri.user
        │   │               ├── config
        │   │               │   └── SwaggerConfig.java
        │   │               ├── dto
        │   │               │   └── Response.java
        │   │               ├── endpoint
        │   │               │   └── UserController.java
        │   │               ├── entity
        │   │               │   └── User.java
        │   │               ├── exception
        │   │               │   ├── CustomException.java
        │   │               │   ├── ErrorCode.java
        │   │               │   ├── ErrorProducer.java
        │   │               │   ├── RestResponseEntityExceptionHandler.java
        │   │               │   └── UserNotFoundException.java
        │   │               ├── repository
        │   │               │   └── UserRepository.java
        │   │               ├── service
        │   │               │   ├── impl
        │   │               │   │   └── UserServiceImpl.java
        │   │               │   └── UserService.java
        │   │               ├── ServletInitializer.java
        │   │               └── UserApplication.java
        │   ├── resources
        │   │   ├── application.properties
        │   └── webapp
        └── test
            └── java.com.cymmetri.user
                            └── UserApplicationTests.java
```
__Compile and Execute__

From console nevigate to ``cymmetri-microservices-sample`` and execute following command:

``
$ mvn clean compile package spring-boot:run
``