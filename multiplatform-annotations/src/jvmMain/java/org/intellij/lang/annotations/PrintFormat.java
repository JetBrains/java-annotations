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

import java.lang.annotation.Documented;

/**
 * Specifies that the method parameter is a printf-style print format pattern,
 * as described in {@link java.util.Formatter}.
 * <p>
 * Code editors that support {@link Pattern} annotation will check
 * the syntax of this value automatically. It could also be especially recognized to
 * check whether the subsequent var-arg arguments match the expected arguments
 * mentioned in the pattern. E. g., consider that the following method is annotated:
 * <pre><code>
 * void myprintf(&#64;PrintFormat String format, Object... args) {...}
 * </code></pre>
 * <p>
 * In this case, code editors might recognize that the following call is erroneous,
 * and issue a warning:
 * <pre><code>
 * myprintf("%d\n", "hello"); // warning: a number expected instead of "hello"
 * </code></pre>
 *
 * @see Pattern
 */
@Documented
@Pattern(PrintFormatPattern.PRINT_FORMAT)
public @interface PrintFormat {
}

class PrintFormatPattern {

    // %[argument_index$][flags][width][.precision]conversion

    // Expression is taken from java.util.Formatter.fsPattern

    @Language("RegExp")
    private static final String ARG_INDEX = "(?:\\d+\\$)?";
    @Language("RegExp")
    private static final String FLAGS = "(?:[-#+ 0,(<]*)?";
    @Language("RegExp")
    private static final String WIDTH = "(?:\\d+)?";
    @Language("RegExp")
    private static final String PRECISION = "(?:\\.\\d+)?";
    @Language("RegExp")
    private static final String CONVERSION = "(?:[tT])?(?:[a-zA-Z%])";
    @Language("RegExp")
    private static final String TEXT = "[^%]|%%";

    @Language("RegExp")
    static final String PRINT_FORMAT = "(?:" + TEXT + "|" +
            "(?:%" +
            ARG_INDEX +
            FLAGS +
            WIDTH +
            PRECISION +
            CONVERSION + ")" +
            ")*";
}