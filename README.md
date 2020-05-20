# Formula System
## System for monitoring bolid parameters

The system consists of three applications designed to monitor the parameters of the formula 1 engine. To create it, Kafka was used to enable communication between other applications in the system. The whole was created in Java 8 and Spring Framework.

## Application description 
### Bolid App
First application generate data and send it to Kafka server. Expect that, user can send request to service bolid and receive message from team about exceeded engine parameter values.

### Logger App
Second application collecting data and save it to txt file. Example file is presented below.

### Admin App 
Third application allows to receive all request from Bolid App and react to them. Depend on team manager decision can be positive or negative. Expect that, application sends message to Bolid App about exceeded engine parameters.

## Endoint to send request
This link allows to send a query to the team if the driver can come down to pitstop.  
Method GET: http://localhost:8080/pitstop

## Graphics
### System Structure
![System Structure](https://github.com/BrunonLemanski/formula-system/blob/master/images/image_1.png?raw=true)

### Bolid Logs file
![Bolid Logs](https://github.com/BrunonLemanski/formula-system/blob/master/images/image_2.png?raw=true)

### Race Logs file
![Race Logs](https://github.com/BrunonLemanski/formula-system/blob/master/images/image_3.png?raw=true)
