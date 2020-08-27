# microprofile-starter

Welcome to the MicroProfile starter application! This is a sample of what a basic microservice might look like. It uses Maven to build a basic REST microservice on Open Liberty. Not familiar with Maven? Never heard of REST? No problem. This readme is meant to walk you through these technologies and concepts so you can gain a basic understanding of what they are.

## Maven

Maven is a build tool used to manage the building and running of an application. It will compile your code, deploy it to an app server, run tests, package the WAR and perform any other tasks you may need when developing. These tasks are all invoked via the Maven command line and can either be issued directly or through what's called the "Maven build lifecycle" (more on that below). Additionally, these tasks are all configured by "plugins" that you load into Maven. Some of these plugins are implicitly loaded (like a compile plugin that compiles your code). Other third party plugins can be loaded manually.

Maven also allows you to specify libraries that your app may need which are pulled from a remote repository called Maven Central.

#### POM

All Maven configuration for an app is controlled through one file called the "pom.xml." This is where you specify your app's dependencies and any plugins needed for its build lifecycle. Let's take a look at the pom.xml for this app.

The first thing you'll notice in the pom file is a set of descriptors for your app:

```xml
    <groupId>microprofile.starter</groupId>
    <artifactId>starter-app</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
```

This basically tags your app with a group id, name (artifactId), and version. It also specifies that you want your app packaged as a war (as opposed to a jar if this was a library rather than a runnable app).

Next you'll see a block of properties:

```xml
<properties>
     <maven.compiler.target>1.8</maven.compiler.target>
     <maven.compiler.source>1.8</maven.compiler.source>
     <http.port>9080</http.port>
     <https.port>9443</https.port>
     <app.name>${project.artifactId}</app.name>
 </properties>
 ```

 These properties are relative to this pom file, and you can set anything you want here. You'll notice that things like "http.port" are used later on in the pom. Also worth noting is that Maven has a set of built in variables. For example, `${project.artifactId}` refers to the artifactId specified at the top of the file.

 Next comes our `<dependencies>` block. This is where you configure any libraries that your app needs. For example, if you are familiar with JUnit for running unit tests, there is a JUnit dependency configured here:

 ```xml
 <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.6.0</version>
    <scope>test</scope>
</dependency>
```

