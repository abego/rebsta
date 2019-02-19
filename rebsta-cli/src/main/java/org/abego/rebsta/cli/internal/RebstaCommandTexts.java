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

import org.abego.commons.lang.exception.MustNotInstantiateException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.abego.rebsta.cli.internal.ProjectInfo.artifactId;
import static org.abego.rebsta.cli.internal.ProjectInfo.version;
import static org.abego.rebsta.cli.internal.Texts.commandLineHeader;
import static org.abego.rebsta.cli.internal.Texts.disclaimer;
import static org.abego.rebsta.cli.internal.Texts.footerText;
import static org.abego.rebsta.cli.internal.Texts.helpText;

class RebstaCommandTexts {

    private static final int HELP_TEXT_WIDTH = 90;

    RebstaCommandTexts() {
        throw new MustNotInstantiateException();
    }

    static String fullVersionText() {
        return Texts.fullVersionText(
                artifactId(), version(), copyrightText(), disclaimer());
    }

    static String helpAndUsageText() {
        return helpText(ProjectInfo.applicationName(), usageText());
    }

    private static String copyrightText() {
        return Texts.copyright(ProjectInfo.copyrightYear());
    }

    private static String usageText() {
        Options options = RebstaCommandOptions.rebstaCommandOptions();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter
                .printHelp(
                        printWriter,
                        HELP_TEXT_WIDTH,
                        commandLineSyntax(),
                        commandLineHeader(),
                        options,
                        0,
                        0,
                        footerText(),
                        false);
        return stringWriter.toString();
    }

    private static String commandLineSyntax() {
        return Texts.commandLineSyntax(ProjectInfo.applicationName());
    }

}
