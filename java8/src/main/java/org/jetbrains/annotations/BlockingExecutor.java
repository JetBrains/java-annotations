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
 * Indicates that the annotated executor (e.g. coroutine dispatcher, scheduler, etc.)
 * allows blocking methods execution.
 *
 * If a given context does not allow blocking calls, {@link NonBlockingExecutor} should be used.
 *
 * Example:
 * <pre>
 * {@code
 *  class ExampleService {
 *      @BlockingContext
 *      val dispatcher: CoroutineContext
 *          get() { ... }
 *
 *      suspend fun foo() {
 *          val result = withContext(dispatcher) {
 *              blockingBuzz() // no IDE warning
 *          }
 *      }
 *
 *      @Blocking fun blockingBuzz() { ... }
 *  }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface BlockingExecutor {
}
