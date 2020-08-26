# microprofile-starter

Welcome to the MicroProfile starter application! This is a sample of what a basic microservice might look like. It uses Maven to build a basic REST microservice on Open Liberty. Not familiar with Maven? Never heard of REST? No problem. This readme is meant to walk you through these technologies and concepts so you can gain a basic understanding of what they are. 

## Maven

Maven is a build tool used to manage the building and running of your application. It will compile your code, deploy it to an app server, run tests, package the WAR and perform any other tasks you may for your app. These tasks are all invoked via the Maven command line and can either be directly or through what's called the "Maven build lifecycle" (more on that below). Additionally, these tasks are all configured by "plugins" that you load into Maven. Some of these plugins are implicitly loaded into Maven (like the compile plugin that compiles your code). Other third party plugins can be loaded manually as desired.

Maven also allows you to specify libraries that your app may need which are pulled from a remote repository called Maven Central.

#### POM

All Maven configuration is controlled through one file called the "pom.xml." This is where you specify your apps dependencies and any plugins needed for your app's build lifecycle. Let's take a look at the pom.xml for this app.

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

 These properties are relative to this pom file and you can set anything you want here. You'll notice later on that things like "http.port" are used later on in the pom. Also worth noting is that Maven has a set of built in variables. For example, `${project.artifactId}` refers to the artifactId specified at the top of the file.

 Next comes our `<dependencies>` block. This is where you configure any libraries that your app needs. For example, if you are familiar with Junit for running unit tests, there is a Junit dependency configured here:

 ```xml
 <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.6.0</version>
    <scope>test</scope>
</dependency>
```

Each dependency comes from [Maven Central](https://mvnrepository.com/repos/central) and is configured with it's groupId, artifactId and version (similar to how our app is tagged). Additionally, dependencies have a "scope" property. This basically tells Maven when in the build lifecycle to use this library (compile time? test time? etc). More on the build lifecycle later.

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

If you haven't caught on by now, all Maven entities have a groupId, artifactId and version. Plugins have a bunch of additional stuff you can add including a "configuration" block where you can specify all sorts of things specific to that plugin. In this case, we are telling the maven-war-plugin to fail if a web.xml is missing from the app and not to package up the pom.xml when building our official application war file.  

#### Build lifecycle

So we mentioned this "Maven build lifecycle" a few time. Maven has a built in set of "phases" that it runs in order. You can find more info on that [here](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) but the main phases are `compile`, `test`, `package`, `install`. If you invoke any of these phases, for example `mvn package`. Maven will go through all phases up until and including the package phase. At each phase, it will run any "goals" bound to that phase. These are either default goals Maven has or any goals specified by the plugins you've configured.

#### CLI commands

So let's see this in action.

1. Follow the "Installation" section here: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html.  (You can also check out the rest of that article for more good stuff on getting started with Maven).

2. Download or clone this git project to have a local copy. If you are not familiar with Git, you can find a nice intro guide [here](https://guides.github.com/activities/hello-world/)

3. `cd` into the project (where the pom.xml is)

4. Run 'mvn package' (we will do some fancier stuff later on)

When this completes you will see that maven created a new "target" directory. This is where all the build output will go including your newly built "starter-app.war" application!


## Open Liberty

#### App server basics

#### Liberty Maven plugin

< show plugin in pom.xml >

#### Server config

< show server.xml in src >


## REST

#### CRUD model

#### HTTP methods

< walk through code >

1. jax-rs feature in server.xml
2. REST classes
3. Start app and hit url


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
