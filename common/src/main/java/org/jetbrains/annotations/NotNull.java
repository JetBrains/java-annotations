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
 * An element annotated with {@link NotNull} claims {@code null} value is <em>forbidden</em>
 * to return (for methods), pass to (parameters) and hold (local variables and fields). When a
 * package is annotated with {@link NotNull} it claims that all fields and the return value for
 * methods in every class of this package are non-nullable by default.
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools
 * to validate against probable runtime errors and element contract violations.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE, ElementType.PACKAGE})
public @interface NotNull {
  /**
   * @return Custom exception message
   */
  String value() default "";

  /**
   * @return Custom exception type that should be thrown when not-nullity contract is violated.
   * The exception class should have a constructor with one String argument (message).
   *
   * By default, {@link IllegalArgumentException} is thrown on null method arguments and
   * {@link IllegalStateException} &mdash; on null return value.
   */
  Class<? extends Exception> exception() default Exception.class;
}
