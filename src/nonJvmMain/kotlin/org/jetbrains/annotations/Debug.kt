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

import org.intellij.lang.annotations.Language

/**
 * @since 18.0.0
 */
actual class Debug private actual constructor() {
    /**
     * Prohibited default constructor.
     */
    init {
        throw AssertionError("Debug should not be instantiated")
    }

    /**
     * Allows to change the presentation of an object in debuggers
     */
    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.BINARY)
    actual annotation class Renderer(
        /**
         * Expression to be evaluated and used as the textual representation of the object.<br></br>
         * `this` refers to the class instance being presented
         */
        @get:Language(
            value = "JAVA",
            prefix = "class Renderer{String \$text(){return ",
            suffix = ";}}"
        ) @get:NonNls actual val text: String = "",
        /**
         * Expression to be evaluated to obtain an array of object's children.<br></br>
         * Usually the result is an array of elements in a collection, or an array of entries in a map.<br></br>
         * `this` refers to the class instance being presented
         */
        @get:Language(
            value = "JAVA",
            prefix = "class Renderer{Object[] \$childrenArray(){return ",
            suffix = ";}}"
        ) @get:NonNls actual val childrenArray: String = "",
        /**
         * Expression to be evaluated to check if the object has any children at all.<br></br>
         * This should work faster than [.childrenArray] and return boolean.<br></br>
         * `this` refers to the class instance being presented
         */
        @get:Language(
            value = "JAVA",
            prefix = "class Renderer{boolean \$hasChildren(){return ",
            suffix = ";}}"
        ) @get:NonNls actual val hasChildren: String = ""
    )
}
