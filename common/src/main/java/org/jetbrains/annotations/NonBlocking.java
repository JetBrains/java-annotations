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
 * Indicates that the annotated method is inherently non-blocking and can be executed in a non-blocking context.
 * <p>
 * When this annotation is used on a {@code class}, all the methods declared by the annotated class are considered
 * <em>non-blocking</em>.
 * <p>
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools to validate against
 * probable runtime errors and contract violations.
 *
 * @since 22.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface NonBlocking {
}
