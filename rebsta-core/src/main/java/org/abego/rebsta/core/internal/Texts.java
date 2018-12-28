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

package org.abego.rebsta.core.internal;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

class Texts {
    private static final String BUNDLE_NAME = "org.abego.rebsta.core.internal.Texts";

    private static final ResourceBundle BUNDLE = getBundle(BUNDLE_NAME);

    static String errorWhenLoadingResourceBundle(Object p0) {
        return textFor("errorWhenLoadingResourceBundle", p0); //NON-NLS
    }

    static String errorWhenWritingJavaFile(Object p0) {
        return textFor("errorWhenWritingJavaFile", p0); //NON-NLS
    }

    static String missingJavaSourcesRootParameter() {
        return textFor("missingJavaSourcesRootParameter"); //NON-NLS
    }

    static String missingResourceBundleNameParameter() {
        return textFor("missingResourceBundleNameParameter"); //NON-NLS
    }

    static String missingResourcesRootParameter() {
        return textFor("missingResourcesRootParameter"); //NON-NLS
    }


    private static String textFor(String key, Object... arguments) {
        return BUNDLE.containsKey(key)
                ? new MessageFormat(BUNDLE.getString(key)).format(arguments)
                : MessageFormat.format("??{0}??", key); //NON-NLS
    }
}
