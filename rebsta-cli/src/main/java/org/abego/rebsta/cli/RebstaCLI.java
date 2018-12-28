/*
 * MIT License
 *
 * Copyright (c) 2018 Udo Borkowski, (ub@abego.org)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.abego.rebsta.cli;

import org.abego.rebsta.core.RebstaException;

import java.io.PrintStream;

import static org.abego.commons.lang.ThrowableUtil.messageOrClassName;
import static org.abego.commons.lang.exception.MustNotInstantiateException.throwMustNotInstantiate;
import static org.abego.rebsta.cli.RebstaCommand.rebstaCommand;

public class RebstaCLI {
    /**
     * Exit status code to indicate an abnormal termination.
     *
     * <p>See {@link System#exit(int)}.</p>
     */
    public static final int ERROR_EXIT_STATUS = 1;

    RebstaCLI() {
        throwMustNotInstantiate();
    }

    /**
     * Run a {@link RebstaCommand} with the given {@code arguments},
     * possibly writing output to {@link System#out}.
     *
     * <p>In case of an error print error messages to {@link System#err}
     * and exit with error code {@link #ERROR_EXIT_STATUS}, otherwise exit
     * normally (see {@link System#exit(int)}).</p>
     */
    public static void main(String[] arguments) {

        runCommand(arguments, System.err, System.out,
                () -> System.exit(ERROR_EXIT_STATUS));
    }

    /**
     * Run a {@link RebstaCommand} with the given {@code arguments},
     * possibly writing output to {@code out}.
     *
     * <p>In case of an error write an error message to {@code errorOutput}
     * and call {@code onError}.</p>
     */
    public static void runCommand(String[] arguments,
                                  PrintStream out, PrintStream errorOutput,
                                  Runnable onError) {

        try {
            rebstaCommand(arguments).run(out);

        } catch (RebstaException e) {
            errorOutput.println(messageOrClassName(e));
            onError.run();
        }
    }

    /**
     * Run a {@link RebstaCommand} with the given {@code arguments},
     * possibly writing output to {@code out}.
     *
     * <p>In case of an error write an error message to {@code errorOutput}.</p>
     */
    public static void runCommand(String[] arguments,
                                  PrintStream out, PrintStream errorOutput) {
        runCommand(arguments, out, errorOutput, () -> {});
    }


}
