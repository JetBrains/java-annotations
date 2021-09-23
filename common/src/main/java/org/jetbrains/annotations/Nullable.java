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
 * An element annotated with {@link Nullable} claims {@code null} value is perfectly <em>valid</em>
 * to return (for methods), pass to (parameters) or hold in (local variables and fields). When a package
 * is annotated with {@link Nullable} it claims that all fields and the return value for methods in every
 * class of this package are nullable by default.
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools
 * to validate against probable runtime errors or element contract violations.
 * <p>
 * By convention, this annotation applied only when the value should <em>always</em> be checked against {@code null}
 * because the developer could do nothing to prevent null from happening.
 * Otherwise, too eager {@link Nullable} usage could lead to too many false positives from static analysis tools.
 * <p>
 * For example, {@link java.util.Map#get(Object key)} should <em>not</em> be annotated {@link Nullable} because
 * someone may have put not-null value in the map by this key and is expecting to find this value there ever since.
 * <p>
 * On the other hand, the {@link java.lang.ref.Reference#get()} should be annotated {@link Nullable} because
 * it returns {@code null} if object got collected which can happen at any time completely unexpectedly.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE, ElementType.PACKAGE})
public @interface Nullable {
  /**
   * @return textual reason when the annotated value could be null, for documentation purposes.
   */
  @NonNls String value() default "";
}