Each dependency comes from [Maven Central](https://mvnrepository.com/repos/central) and is configured with its groupId, artifactId and version (similar to how our app is tagged). Additionally, dependencies have a "scope" property. This basically tells Maven when in the build lifecycle to use this library (compile time? test time? etc). More on the build lifecycle later.

Last, we configure our plugins. These plugins instruct Maven what to do and provide commands (called goals) that Maven will run. Goals can either be run directly ("Hey Maven, go do this thing"), or as part of the Maven build lifecycle ("Hey Maven, when you are compiling my code, also run this command").

Let's take a look at the war plugin defined here:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
        <failOnMissingWebXml>false</failOnMissingWebXml>
        <packagingExcludes>pom.xml</packagingExcludes>
    </configuration>
</plugin>
```

If you haven't caught on by now, all Maven entities have a groupId, artifactId and version. Plugins have a bunch of additional stuff that you can add including a "configuration" block where you can specify all sorts of things specific to that plugin. In this case, we are telling the maven-war-plugin to fail if a web.xml is missing from the app and not to package up the pom.xml when building our official application war file.  

#### Project structure

Maven requires a certain structure to your application. You can of course change this, but by default your app should look like this:

```
.
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── dev
    │   │       └── microprofile
    │   │           └── starter
    │   │               ├── StarterApplication.java
    │   │               └── StarterResource.java
    │   ├── liberty
    │   │   └── config
    │   │       └── server.xml
    │   └── webapp
    │       ├── WEB-INF
    │       │   └── beans.xml
    │       └── index.html
    └── test
        └── java
            └── it
                └── dev
                    └── microprofile
                        └── starter
                            └── EndpointTest.java

```

1. Your root project directory has the application's pom.xml and a `src` directory.

1. The `src` directory has two subdirectories: `main` (where your app code and configuration lives) and `test` (where your test code lives).

1. All Java code goes under `src/main/java`, HTML and webapp config goes under `src/main/webapp` and in this case, all Open Liberty server config goes under `src/main/liberty`

#### Build lifecycle

So we mentioned this "Maven build lifecycle" a few times. Maven has a built in set of "phases" that it runs in order. You can find more info on that [here](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html), but the main phases are `compile`, `test`, `package`, and `install`. If you invoke any of these phases, for example `mvn package`, Maven will go through all phases up to and including the package phase. At each phase, it will run any "goals" bound to that phase.

#### CLI commands

The Maven CLI is your gateway to using Maven. All commands begin with `mvn` and must be run from the directory where your application's pom.xml lives. Typically, you would run a Maven build phase as described above. For example, `mvn package` will run the "package" phase and all goals associated with the phases up to "package". You can also invoke plugin goals directly by specifying the plugin name followed by the goal you want. For example, to just run the default "compile" goal of the compiler plugin, you can issue `mvn compiler:compile`.

#### Try it out

So let's see this in action.

1. Follow the "Installation" section here: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html.  (You can also check out the rest of that article for more good stuff on getting started with Maven).

1. Download or clone this git project to have a local copy. If you are not familiar with Git, you can find a nice intro guide [here](https://guides.github.com/activities/hello-world/)

1. On the command line, `cd` into the microprofile-starter directory.

1. Run 'mvn package'

In the output, you will see Maven compiling code and packaging the war file. When this completes you will see a newly created "target" directory. This is where all the build output will go including your "starter-app.war" application!

## Open Liberty

#### App server basics

#### Liberty Maven plugin

< show plugin in pom.xml >

#### Server config

< show server.xml in src >


## REST

REST stands for REpresentational State Transfer. It is a style of communication over HTTP, and simply put, is a way to expose an API for your app over the internet.

#### HTTP methods

REST APIs communicate over HTTP and therefore use any of the 4 HTTP methods: GET, POST, PUT, and DELETE.

#### CRUD model

CRUD stands for Create, Read, Update, and Delete, and is a model for how to manage "stuff" over the internet in the simplest way possible. Ideally, these tasks map to the 4 HTTP methods mentioned above:

* Create -> POST
* Read -> GET
* Update -> PUT
* Delete -> DELETE

REST APIs typically follow this CRUD model and are used to simply manage the lifecycle of items.

#### Statelessness

REST APIs are stateless. That means that calling the API for a particular app simply triggers a task to be performed (create, read, update, or delete something), but does not change the "state" of the application. For example, let's say there is an app that manages pages in a book. You can call the app and say "give me page 1" and it gives you page 1. If you want the next page you may want to say "give me the _next_ page", but the app has no idea what page you are on - it is stateless. Instead, you say, "give me page 2" and it gives you page 2.

#### JAX-RS

JAX-RS is the Java specification for creating REST APIs. It allows you to route HTTP requests directly to classes and methods you have in your Java code.

#### Look at the code

So let's see what a REST API looks like in this Java application.

1. Go into the `src/main/java/dev/microprofile/starter` directory.

In here you will see two java classes: `StarterApplication.java` and `StarterResource.java`.

2. Open `StarterApplication.java`

As you can see, there is not much in this class. This simply establishes that this Java package will provide a REST "Application". It's worth noting here that the term "Application" is a JAX-RS entity and does not refer to our app itself. One Java app can have multiple JAX-RS "Applications". Don't worry if you find this confusing, it will make more sense later on.

While this class is mostly empty, there is one thing of value here:

```java
@ApplicationPath("/starter")
```

This annotation on the class sets a root "path" for this REST Application called "starter". This is a URL path accessible over HTTP. For example, this particular REST Application is accessible here: `http://localhost/starter`. Other REST Applications would be accessible on different URL paths and is how one Java app can have multiple REST "Applications."

3. Now open `StarterResource.java`

This is the meat of your REST Application and is where you define all of the sub-paths and API methods. Let's take a look.

* At the class level, we added another "path" to our URL: `@Path("/resource")`. That means that any method in this `StarterResource` class is accessible here: `http://localhost/starter/resource`

* Next, we have a method defined called `getRequest()`:

```java
@GET
public String getRequest() {
    return "StarterResource response";
}
```

The  `@GET` annotation means that this Java method is accessible at our URL path (`http://localhost/starter/resource`) via the HTTP "GET" method. In this case, we are getting a simple string response.

4. If the server isn't already started, issue `mvn liberty:dev` on the command line from the directory containing our pom.xml.

5. Open a browser, and go to `http://localhost:9080/starter/resource`.

You should see the "StarterResource response" string printed to the screen.


Next we are going to look at how REST APIs are used for communication between apps - an important principle on microservices.


## Microservices

< basic concepts - REST communication, multi language, 1 app per server, etc >

#### MicroProfile

< walk through code >
1. mp features in server.xml

## Additional Resources

- Link to OL site
- Link to Maven doc
- Link to Liberty Maven plugin
- Link to OL guides
