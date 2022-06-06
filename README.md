# BetVictor assessment
Small Spring Boot application which allows to users to exchange messages through an ActiveMQ broker.

## How to build the application
Being a simple Spring Boot application using maven as build tool, the build is very simple.

Run the following command: `mvn clean install` which will generate a <i>fat jar</i> (it contains all the necessary dependencies).

## How to deploy the application
Run the following command: `java -jar betvictor-assessment-1.0.0.jar` on the machine you want the application to run.

An ActiveMQ broker is also required on the machine running on the default port, i.e. `61616`  
A quick way to do that is by using docker:  
`docker pull rmohr/activemq`  
`docker run -p 61616:61616 -p 8161:8161 rmohr/activemq`  
Or see a detailed guide [here](https://activemq.apache.org/getting-started).

## Testing strategy
As it is a very small application there are no unit tests but 2 integration/component tests within `ApplicationTest` which are 
mostly configured by Spring: using an embedded broker and an in-memory database.
