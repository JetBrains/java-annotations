package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * The annotation should be applied to overridable non-abstract method
 * and indicates that all the overriders must invoke this method via 
 * superclass method invocation expression. The static analysis tools 
 * may report a warning if overrider fails to invoke this method.
 * 
 * @since 20.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustBeInvokedByOverriders {}
