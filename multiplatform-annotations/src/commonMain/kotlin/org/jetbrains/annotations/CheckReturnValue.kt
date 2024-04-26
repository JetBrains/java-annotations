package org.jetbrains.annotations

/**
 * Specifies that the method is impure and that its return value must be used.
 *
 *
 * For pure methods (annotated with `@Contract(pure = true)`),
 * it's implied that the resulting value is important,
 * so this annotation would be redundant.
 *
 *
 * Some impure methods have side effects and still require the return value to be used.
 * For example, [java.io.InputStream.read]
 * returns the number of bytes actually stored in the byte array.
 * Without checking the return value, it's impossible to say how many bytes were actually read.
 *
 *
 * This annotation should not be used if the return value of the method
 * provides only *additional* information.
 * For example, the main purpose of [java.util.Collection.add]
 * is to modify the collection, and the return value is only interesting
 * when adding an element to a set, to see if the set already contained that element before.
 *
 *
 * When used on a type, the annotation applies to all methods that do not return `void`.
 *
 *
 * When used on a package, the annotation applies to all types of that package.
 *
 * @see Contract.pure
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE
)
annotation class CheckReturnValue
