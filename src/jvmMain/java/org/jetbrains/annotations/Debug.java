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

import org.intellij.lang.annotations.Language;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since 18.0.0
 */
public final class Debug {

  /**
   * Prohibited default constructor.
   */
  private Debug() {
    throw new AssertionError("Debug should not be instantiated");
  }

  /**
   * Allows changing the presentation of an object in debuggers
   */
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.CLASS)
  public @interface Renderer {
    /**
     * Expression to be evaluated and used as the textual representation of the object.<br>
     * {@code this} refers to the class instance being presented
     */
    @Language(value = "JAVA", prefix = "class Renderer{String $text(){return ", suffix = ";}}")
    @NonNls
    String text() default "";

    /**
     * Expression to be evaluated to obtain an array of object's children.<br>
     * Usually the result is an array of elements in a collection, or an array of entries in a map.<br>
     * {@code this} refers to the class instance being presented
     */
    @Language(value = "JAVA", prefix = "class Renderer{Object[] $childrenArray(){return ", suffix = ";}}")
    @NonNls
    String childrenArray() default "";

    /**
     * Expression to be evaluated to check if the object has any children at all.<br>
     * This should work faster than {@link #childrenArray()} and return boolean.<br>
     * {@code this} refers to the class instance being presented
     */
    @Language(value = "JAVA", prefix = "class Renderer{boolean $hasChildren(){return ", suffix = ";}}")
    @NonNls
    String hasChildren() default "";
  }
}
