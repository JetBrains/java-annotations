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
 * If a method overrides a superclass method, and the superclass method specifies the nullability on parameter
 * or return type, then the subclass method should specify the same nullability, either directly or indirectly
 * via {@code @NotNullByDefault}. The only exception is the covariant return type nullability: if the superclass
 * method is declared to return nullable value, then subclass may declare to return a not-null value.
 * <p>
 * The tools may issue a warning if the nullability for a subclass method contradicts from the specified nullability
 * of a superclass method.
 * <p>
 * For newly declared type parameters, the annotation applies to its bounds, including implicit {@code Object}
 * bound if no explicit bound is declared. For example, {@code <T>} declared under {@code @NotNullByDefault} scope
 * means the same as {@code <T extends @NotNull Object>}. To reset to default behavior in this case, one should use
 * {@code <T extends @UnknownNullability Object>}.
 * <p>
 * The type parameter references are not affected by {@code @NotNullByDefault}. For example:
 * <pre>{@code @NotNullByDefault
 * interface Pair<K extends @Nullable Object, V> {
 *   // Not assumed to be @NotNull; may return null depending on the K instantiation
 *   K getKey();
 *   // Returns @NotNull, as implicit upper bound of V is @NotNull Object,
 *   // so it cannot be instantiated with a nullable type
 *   V getValue();
 * } }</pre>
 * <p>
 * The annotation has no effect on local variables.
 *
 * @see NotNull
 * @since 26.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.PACKAGE})
@ApiStatus.Experimental
public @interface NotNullByDefault {
}
