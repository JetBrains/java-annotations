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
 * Specifies that an element of the program is a user-visible string which needs to be localized.
 * This annotation is intended to be used by localization tools for
 * detecting strings which should be reported as requiring localization.
 *
 * <p>
 * This annotation also could be used as a meta-annotation, to define derived annotations for convenience.
 * E.g. the following annotation could be defined to annotate the strings that represent dialog titles:
 *
 * <pre>
 * &#64;Nls(capitalization = Capitalization.Title)
 * &#64;interface DialogTitle {}
 * </pre>
 * <p>
 * Note that using the derived annotation as meta-annotation is not supported.
 * Meta-annotation works only one level deep.
 *
 * @see NonNls
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE, ElementType.TYPE, ElementType.PACKAGE})
public @interface Nls {

  enum Capitalization {

    NotSpecified,
    /**
     * e.g. This Is a Title
     */
    Title,
    /**
     * e.g. This is a sentence
     */
    Sentence
  }

  Capitalization capitalization() default Capitalization.NotSpecified;
}
