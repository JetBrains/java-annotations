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
package org.jetbrains.annotations

/**
 * Helper annotations for asynchronous computation.
 * Used for example in IntelliJ IDEA's debugger for async stacktraces feature.
 *
 * @author egor
 */
actual class Async private actual constructor() {
    /**
     * Prohibited default constructor.
     */
    init {
        throw AssertionError("Async should not be instantiated")
    }

    /**
     * Indicates that the marked method schedules async computation.
     * Scheduled object is either `this`, or the annotated parameter value.
     */
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.VALUE_PARAMETER
    )
    actual annotation class Schedule

    /**
     * Indicates that the marked method executes async computation.
     * Executed object is either `this`, or the annotated parameter value.
     * This object needs to match with the one annotated with [Schedule]
     */
    @Retention(AnnotationRetention.BINARY)
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.VALUE_PARAMETER
    )
    actual annotation class Execute
}
