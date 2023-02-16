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
package org.intellij.lang.annotations;

import org.jetbrains.annotations.NonNls;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that an element of the program represents a string that is a regular expression text supported
 * by {@link java.util.regex.Pattern}.
 * Code editors may use this annotation to enable syntax highlighting, code completion and other features
 * inside the literals that assigned to the annotated variables, passed as arguments to the annotated parameters,
 * or returned from the annotated methods.
 *
 * @see Language
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({METHOD, FIELD, PARAMETER, LOCAL_VARIABLE, ANNOTATION_TYPE})
@Language("RegExp")
public @interface RegExp {
    /**
     * A constant prefix that is assumed to be implicitly added before the regular expression.
     */
    @NonNls String prefix() default "";
    /**
     * A constant suffix that is assumed to be implicitly added after the regular expression.
     */
    @NonNls String suffix() default "";
}
