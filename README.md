# Annotations for JVM-based languages 
[![official JetBrains project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jetbrains/annotations/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.jetbrains/annotations) [![javadoc](https://javadoc.io/badge2/org.jetbrains/annotations/javadoc.svg)](https://javadoc.io/doc/org.jetbrains/annotations)

A set of Java annotations which can be used in JVM-based languages. They serve as an additional documentation and can be 
interpreted by IDEs and static analysis tools to improve code analysis.

[Change Log](CHANGELOG.md) | [Contributing](CONTRIBUTING.md) | [Code of Conduct](CODE_OF_CONDUCT.md)

## Documentation

- [JavaDoc](https://javadoc.io/doc/org.jetbrains/annotations)
- [JavaDoc](https://javadoc.io/doc/org.jetbrains/annotations-java5) for legacy Java 5.0 compatible package

## Using the annotations
The annotations are published on [Maven Central](https://repo1.maven.org/maven2/org/jetbrains/annotations/). To add a dependency
using gradle write the following in the `build.gradle` file:
```
dependencies {
    compileOnly 'org.jetbrains:annotations:23.0.0'
}

```
To add a dependency using Maven, write the following in `pom.xml`:
```xml
<dependency>
  <groupId>org.jetbrains</groupId>
  <artifactId>annotations</artifactId>
  <version>23.0.0</version>
  <scope>provided</scope>
</dependency>
```

`annotations` artifact requires JDK 1.8 or higher. If your project is compiled using JDK 1.5, 1.6 or 1.7 you can use 
the `annotations-java5` artifact instead. Please note that `annotations-java5` artifact is considered a legacy, so
 most of new annotations will appear in the `annotations` artifact only.
