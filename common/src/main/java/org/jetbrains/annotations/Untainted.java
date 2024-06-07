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
 * Specifies that an element of the program is a safe (trusted) string. Untainted strings contain data known to be safe
 * like sanitized user input or a strings obtained from trusted sources. Untainted strings can be considered safe to
 * pass into for example database queries and file operations. The opposite of an untainted string is a tainted string.
 * The annotation is intended to be used by taint checking tools. These tools can check whether tainted strings are
 * passed into untainted strings without being sanitized. Checking tainted strings can help detecting security
 * vulnerabilities like code injection.
 *
 * <p>
 * This annotation also could be used as a meta-annotation, to define derived annotations for convenience.
 * E.g. the following annotation could be defined to annotate the strings that represent an error message which is
 * untainted.
 * <pre>
 * &#64;UnTainted
 * &#64;interface ErrorMessage { }
 * </pre>
 * <p>
 *
 * Note that using the derived annotation as meta-annotation is not supported.
 * Meta-annotation works only one level deep.
 *
 * @since 24.0.0
 * @see Tainted
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE, ElementType.ANNOTATION_TYPE})
public @interface Untainted { }