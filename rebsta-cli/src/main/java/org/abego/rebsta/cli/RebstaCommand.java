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

import java.io.File;
import java.io.PrintStream;

import static org.abego.rebsta.cli.internal.RebstaCommandImpl.rebstaCommandImpl;

/**
 * A command line interface to the {@link org.abego.rebsta.core.Rebsta} features
 *
 * <p>For a synopsis of the command see the project's README.</p>
 */
public interface RebstaCommand {

    String HELP_OPTION = "help";  // NON-NLS
    String JAVAFILE_CHARSET_OPTION = "javafile-charset"; // NON-NLS
    String CLASS_NAME_OPTION = "classname"; // NON-NLS
    String RESOURCEBUNDLE_CHARSET_OPTION = "resourcebundle-charset"; // NON-NLS
    String PACKAGE_PRIVATE_OPTION = "package-private"; // NON-NLS
    String VERSION_OPTION = "version"; // NON-NLS

    /**
     * Return a new {@link RebstaCommand} object for the given {@code arguments}.
     */
    static RebstaCommand rebstaCommand(String... arguments) {
        return rebstaCommandImpl(arguments);
    }

    /**
     * Return the file the command will generate to hold the Java class to access
     * the texts for the resource bundle through static accessor methods.
     */
    File textAccessClassJavaFile();

    /**
     * Run the command, possibly writing output to {@code out}.
     **/
    void run(PrintStream out);

}
