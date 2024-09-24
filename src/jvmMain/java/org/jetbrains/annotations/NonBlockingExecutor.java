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
 * Indicates that the annotated executor (CoroutineContext, Scheduler)
 * does not allow blocking methods execution.
 *
 * <p>
 * If a given executor allows blocking calls, {@link BlockingExecutor} should be used.
 *
 * <p>
 * Example 1 (Kotlin coroutines):
 * <pre><code>
 *  class NonBlockingExampleService {
 *      val dispatcher: @NonBlockingExecutor CoroutineContext
 *          get() { ... }
 *
 *      suspend fun foo() {
 *          val result = withContext(dispatcher) {
 *              blockingBuzz() // IDE warning: `Possibly blocking call in non-blocking context`
 *          }
 *      }
 *
 *      &#064;Blocking fun blockingBuzz() { ... }
 *  }
 * </code></pre>
 *
 * <p>
 * Example 2 (Java with Reactor framework):
 * <pre><code>
 *  class NonBlockingExampleService {
 *      private static final @NonBlockingExecutor Scheduler operationsScheduler =
 *              Schedulers.newParallel("parallel");
 *
 *      public Flux&lt;String&gt; foo(Flux&lt;String&gt; urls) {
 *          return urls.publishOn(operationsScheduler)
 *                  .filter(url -&gt; blockingBuzz(url) != null);  // IDE warning: `Possibly blocking call in non-blocking context`
 *      }
 *
 *      &#064;Blocking
 *      private String blockingBuzz(String url) { ... }
 *  }
 * </code></pre>
 *  @see Blocking
 *  @see NonBlocking
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.TYPE_USE})
public @interface NonBlockingExecutor {
}
