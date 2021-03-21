# myRetail-api
Case Study for Target Interview

# Technology Stack
1. Java 8
2. Spring Boot / Spring MVC
3. MongoDB
4. JUnit 5
5. Maven

# Setup Environment
## Java
This project uses Java 1.8.
Download Java via openjdk : https://adoptopenjdk.net/installation.html

Once installed, verify by typing 'java -version' in your terminal.  
Example output 
>openjdk version "1.8.0_275"<br/>
>OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_275-b01)<br>
>OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.275-b01, mixed mode)<br>


## MongoDB
This project uses MongoDB v4.4.3
Instructions for installing MongoDB can be found here : https://docs.mongodb.com/manual/installation/

Once installed, verify by typing 'mongo -version' in your terminal.  
Example output
>Build Info: {<br/>
>    "version": "4.4.3",<br/>
>    "gitVersion": "913d6b62acfbb344dde1b116f4161360acd8fd13",<br/>
>    "modules": [],<br/>
>    "allocator": "system",<br/>
>    "environment": {<br/>
>        "distarch": "x86_64",<br/>
>        "target_arch": "x86_64"<br/>
>    }<br/>
>}<br/>

Ensure the MongoDB service is running
The data used for this project is either seeded at the start of the application, or is obtained from an external source.  As long as the MongoDB service is running on port 27017, no further setup is required.

## Maven
This project uses a maven wrapper, and therefore, installing Maven is not necessary.  All maven commands can be run by using ./mvnw instead of mvn

# Clone repository by copy/pasting this link in your terminal https://github.com/jpowell0721/myRetail-api.git
To run unit tests, navigate to the folder you cloned the project in and type './mvnw test'

To run the project and start the embedded tomcat server, type './mvnw spring-boot:run'

# Example Postman queries and returns. 
[Successful GET request](images/get_success.png)


