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

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

class Texts {
    private static final String BUNDLE_NAME = "org.abego.rebsta.cli.internal.Texts";

    private static final ResourceBundle BUNDLE = getBundle(BUNDLE_NAME);

    static String charsetNotSupported(Object p0) {
        return textFor("charsetNotSupported", p0); //NON-NLS
    }

    static String classNameOptionDescription() {
        return textFor("classNameOptionDescription"); //NON-NLS
    }

    static String commandLineHeader() {
        return textFor("commandLineHeader"); //NON-NLS
    }

    static String commandLineSyntax(Object p0) {
        return textFor("commandLineSyntax", p0); //NON-NLS
    }

    static String copyright(Object p0) {
        return textFor("copyright", p0); //NON-NLS
    }

    static String disclaimer() {
        return textFor("disclaimer"); //NON-NLS
    }

    static String errorInCommand(Object p0) {
        return textFor("errorInCommand", p0); //NON-NLS
    }

    static String errorWhenRunningRebstaCommand(Object p0) {
        return textFor("errorWhenRunningRebstaCommand", p0); //NON-NLS
    }

    static String footerText() {
        return textFor("footerText"); //NON-NLS
    }

    static String fullVersionText(Object p0, Object p1, Object p2, Object p3) {
        return textFor("fullVersionText", p0, p1, p2, p3); //NON-NLS
    }

    static String helpOptionDescription() {
        return textFor("helpOptionDescription"); //NON-NLS
    }

    static String helpText(Object p0, Object p1) {
        return textFor("helpText", p0, p1); //NON-NLS
    }

    static String javaFileCharsetOptionDescription() {
        return textFor("javaFileCharsetOptionDescription"); //NON-NLS
    }

    static String missingArguments() {
        return textFor("missingArguments"); //NON-NLS
    }

    static String packagePrivateOptionDescription() {
        return textFor("packagePrivateOptionDescription"); //NON-NLS
    }

    static String resourceBundleCharsetOptionDescription() {
        return textFor("resourceBundleCharsetOptionDescription"); //NON-NLS
    }

    static String tooManyArguments() {
        return textFor("tooManyArguments"); //NON-NLS
    }

    static String versionOptionDescription() {
        return textFor("versionOptionDescription"); //NON-NLS
    }


    private static String textFor(String key, Object... arguments) {
        return BUNDLE.containsKey(key)
                ? new MessageFormat(BUNDLE.getString(key)).format(arguments)
                : MessageFormat.format("??{0}??", key); //NON-NLS
    }
}
