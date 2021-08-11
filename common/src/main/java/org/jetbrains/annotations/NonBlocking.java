package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * Indicates that the annotated method is inherently non-blocking and can be executed in a non-blocking context.
 * <p>
 * When this annotation is used on a {@code class}, all the methods declared by the annotated class are considered
 * <em>non-blocking</em>.
 * <p>
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools to validate against
 * probable runtime errors and contract violations.
 *
 * @since 22.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface NonBlocking {
}
