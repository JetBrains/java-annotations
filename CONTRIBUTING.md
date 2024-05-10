### How to contribute
If you are not a JetBrains' employee you must sign [Contributor License Agreement](https://www.jetbrains.org/display/IJOS/Contributor+Agreement) first. 
You may do it [electronically](https://www.jetbrains.com/agreements/cla/). After that send your changes as a pull request or commit them directly to a branch.

### Versioning
Update `projectVersion` property in gradle.properties file before doing the change:
* increase the patch component of the version if you're changing a javadoc
* increase the minor component of the version if you're adding a new element into an existing annotation or add 
a new annotation highly related to an existing one
* increase the major component of the version if you're adding a completely new annotation.

During the new version release:
* Update version number in the examples in README.md
* Add a new section in CHANGELOG.md describing the changes in the new version
* Add a tag with the version number to the corresponding commit
* Push changes
* Use 'Draft a new release' button on the Releases GitHub page; select the added tag; copy it to the release title
 field; copy the added changelog section into the release details field.

### Multiplatform artifact
We publish two artifacts: annotations (annotations written in Java) and multiplatform-annotations (same annotations
written in Kotlin Multiplatform). Please check that changes are done both in java-annotations and
multiplatform-annotations folders. Jvm artifact of multiplatform-annotations should be a drop-in replacement of
java-annotations, every annotation added to java-annotations should also be added to multiplatform-annotations.

### Backward compatibility
All the changes should be backward compatible i.e. you can only add new annotations and new elements into existing annotation. 
If it's absolutely necessary to remove an annotation or its element we must firstly release a new major version where 
the corresponding element is marked as deprecated and then remove it in one of the next major versions.

### Retention policy
No annotation in this package should have `RUNTIME` retention policy. Only `SOURCE` and `CLASS` policy is accepted. 
The `CLASS` policy is preferred. The `SOURCE` policy could be used in rare cases when compilation could not preserve all 
the necessary information (e.g. `@MagicConstant` annotation).