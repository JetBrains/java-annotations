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
 * A member or type annotated with VisibleForTesting claims that its visibility is higher than necessary,
 * only for testing purposes. In particular:
 *
 *  * If public or protected member/type is annotated with VisibleForTesting,
 * it's assumed that package-private access is enough for production code.
 *  * If package-private member/type is annotated with VisibleForTesting,
 * it's assumed that private access is enough for production code.
 *  * It's illegal to annotate private member/type as VisibleForTesting.
 *
 *
 *
 * This annotation means that the annotated element exposes internal data and breaks encapsulation
 * of the containing class; the annotation won't prevent its use from production code, developers
 * even won't see warnings if their IDE doesn't support the annotation. It's better to provide
 * proper API which can be used in production as well as in tests.
 *
 * @since 20.0.0
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.CLASS
)
annotation class VisibleForTesting 