FROM openjdk:8-jre-slim

WORKDIR /usr/share

# Add the project jar & copy dependencies
ADD  target/PartnerPortalV1-0.0.1-SNAPSHOT.jar PartnerPortalV1-0.0.1-SNAPSHOT.jar

# Add the suite xmls
ADD login.xml login.xml
ADD homepage.xml homepage.xml
ADD sqpage.xml sqpage.xml

# Command line to execute the test

ENTRYPOINT java -cp PartnerPortalV1-0.0.1-SNAPSHOT.jar -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE

