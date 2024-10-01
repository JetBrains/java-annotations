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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An element annotated with {@code UnknownNullability} claims that no specific nullability
 * should be assumed by static analyzer. The unconditional dereference of the annotated value
 * should not trigger a static analysis warning by default (though static analysis tool may have
 * an option to perform stricter analysis and issue warnings for {@code @UnknownNullability} as well).
 * It's mainly useful at method return types to mark methods that may occasionally
 * return {@code null} but in many cases, user knows that in this particular code path
 * {@code null} is not possible, so producing a warning would be annoying.
 * <p>
 * The {@code UnknownNullability} is the default nullability for unannotated methods, so it's
 * rarely necessary. An explicit annotation may serve to document the method behavior and also
 * to override automatic annotation inference result that could be implemented in some static
 * analysis tools. Finally, it can override the effect of {@link NotNullByDefault}.
 *
 * @since 21.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE_USE})
public @interface UnknownNullability {
  /**
   * @return textual reason when the annotated value could be null, for documentation purposes.
   */
  @NonNls String value() default "";
}
