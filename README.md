# Bhub challenge

## Prerequisites
To run and build this project, the following dependencies must be installed:
* Java 21 JDK (https://jdk.java.net/21/)
* Maven 3.x (https://maven.apache.org/download.cgi)
* Windows SDK (https://developer.microsoft.com/en-us/windows/downloads/windows-sdk/)
* Git (https://git-scm.com/downloads)
* Docker (https://www.docker.com/products/docker-desktop)

## Develop

To build project:

```
git clone https://github.com/jslsolucoes/bhub.git
cd bhub
mvn clean install docker:build
```

Build will do most of steps like:

    - Build Java project including unit and integration tests
    - Build Docker image with prefix jslsolucoes/bhub:xxx

So you can run project with:

```docker run -p 8081:8081 jslsolucoes/bhub:0.0.1```

When container its running, open your browser and explore api docs:

```http://localhost:8081/swagger-ui/index.html```

You can also check actuactor metrics:

```http://localhost:8081/actuator```

```http://localhost:8081/actuator/health```

```http://localhost:8081/actuator/prometheus```

