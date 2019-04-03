# Getting Started

## Using Gradle

### Run Acceptance Tests (Spock + Groovy)
```
> ./gradlew clean testAcceptance
```

### Target Acceptance Environment
```
> SERVER_BASEURL=http://localhost:9292 ./gradlew clean testAcceptance
```

### Run Unit Tests (JUnit + Java)
```
> ./gradlew clean test
```

## Using Maven

### Run Acceptance Tests (Spock + Groovy)
```
> ./mvnw -Dtest="**/*Spec*.*" clean test
```

### Target Acceptance Environment
```
> ./mvnw -Dtest="**/*Spec*.*" -Dserver.baseUrl=http://localhost:9292 clean test
```

### Run Unit Tests (JUnit + Java)
```
> ./mvnw -Dtest="**/*Test*.*" clean test
```

or

```
> ./mvnw clean test
```
