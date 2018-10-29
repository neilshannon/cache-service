Cache Service (Spring Boot Autoconfiguration Example)
==

## Summary
The CacheService is intended to be used to invalidate cache entries upon updates resource representations via a 
REST HTTP service.  Put this jar on your classpath in order to provide a CacheService to your Spring Boot applications.  
Spring AutoConfiguration will default to a Varnish implementation which sends a POST to a Varnish URI with a special 
HTTP header to invalidate cache entries.

## Usage
Add the build artifact (jar) on the classpath of the application that will use the CacheService.
Ensure that the environment variable "VARNISH_URI" is set.

## Operation

#### To build
`./gradlew clean build`

#### To publish locally
`./gradlew install`

#### To test
`./gradlew test`