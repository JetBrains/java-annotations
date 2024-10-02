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
 * An element annotated with NotNull claims {@code null} value is <em>forbidden</em>
 * to return (for methods), pass to (parameters) and hold (local variables and fields).
 * <p>
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools
 * to validate against probable runtime errors and element contract violations.
 * <p>
 * If a field is annotated as {@code @NotNull} it's expected to be initialized during object construction.
 * Tools may issue a warning if it's not the case.
 * <p>
 * If a method overrides a superclass method, and the superclass method specifies the nullability on parameter
 * or return type, then the subclass method should specify the same nullability, either directly or indirectly
 * via {@link NotNullByDefault}. The only exception is the covariant return type nullability: if the superclass
 * method is declared to return nullable value, then subclass may declare to return a not-null value.
 * <p>
 * The tools may issue a warning if the nullability for a subclass method contradicts from the specified nullability
 * of a superclass method.
 *
 * @see Nullable
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface NotNull {
  /**
   * @return Custom exception message
   */
  String value() default "";

  /**
   * @return Custom exception type that should be thrown when not-nullity contract is violated.
   * The exception class should have a constructor with one String argument (message).
   * <p>
   * By default, {@link IllegalArgumentException} is thrown on null method arguments and
   * {@link IllegalStateException} &mdash; on null return value.
   */
  Class<? extends Exception> exception() default Exception.class;
}
