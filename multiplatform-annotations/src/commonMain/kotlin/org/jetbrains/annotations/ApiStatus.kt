/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.annotations

/**
 * Set of annotations which can be used to specify status of API Element.
 *
 * @since 18.0.0
 */
class ApiStatus private constructor() {
    /**
     * Prohibited default constructor.
     */
    init {
        throw AssertionError("ApiStatus should not be instantiated")
    }

    /**
     *
     * Indicates that a public API of the annotated element (class, method or field) is not in stable state yet. It may be renamed, changed or
     * even removed in a future version. This annotation refers to API status only, it doesn't mean that the implementation has
     * an 'experimental' quality.
     *
     *
     * It's safe to use an element marked with this annotation if the usage is located in the same sources codebase as the declaration. However,
     * if the declaration belongs to an external library such usages may lead to problems when the library will be updated to another version.
     *
     *
     * If a package is marked with this annotation, all its containing classes are considered experimental.
     * Subpackages of this package are not affected and should be marked independently.
     *
     *
     * If a type is marked with this annotation, all its members are considered experimental, but its inheritors are not.
     *
     *
     * If a method is marked with this annotation, overriding methods are not considered experimental.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FILE
    )
    annotation class Experimental

    /**
     * Indicates that the annotated element (class, method, field, etc) must not be considered as a public API. It's made visible to allow
     * usages in other packages of the declaring library, but it must not be used outside of that library. Such elements
     * may be renamed, changed or removed in future versions.
     *
     *
     * If a package is marked with this annotation, all its containing classes are considered internal.
     * Subpackages of this package are not affected and should be marked independently.
     *
     *
     * If a type is marked with this annotation, all its members are considered internal, but its inheritors are not.
     *
     *
     * If a method is marked with this annotation, overriding methods are not considered internal.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FILE
    )
    annotation class Internal

    /**
     *
     * Indicates that a public API of the annotated element (class, method or field) is subject to deprecation in a future version.
     * It's a weaker variant of [Deprecated] annotation.
     * The annotated API is not supposed to be used in the new code because a better API exists,
     * but it's permitted to postpone the migration of the existing code, therefore the usage is not considered a warning.
     *
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FILE
    )
    annotation class Obsolete(
        /**
         * Specifies in which version the API became obsolete.
         */
        val since: String = ""
    )

    /**
     *
     * Indicates that a public API of the annotated element (class, method or field) is subject to removal in a future version.
     * It's a stronger variant of [Deprecated] annotation.
     *
     *
     * Since many tools aren't aware of this annotation it should be used as an addition to `@Deprecated` annotation
     * or `@deprecated` Javadoc tag only.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FILE
    )
    annotation class ScheduledForRemoval(
        /**
         * Specifies in which version the API will be removed.
         */
        val inVersion: String = ""
    )

    /**
     *
     * Indicates that the annotated element firstly appeared in the specified version of the library, so the code using that element
     * won't be compatible with older versions of the library. This information may be used by IDEs and static analysis tools.
     * This annotation can be used instead of '@since' Javadoc tag if it's needed to keep that information in *.class files or if you need
     * to generate them automatically.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FILE
    )
    annotation class AvailableSince(
        /**
         * Specifies a version where the annotation API firstly appeared.
         */
        val value: String
    )

    /**
     *
     * Indicates that the annotated API class, interface or method **must not be extended, implemented or overridden**.
     *
     *
     * API class, interface or method may not be marked `final` because it is extended by classes of the declaring library
     * but it is not supposed to be extended outside the library. Instances of classes and interfaces marked with this annotation
     * may be cast to an internal implementing class in the library code, leading to `ClassCastException`
     * if a different implementation is provided by a client.
     *
     *
     * New abstract methods may be added to such classes and interfaces in new versions of the library breaking compatibility
     * with a client's implementations.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    annotation class NonExtendable

    /**
     *
     * Indicates that the annotated method is part of SPI (Service Provider Interface), which is intended to be
     * **only implemented or overridden** but not called by clients of the declaring library.
     * If a class or interface is marked with this annotation it means that all its methods can be only overridden.
     *
     *
     * Although there is a standard mechanism of `protected` methods, it is not applicable to interface's methods.
     * Also, API method may be made `public` to allow calls only from different parts of the declaring library but not outside it.
     *
     *
     * Signatures of such methods may be changed in new versions of the library in the following steps. Firstly, a method with new signature
     * is added to the library delegating to the old method by default. Secondly, all clients implement the new method and remove
     * implementations of the old one. This leads to compatibility breakage with code that calls the methods directly.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.CLASS,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    annotation class OverrideOnly
}