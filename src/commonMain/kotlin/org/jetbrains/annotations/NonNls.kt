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
 * Specifies that an element of the program is not a user-visible string which needs to be localized,
 * or does not contain such strings. This annotation is intended to be used by localization tools for
 * detecting strings which should not be reported as requiring localization.
 *
 *  * If a method parameter is annotated with `NonNls`, the strings passed
 * as values of this parameter are not reported as requiring localization.
 * Also, if the parameter of a property setter method is annotated with `NonNls`, values
 * of that property in UI Designer forms are never highlighted as hard-coded strings.
 *  * If a field is annotated with `NonNls`, all string literals found in the
 * initializer of the field are not reported as requiring localization.
 *  * If a method is called on a field, parameter or local variable annotated with `NonNls`,
 * string literals passed as parameters to the method are not reported as requiring localization.
 *  * If a field, parameter or local variable annotated with `NonNls` is passed as a
 * parameter to the `equals()` method invoked on a string literal, the literal is not
 * reported as requiring localization.
 *  * If a field, parameter or local variable annotated with `NonNls` is found at
 * the left side of an assignment expression, all string literals in the right side
 * of the expression are not reported as requiring localization.
 *  * If a method is annotated with `NonNls`, string literals returned from the method
 * are not reported as requiring localization.
 *  * If a class is annotated with `NonNls`, all string literals in
 * the class and all its subclasses are not reported as requiring localization.
 *  * If a package is annotated with `NonNls`, all string literals in
 * the package and all its subpackages are not reported as requiring localization.
 *
 *
 *
 *
 * This annotation also could be used as a meta-annotation, to define derived annotations for convenience.
 * E.g. the following annotation could be defined to annotate the strings that represent UUIDs,
 * thus should not be localized:
 *
 * <pre>
 * &#64;NonNls
 * &#64;interface UUID {}
</pre> *
 *
 *
 * Note that using the derived annotation as meta-annotation is not supported.
 * Meta-annotation works only one level deep.
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE,
    AnnotationTarget.FILE
)
@kotlin.jvm.ImplicitlyActualizedByJvmDeclaration
expect annotation class NonNls()
