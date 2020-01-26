# status_checker

## Dependencies
- Java 11
- Maven 3.6

## Build
Run the following command at the root of the project. This 
will produce the jar called StatusChecker-1.0-SNAPSHOT.jar 
under the target folder. 

`mvn clean package`

## Execute
To execute the jar against a remote server host prefix run
the following command. 

`java -jar target/StatusChecker-1.0-SNAPSHOT.jar <serverprefix>`

### Debug logging enabled
If you want to run with debug logs then run with the following
jvm args. Debug will show you progress as the application moves 
through the servers. Info logging is on by default. 

`java -Dorg.slf4j.simpleLogger.defaultLogLevel=debug -jar target/StatusChecker-1.0-SNAPSHOT.jar <serverprefix>`

## Execute local only
On a mac you can easily run the fictitious server by 
the following commands

`cd ./src/test/resources`

`python -m SimpleHTTPServer 8000`

Run the application without providing the host prefix

`java -jar target/StatusChecker-1.0-SNAPSHOT.jar`