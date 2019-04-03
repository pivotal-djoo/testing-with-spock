# Getting Started

## Using Gradle

### Run Acceptance Tests (Spock + Groovy)
```
> ./gradlew testAcceptance
```

### Run Unit Tests (JUnit + Java)
```
> ./gradlew test
```

## Using Maven

### Run Acceptance Tests (Spock + Groovy)
```
> ./mvnw -Dtest="**/*Spec*.*" test
```

### Run Unit Tests (JUnit + Java)
```
> ./mvnw -Dtest="**/*Test*.*" test
```

or

```
> ./mvnw test
```
