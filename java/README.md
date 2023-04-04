# Sample Perfecto + Java Project with Axe integration


This sample project is designed to get you up and running within few simple steps.

Begin with installing the dependencies below, and continue with the Getting Started procedure below.

## Dependencies
There are several prerequisite dependencies you should install on your machine prior to starting to work with this project:

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

* An IDE to write your tests on - [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr) or [IntelliJ](https://www.jetbrains.com/idea/download/#)

* [Maven](https://maven.apache.org/)

Eclipse users should also install:

1. [Maven Plugin](http://marketplace.eclipse.org/content/m2e-connector-maven-dependency-plugin)

2. [TestNG Plugin](http://testng.org/doc/download.html)

IntelliJ IDEA users should also install:

1. [Maven Plugin for IDEA](https://plugins.jetbrains.com/plugin/1166)

TestNG Plugin is built-in in the IntelliJ IDEA, from version 7 onwards.

## Downloading the Sample Project

* Clone this repository.

* After downloading and unzipping the project to your computer, open it from your IDE by choosing the folder containing the pom.xml 

**********************
# Getting Started

## Setup Perfecto Web Capabilities

* Open BaseDriver.java.

* Edit the browser capabilities as applicable.

## Running sample as is

* Run pom.xml with the below maven goals & properties: 

		clean
		install
		-DcloudName=${cloudName}
		-DsecurityToken=${securityToken}
</p>

`where ${cloudName} refers to your perfecto's cloud name. E.g. demo is the cloud name of demo.app.perfectomobile.com and ${securityToken} refers to your perfecto's security token.`

* CI dashboard integration can be integrated with the below maven goals & properties:

		clean
		install
		-DcloudName=${cloudName}
		-DsecurityToken=${securityToken}
		-Dreportium-job-name=${JOB_NAME} 
		-Dreportium-job-number=${BUILD_NUMBER} 
		-Dreportium-job-branch=${GIT_BRANCH} 
		-Dreportium-tags=${myTag}
