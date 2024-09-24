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
 * Indicates that the annotated method is inherently blocking and should not be executed in a non-blocking context.
 *
 *
 * When this annotation is used on a `class`, all the methods declared by the annotated class are considered
 * *blocking*.
 *
 *
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools to validate against
 * probable runtime errors and element contract violations.
 *
 * @since 22.0.0
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.CLASS
)
@kotlin.jvm.ImplicitlyActualizedByJvmDeclaration
expect annotation class Blocking()
