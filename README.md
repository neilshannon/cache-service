Cache Service
=============

## Summary
The CacheService is intended to be used to invalidate cache entries upon updates to product, user, merchant, inventory, 
or pricing data.  Put this jar on your classpath in order to provide a CacheService to your Spring Boot applications.  
Spring AutoConfiguration will default to a Varnish implementation.

## Operation

#### To build
`./gradlew clean build`

#### To publish locally
`./gradlew install`

#### To test
`./gradlew test`