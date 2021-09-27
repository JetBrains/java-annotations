package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * Indicates that the annotated execution context (e.g. coroutine dispatcher, scheduler, etc.) allows blocking calls inside.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface BlockingContext {
}
