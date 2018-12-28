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

package org.abego.rebsta.cli.internal;

import org.apache.commons.cli.Options;

import static org.abego.commons.lang.exception.MustNotInstantiateException.throwMustNotInstantiate;
import static org.abego.rebsta.cli.RebstaCommand.CLASS_NAME_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.HELP_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.JAVAFILE_CHARSET_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.PACKAGE_PRIVATE_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.RESOURCEBUNDLE_CHARSET_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.VERSION_OPTION;

class RebstaCommandOptions {

    RebstaCommandOptions() {
        throwMustNotInstantiate();
    }

    static Options rebstaCommandOptions() {
        return new Options()
                .addOption("c", CLASS_NAME_OPTION, true, // NON-NLS
                        Texts.classNameOptionDescription())
                .addOption("h", HELP_OPTION, false,  // NON-NLS
                        Texts.helpOptionDescription())
                .addOption("jc", JAVAFILE_CHARSET_OPTION, true, // NON-NLS
                        Texts.javaFileCharsetOptionDescription())
                .addOption("rc", RESOURCEBUNDLE_CHARSET_OPTION, true, // NON-NLS
                        Texts.resourceBundleCharsetOptionDescription())
                .addOption("pp", PACKAGE_PRIVATE_OPTION, false, // NON-NLS
                        Texts.packagePrivateOptionDescription())
                .addOption(null, VERSION_OPTION, false,
                        Texts.versionOptionDescription());
    }

}
