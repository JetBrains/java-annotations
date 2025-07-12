package org.jetbrains.annotations;

import java.io.Closeable;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Properties;

/**
 * Indicates that responsibility for closing a {@link Closeable} resource is <em>not</em> transferred.
 *
 * <p>
 * When marked on a method, the return value of the method is still owned and managed by the callee and therefore the
 * caller must <em>not</em> close it. For example, a class may encapsulate an {@code OutputStream}, and expose it via a
 * getter. The getter does not transfer responsibility for closing the stream.
 *
 * <p>
 * When marked on a parameter, the callee does not take responsibility for closing the {@code Closeable}, and
 * responsibility for closing it stays with the caller. For example, {@link Properties#load(InputStream)} doesn't close
 * its parameter, and relying on that method to close the {@code InputStream} would be a mistake.
 *
 * <p>
 * When marked on a class or interface implementing {@code Closeable}, indicates that this class and its subclasses do
 * <em>not</em> contain resources which must be closed, and therefore not closing them won't lead to any resource leak.
 * For example, {@link StringWriter} does not hold any external resources despite implementing {@code Closeable}, and
 * closing it has no effect. Note that this does <em>not</em> mean that the class <em>cannot</em> be closed, only that
 * it does not <em>have</em> to be closed.
 *
 * <p>
 * This annotation also could be used as a meta-annotation, to define other annotations for convenience.
 *
 * <p>
 * This annotation may be used by tools to check when a closeable resource hasn't been closed or closed by the wrong
 * party. Note that this annotation has no effect when used for types that don't implement {@link Closeable}.
 *
 * @see Owning
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@ApiStatus.Experimental
public @interface NotOwning {
}
