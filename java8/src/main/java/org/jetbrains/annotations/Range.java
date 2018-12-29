package org.jetbrains.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines the allowed range of a number parameter.
 * The method may throw an {@link IllegalArgumentException} if the parameter is not within its bounds.
 * If the annotation is used on a {@code VarArgs} parameter, it describes the minimum and maximum amount of arguments.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface Range {
    /**
     * Defines the minimum value of the number. Default is {@code 0}.
     *
     * @return The minimum value of the number.
     */
    long min() default 0;

    /**
     * Defines the maximum value of the number.
     *
     * @return The maximum value oif the number.
     */
    long max();
}
