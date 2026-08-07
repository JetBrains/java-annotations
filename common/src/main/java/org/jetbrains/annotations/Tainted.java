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
 * Specifies that an element of the program is an unsafe (untrusted) string. Tainted strings are often strings that
 * contain user input and can contain arbitrary data. Tainted strings need to be sanitized before using in, for example
 * database queries and file system operations. When not properly sanitizing tainted strings the program might be
 * vulnerable to code injection and other vulnerabilities. The opposite of a tainted string is an untainted string. The
 * annotation is intended to be used by taint checking tools. These tools can check whether tainted strings are passed
 * into untainted strings without being sanitized.
 *
 * <p>
 * This annotation also could be used as a meta-annotation, to define derived annotations for convenience.
 * E.g. the following annotation could be defined to annotate the strings that represent a chat message which is
 * tainted.
 * <pre>
 * &#64;Tainted
 * &#64;interface ChatMessage { }
 * </pre>
 * <p>
 *
 * Note that using the derived annotation as meta-annotation is not supported.
 * Meta-annotation works only one level deep.
 *
 * @since 24.0.0
 * @see Untainted
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE, ElementType.ANNOTATION_TYPE})
public @interface Tainted { }