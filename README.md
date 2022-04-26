# Employee Management System
Sample demo to deploy spring boot to AWS Elastic Beanstalk on t2.micro EC2 instance

### Steps on AWS Management Console:
* Create AWS account, make sure to add billing details to have access to AWS Management Console and to get started
* When login into AWS Management Console, one logs in as ROOT USER.
* Create IAM User under user group and assign appropriate policy. (I created user and assigned AdminstratorAccess for now)
* Create environment under EBS -> Environments
* Create application under EBS -> Applications for the created environment.
* Upload the appropriate jar for the spring boot application and wait for it to get deployed.


### AWS Details
* Server accessed at : http://samplespringbootawsebs-env-1.eba-epr3sd2m.us-east-1.elasticbeanstalk.com/actuator/health
    *Currently terminated all AWS instances, won't be able to access the above url recommend to use h2 profile by default.

### Server Details:
OpenAPI Specification (OAS3) : http://localhost:8080/swagger-ui/index.html

H2 embedded database console: http://localhost:8080/h2-console

Some Actuator endpoints:
 1. http://localhost:8080/actuator/health
 2. http://localhost:8080/actuator/info
 3. http://localhost:8080/actuator/env
 4. http://localhost:8080/actuator/beans
 5. http://localhost:8080/actuator/loggers
 6. http://localhost:8080/actuator/metrics
 7. http://localhost:8080/actuator/threaddump
