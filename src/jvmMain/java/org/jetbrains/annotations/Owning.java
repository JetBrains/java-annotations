package org.jetbrains.annotations;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Writer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Files;

/**
 * Indicates a transfer of responsibility for closing a {@link Closeable} resource.
 *
 * <p>
 * When marked on a method, the responsibility for closing the return value of this method is transferred to the caller.
 * For example, the {@link Files#newInputStream} method constructs an {@code InputStream} and returns it, then the
 * caller is responsible for closing this {@code InputStream}.
 *
 * <p>
 * When marked on a parameter, the responsibility for closing is transferred from the caller to the callee. For example,
 * the following method's parameter may be annotated with {@code @Owning}:
 * <pre>
 * {@code
 * public static void closeQuietly(@Owning Closeable resource) {
 *     try {
 *         resource.close();
 *     } catch (IOException ignored) {
 *     }
 * }
 * }
 * </pre>
 * Another example is {@link BufferedWriter#BufferedWriter(Writer)} which wraps a {@code Writer}, as the responsibility
 * for closing the wrapped writer is transferred away from the caller (to the {@code BufferedWriter}).
 *
 * <p>
 * When marked on a class or interface implementing {@code Closeable}, indicates that this class and its subclasses
 * contain resources which must be closed, and therefore {@link Closeable#close close()} <em>must</em> be called at some
 * point. This is the default assumption for most closeable classes; this annotation may be used to override a
 * {@link NotOwning} annotation in a superclass.
 *
 * <p>
 * This annotation also could be used as a meta-annotation, to define other annotations for convenience.
 *
 * <p>
 * This annotation may be used by tools to check when a closeable resource hasn't been closed or closed by the wrong
 * party. Note that this annotation has no effect when used for types that don't implement {@link Closeable}.
 *
 * @see NotOwning
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@ApiStatus.Experimental
public @interface Owning {
}
