# Breakify React + Java Challenge

## Prerequisites
To run and build this project, the following dependencies must be installed in your machine:
* Java 21 JDK (https://jdk.java.net/21/)
* Maven 3.x (https://maven.apache.org/download.cgi)
* Node 20.x (https://nodejs.org/en/download/)
* Windows SDK (https://developer.microsoft.com/en-us/windows/downloads/windows-sdk/)
* Git (https://git-scm.com/downloads)
* Docker (https://www.docker.com/products/docker-desktop)

## Running breakify challenge

```docker run -p 8081:8081 jslsolucoes/react-challenge:0.0.1```

When container its running, open your browser and access the url:

```http://localhost:8081```


## Develop

To build project:

```
git clone https://github.com/jslsolucoes/react-challenge.git
cd react-challenge
mvn clean install docker:build
```

Build will do most of steps like:

    - Build React project (including install and running lint and tests)
    - Build Java project embedded with React project (including tests)
    - Build Docker image with prefix jslsolucoes/react-challenge:xxx


