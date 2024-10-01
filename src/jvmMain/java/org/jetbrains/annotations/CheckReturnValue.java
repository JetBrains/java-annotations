package org.jetbrains.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Specifies that the method is impure and that its return value must be used.
 * <p>
 * For pure methods (annotated with {@code @Contract(pure = true)}),
 * it's implied that the resulting value is important,
 * so this annotation would be redundant.
 * <p>
 * Some impure methods have side effects and still require the return value to be used.
 * For example, {@link java.io.InputStream#read(byte[])}
 * returns the number of bytes actually stored in the byte array.
 * Without checking the return value, it's impossible to say how many bytes were actually read.
 * <p>
 * This annotation should not be used if the return value of the method
 * provides only <i>additional</i> information.
 * For example, the main purpose of {@link java.util.Collection#add(Object)}
 * is to modify the collection, and the return value is only interesting
 * when adding an element to a set, to see if the set already contained that element before.
 * <p>
 * When used on a type, the annotation applies to all methods that do not return {@code void}.
 * <p>
 * When used on a package, the annotation applies to all types of that package.
 *
 * @see Contract#pure()
 * @since 24.0.0
 */
@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PACKAGE})
public @interface CheckReturnValue {
}
