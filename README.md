# Bhub challenge

## Prerequisites
To run and build this project, the following dependencies must be installed:

* Windows Or Linux

* Java 21 JDK (https://jdk.java.net/21/)

Check if java is installed using terminal:

```java -version```

Should output something like:

```
java version "21" 2023-09-19 LTS
Java(TM) SE Runtime Environment (build 21+35-LTS-2513)
Java HotSpot(TM) 64-Bit Server VM (build 21+35-LTS-2513, mixed mode, sharing)
```

* Maven 3.x (https://maven.apache.org/download.cgi)

Extract maven to a folder and add it to your PATH environment variable.

```set PATH=%PATH%;installdir\apache-maven-3.x.x\bin```

Check if maven is installed using terminal:

```mvn --version```

Should output something like:

```
Apache Maven 3.x.x (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: D:\softwares\apache-maven-3.6.3\bin\..
Java version: 21, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-21
Default locale: pt_BR, platform encoding: UTF-8
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```
* Docker (https://www.docker.com/products/docker-desktop)

Check if docker is installed using terminal:

```docker --version```

Should output something like:

```
Docker version 20.10.8, build 3967b7d
```


## Develop

To build project:

Extract project bhub.zip

```
cd bhub
mvn clean install docker:build
```

Build will do most of the steps like:

    - Build Java project including unit and integration tests
    - Build Docker image with prefix jslsolucoes/bhub:xxx

So you can run project with:

```docker run -p 8081:8081 jslsolucoes/bhub:0.0.1```

When container is running, open your browser and explore api docs:

```http://localhost:8081/swagger-ui/index.html```

You can also check for actuactor metrics:

```http://localhost:8081/actuator```

```http://localhost:8081/actuator/health```

```http://localhost:8081/actuator/prometheus```

