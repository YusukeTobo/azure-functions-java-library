/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.functions.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The timer trigger lets you run a function on a schedule by specifying a CRON expression for when the function should
 * run. For more details and examples on how to specify a CRON expression, refer to the {@link #schedule()} attribute of
 * this annotation.
 *
 * <p>An example of using the timer trigger is shown below, where the {@code keepAlive} function is set to trigger and
 * execute every five minutes:</p>
 *
 * <pre>
 * {@literal @}FunctionName("keepAlive")
 *  public void keepAlive(
 *    {@literal @}TimerTrigger(name = "keepAliveTrigger", schedule = "0 *&#47;5 * * * *") String timerInfo,
 *     ExecutionContext context
 *  ) {
 *      // timeInfo is a JSON string, you can deserialize it to an object using your favorite JSON library
 *      context.getLogger().info("Timer is triggered: " + timerInfo);
 *  }</pre>
 *
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface TimerTrigger {
    /**
     * The name of the variable that represents the timer object in function code.
     *
     * @return The name of the variable that represents the timer object in function code.
     */
    String name();

    /**
     * <p>Defines how Functions runtime should treat the parameter value. Possible values are:</p>
     * <ul>
     *     <li>"": get the value as a string, and try to deserialize to actual parameter type like POJO</li>
     *     <li>string: always get the value as a string</li>
     *     <li>binary: get the value as a binary data, and try to deserialize to actual parameter type byte[]</li>
     * </ul>
     * @return The dataType which will be used by the Functions runtime.
     */
    String dataType() default "";

    /**
     * A <a href="http://en.wikipedia.org/wiki/Cron#CRON_expression">CRON expression</a> in the format
     * {@code {second} {minute} {hour} {day} {month} {day-of-week}}.
     *
     * <p>Some examples of CRON expressions that could be used include:</p>
     *
     * <table summary="CRON expression examples">
     *     <tr>
     *         <th>Goal</th>
     *         <th>CRON Expression</th>
     *     </tr>
     *     <tr>
     *         <td>To trigger once every five minutes:</td>
     *         <td>0 *&#47;5 * * * *</td>
     *     </tr>
     *     <tr>
     *         <td>To trigger once at the top of every hour:</td>
     *         <td>0 0 * * * *</td>
     *     </tr>
     *     <tr>
     *         <td>To trigger once every two hours:</td>
     *         <td>0 0 *&#47;2 * * *</td>
     *     </tr>
     *     <tr>
     *         <td>To trigger once every hour from 9 AM to 5 PM:</td>
     *         <td>0 0 9-17 * * *</td>
     *     </tr>
     *     <tr>
     *         <td>To trigger at 9:30 AM every day:</td>
     *         <td>0 30 9 * * *</td>
     *     </tr>
     *     <tr>
     *         <td>To trigger at 9:30 AM every weekday:</td>
     *         <td>0 30 9 * * 1-5</td>
     *     </tr>
     * </table>
     *
     * @return A string representing a CRON expression that will be used to schedule a function to run.
     */
    String schedule();
}
