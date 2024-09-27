package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * A meta-annotation applicable to Java class or package, which means that the non-primitive types mentioned
 * in the following contexts are recursively not-null by default:
 * <ul>
 *   <li>Types of fields</li>
 *   <li>Types of method parameters</li>
 *   <li>Types of method return values</li>
 * </ul>
 * Recursively not-null means that along with types themselves, the components of array types, the type arguments
 * of generic types and the upper bounds of wildcard types are also not-null.
 * <p>
 * The annotation has no effect on overridden method parameters and return values. In this case, the annotations
 * declared on the original method in the superclass or interface take place.
 * <p>
 * The annotation has no effect on newly declared type parameters and their bounds. Only instantiations of
 * type parameters are constrained.
 * <p>
 * The annotation has no effect on local variables.
 *
 * @see NotNull
 * @since 25.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.PACKAGE})
@ApiStatus.Experimental
public @interface NotNullByDefault {
}
