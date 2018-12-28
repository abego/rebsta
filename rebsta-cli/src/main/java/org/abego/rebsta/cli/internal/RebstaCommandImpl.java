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

import org.abego.rebsta.cli.RebstaCommand;
import org.abego.rebsta.core.Rebsta;
import org.abego.rebsta.core.RebstaException;

import java.io.File;
import java.io.PrintStream;

import static org.abego.rebsta.cli.internal.RebstaCommandArguments.parseArguments;
import static org.abego.rebsta.cli.internal.RebstaCommandTexts.fullVersionText;
import static org.abego.rebsta.cli.internal.RebstaCommandTexts.helpAndUsageText;
import static org.abego.rebsta.cli.internal.RebstaParametersAdapter.rebstaParameters;

public class RebstaCommandImpl implements RebstaCommand {

    private final RebstaCommandArguments commandArguments;

    private RebstaCommandImpl(String[] arguments) {
        this.commandArguments = parseArguments(arguments);
    }

    public static RebstaCommandImpl rebstaCommandImpl(String[] arguments) {
        return new RebstaCommandImpl(arguments);
    }

    @Override
    public File textAccessClassJavaFile() {
        return rebsta().textAccessClassJavaFile();
    }

    @Override
    public void run(PrintStream out) {
        try {
            runOrFail(out);
        } catch (Exception e) {
            throw new RebstaException(
                    Texts.errorWhenRunningRebstaCommand(e.getLocalizedMessage()), e);
        }
    }

    private void runOrFail(PrintStream out) {

        if (commandArguments.showHelp()) {
            showHelp(out);

        } else if (commandArguments.showVersion()) {
            out.println(fullVersionText());

        } else {
            rebsta().generateTextAccessClass();
        }
    }

    private void showHelp(PrintStream out) {
        out.println(helpAndUsageText());
    }

    private Rebsta rebsta() {
        return Rebsta.rebsta(rebstaParameters(commandArguments));
    }
}
