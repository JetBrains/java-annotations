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

import java.lang.annotation.*;

/**
 * Specifies the replacement value for non-constant variables and method return values.
 * This may help static analyzers to properly parse the concatenation of several values
 * which is used in @{@link Language} or {@link Pattern} context.
 * <p>
 * Example:
 * <pre>
 * &#64;Subst("Tahoma")
 * final String font = new JLabel().getFont().getName();
 *
 * &#64;Language("HTML")
 * String message = "&lt;html&gt;&lt;span style='font: " + font + "; font-size:smaller'&gt;"
 *   + ... + "&lt;/span&gt;&lt;/html&gt;";
 * </pre>
 * <p>
 * Here the parser assumes that when {@code font} appears in the concatenation its value is {@code "Tahoma"},
 * so it can continue parsing the concatenation.
 * </p>
 *
 * @see Language
 * @see Pattern
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
public @interface Subst {
    String value();
}
