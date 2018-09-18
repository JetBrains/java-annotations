# Annotations for JVM-based languages 
[![official JetBrains project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jetbrains/annotations/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.jetbrains/annotations)

A set of Java annotations which can be used in JVM-based languages. They serve as an additional documentation and can be 
interpreted by IDEs and static analysis tools to improve code analysis.

## Using the annotations
The annotations are published on [Maven Central](http://repo1.maven.org/maven2/org/jetbrains/annotations/) and [JCenter](https://jcenter.bintray.com/org/jetbrains/annotations/). To add a dependency
using gradle write the following in `build.gradle` file:
```
dependencies {
    compile 'org.jetbrains:annotations:16.0.3'
}

```
To add a dependency using Maven write the following in `pom.xml`:
```xml
<dependency>
  <groupId>org.jetbrains</groupId>
  <artifactId>annotations</artifactId>
  <version>16.0.3</version>
</dependency>
```

`annotations` artifact require JDK 1.8 or higher. If your project is compiled using JDK 1.5, 1.6 or 1.7 you can use 
`annotations-java5` artifact instead.


    
