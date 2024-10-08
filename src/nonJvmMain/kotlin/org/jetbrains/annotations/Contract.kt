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
 * Specifies some aspects of the method behavior depending on the arguments. Can be used by tools for advanced data flow analysis.
 * Note that this annotation just describes how the code works and doesn't add any functionality by means of code generation.
 *
 *
 *
 * Method contract has the following syntax:<br></br>
 * <pre>`contract ::= (clause ';')* clause
 * clause ::= args '->' effect
 * args ::= ((arg ',')* arg )?
 * arg ::= value-constraint
 * value-constraint ::= '_' | 'null' | '!null' | 'false' | 'true'
 * effect ::= value-constraint | 'fail' | 'this' | 'new' | 'param<N>'`</pre>
 *
 *
 *
 * The constraints denote the following:<br></br>
 *
 *  *  _ - any value
 *  *  null - null value
 *  *  !null - a value statically proved to be not-null
 *  *  true - true boolean value
 *  *  false - false boolean value
 *
 *
 * The additional return values denote the following:<br></br>
 *
 *  *  fail - the method throws an exception, if the arguments satisfy argument constraints
 *  *  new - (supported in IntelliJ IDEA since version 2018.2) the method returns a non-null new object which is distinct from any other object existing in the heap prior to method execution. If method is also pure, then we can be sure that the new object is not stored to any field/array and will be lost if method return value is not used.
 *  *  this - (supported in IntelliJ IDEA since version 2018.2) the method returns its qualifier value (not applicable for static methods)
 *  *  param1, param2, ... - (supported in IntelliJ IDEA since version 2018.2) the method returns its first (second, ...) parameter value
 *
 * Examples:
 *
 *
 * `@Contract("_, null -> null")` - the method returns null if its second argument is null<br></br>
 * `@Contract("_, null -> null; _, !null -> !null")` - the method returns null if its second argument is null and not-null otherwise<br></br>
 * `@Contract("true -> fail")` - a typical `assertFalse` method which throws an exception if `true` is passed to it<br></br>
 * `@Contract("_ -> this")` - the method always returns its qualifier (e.g. [StringBuilder.append]).<br></br>
 * `@Contract("null -> fail; _ -> param1")` - the method throws an exception if the first argument is null,
 * otherwise it returns the first argument (e.g. `Objects.requireNonNull`).<br></br>
 * `@Contract("!null, _ -> param1; null, !null -> param2; null, null -> fail")` - the method returns the first non-null argument,
 * or throws an exception if both arguments are null (e.g. `Objects.requireNonNullElse` in Java 9).<br></br>
 *
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR
)
actual annotation class Contract(
    /**
     * Contains the contract clauses describing causal relations between call arguments and the returned value
     */
    @get:NonNls actual val value: String = "",
    /**
     * Specifies that the annotated method has no visible side effects.
     * If its return value is not used, removing its invocation won't
     * affect program state and change the semantics, unless method call throws an exception.
     * Exception throwing is not considered to be a side effect.
     *
     *
     * Method should not be marked as pure if it does not produce a side-effect by itself,
     * but it could be used to establish a happens-before relation between an event in
     * another thread, so changes performed in another thread might become visible in current thread
     * after this method invocation. Examples of such methods are [Object.wait], [Thread.join]
     * or [AtomicBoolean.get]. On the other hand, some synchronized methods like [java.util.Vector.get]
     * could be marked as pure, because the purpose of synchronization here is to keep the collection internal integrity
     * rather than to wait for an event in another thread.
     *
     *
     * "Invisible" side effects (such as logging) that don't affect the "important" program semantics are allowed.<br></br><br></br>
     *
     *
     * This annotation may be used for more precise data flow analysis, and
     * to check that the method's return value is actually used in the call place.
     */
    actual val pure: Boolean = false,
    /**
     * Contains a specifier which describes which method parameters can be mutated during the method call.
     * <table>
     * <caption>Possible values:</caption>
     * <tr><td>"this"</td><td>Method mutates the receiver object, and doesn't mutates any objects passed as arguments (cannot be applied for static method or constructor)</td></tr>
     * <tr><td>"param"</td><td>Method mutates the sole argument and doesn't mutate the receiver object (if applicable)</td></tr>
     * <tr><td>"param1", "param2", ...</td><td>Method mutates the N-th argument</td></tr>
     * <tr><td>"this,param1"</td><td>Method mutates the receiver and first argument and doesn't mutate any other arguments</td></tr>
    </table> *
     *
     * @return a mutation specifier string
     */
    @get:NonNls actual val mutates: String = ""
)
