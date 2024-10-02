/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * An element annotated with {@code Nullable} claims {@code null} value is perfectly <em>valid</em>
 * to return (for methods), pass to (parameters) or hold in (local variables and fields).
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools
 * to validate against probable runtime errors or element contract violations.
 * <p>
 * By convention, this annotation applied only when the value should <em>always</em> be checked against {@code null}
 * because the developer could do nothing to prevent null from happening.
 * Otherwise, too eager {@code Nullable} usage could lead to too many false positives from static analysis tools.
 * <p>
 * For example, {@link java.util.Map#get(Object key)} should <em>not</em> be annotated {@code Nullable} because
 * someone may have put not-null value in the map by this key and is expecting to find this value there ever since.
 * It could be annotated as {@link UnknownNullability} or left unannotated.
 * <p>
 * On the other hand, the {@link java.lang.ref.Reference#get()} should be annotated {@code Nullable} because
 * it returns {@code null} if object got collected which can happen at any time completely unexpectedly.
 * <p>
 * If a method overrides a superclass method, and the superclass method specifies the nullability on parameter
 * or return type, then the subclass method should specify the same nullability, either directly or indirectly
 * via {@link NotNullByDefault}. The only exception is the covariant return type nullability: if the superclass
 * method is declared to return nullable value, then subclass may declare to return a not-null value.
 * <p>
 * The tools may issue a warning if the nullability for a subclass method contradicts from the specified nullability
 * of a superclass method.
 *
 * @see NotNull
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface Nullable {
  /**
   * @return textual reason when the annotated value could be null, for documentation purposes.
   */
  @NonNls String value() default "";
}
