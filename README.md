# java-http-server [![Build Status](https://travis-ci.org/mbdebbeler/java-http-server.svg?branch=master)](https://travis-ci.org/mbdebbeler/java-http-server)

## Description
An HTTP server in Java.

## Installation
To clone the project:
```
git clone https://github.com/mbdebbeler/java-http-server.git
```

## Running the server
To server on default port, 5000:
```
cd java-http-server
./gradlew build
bash scripts/start_local_server.sh
```

## Testing

#### Unit Tests:
```
./gradlew test
```
#### Acceptance Tests:
Start the server on the default port (5000). 

From the root of the project, call
```
bash scripts/clone_and_run_image_acceptance_tests.sh
```

## Built With
 - Java
 - Gradle
 - JUnit
 - Spinach 

## Deployment
If you're a future apprentice reading this, I deployed this to an AWS EC2 instance! Slack me and I will be so happy to walk you through the tricky parts to the best of my memory. 
