package org.jetbrains.annotations

/**
 * A meta-annotation applicable to Java class or package, which means that the non-primitive types mentioned
 * in the following contexts are recursively not-null by default:
 *
 *  * Types of fields
 *  * Types of method parameters
 *  * Types of method return values
 *
 * Recursively not-null means that along with types themselves, the components of array types, the type arguments
 * of generic types and the upper bounds of wildcard types are also not-null.
 *
 *
 * The annotation has no effect on overridden method parameters and return values. In this case, the annotations
 * declared on the original method in the superclass or interface take place.
 *
 *
 * The annotation has no effect on newly declared type parameters and their bounds. Only instantiations of
 * type parameters are constrained.
 *
 *
 * The annotation has no effect on local variables.
 *
 * @since 25.0.0
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class NotNullByDefault 