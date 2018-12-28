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

import org.abego.rebsta.core.RebstaException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import static org.abego.commons.lang.ThrowableUtil.messageOrClassName;
import static org.abego.rebsta.cli.RebstaCommand.CLASS_NAME_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.HELP_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.JAVAFILE_CHARSET_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.PACKAGE_PRIVATE_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.RESOURCEBUNDLE_CHARSET_OPTION;
import static org.abego.rebsta.cli.RebstaCommand.VERSION_OPTION;
import static org.abego.rebsta.cli.internal.RebstaCommandOptions.rebstaCommandOptions;

/**
 * The arguments passed to a `rebsta` command, both mandatory and optional ones.
 */
class RebstaCommandArguments {
    private static final int ARG_COUNT = Arg.values().length;

    private final String resourceBundleName;
    private final String textAccessClassName;
    private final String javaSourceRoot;
    private final String resourcesRoot;
    private final String resourceFileCharSetName;
    private final String javaFileCharSetName;
    private final boolean withPackagePrivate;
    private final boolean showHelp;
    private final boolean showVersion;

    private RebstaCommandArguments(String[] arguments) {

        CommandLine commandLine = parse(arguments, rebstaCommandOptions());

        // options
        resourceFileCharSetName = commandLine.getOptionValue(RESOURCEBUNDLE_CHARSET_OPTION);
        javaFileCharSetName = commandLine.getOptionValue(JAVAFILE_CHARSET_OPTION);
        withPackagePrivate = commandLine.hasOption(PACKAGE_PRIVATE_OPTION);
        showHelp = commandLine.hasOption(HELP_OPTION) || arguments.length == 0;
        showVersion = commandLine.hasOption(VERSION_OPTION);
        textAccessClassName = commandLine.getOptionValue(CLASS_NAME_OPTION);

        // arguments
        if (showHelp || showVersion) {
            // no arguments required when using showHelp or showVersion
            resourcesRoot = "";
            javaSourceRoot = "";
            resourceBundleName = "";

        } else {
            String[] args = commandLine.getArgs();

            if (args.length < ARG_COUNT) {
                throw new RebstaException(Texts.missingArguments());
            } else if (args.length > ARG_COUNT) {
                throw new RebstaException(Texts.tooManyArguments());
            }

            resourcesRoot = args[Arg.RESOURCES_ROOT.ordinal()];
            javaSourceRoot = args[Arg.JAVA_SOURCE_ROOT.ordinal()];
            resourceBundleName = args[Arg.RESOURCE_BUNDLE_NAME.ordinal()];
        }
    }

    static RebstaCommandArguments parseArguments(String[] arguments) {
        return new RebstaCommandArguments(arguments);
    }

    private static CommandLine parse(String[] arguments, Options options) {
        try {
            return new DefaultParser().parse(options, arguments);
        } catch (Exception e) {
            throw new RebstaException(Texts.errorInCommand(messageOrClassName(e)), e);
        }
    }

    public String resourceBundleName() {
        return resourceBundleName;
    }

    public String textAccessClassName() {
        return textAccessClassName;
    }

    public String javaSourceRoot() {
        return javaSourceRoot;
    }

    public String resourcesRoot() {
        return resourcesRoot;
    }

    public String resourceFileCharSetName() {
        return resourceFileCharSetName;
    }

    public String javaFileCharSetName() {
        return javaFileCharSetName;
    }

    public boolean withPackagePrivate() {
        return withPackagePrivate;
    }

    public boolean showHelp() {
        return showHelp;
    }

    public boolean showVersion() {
        return showVersion;
    }

    /**
     * The (mandatory) arguments of the command (i.e. the items following the
     * options), in the order they are expected in the command line.
     */
    private enum Arg {
        RESOURCES_ROOT,
        JAVA_SOURCE_ROOT,
        RESOURCE_BUNDLE_NAME
    }


}