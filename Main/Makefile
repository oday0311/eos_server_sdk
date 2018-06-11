install:
	mvn clean install -DskipTests

docker:
	mvn docker:build

tag:
	mvn release:prepare -B
	rm release.properties
	rm pom.xml.releaseBackup

run:
	java -Dserver.port=8080 -jar target/demo-0.0.1-SNAPSHOT.jar

debug:
    java -Dserver.port=8080 -jar target/demo-0.0.1-SNAPSHOT.jar --debug

health:
	curl -i http://localhost:8080/health
