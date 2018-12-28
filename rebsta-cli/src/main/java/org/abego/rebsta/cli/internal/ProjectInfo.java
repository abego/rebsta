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

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

interface ProjectInfo {
    String BUNDLE_NAME = "org/abego/rebsta/cli/internal/ProjectInfo"; // NON-NLS
    ResourceBundle BUNDLE = getBundle(BUNDLE_NAME);

    static String applicationName() {
        return BUNDLE.getString("applicationName"); // NON-NLS
    }

    static String artifactId() {
        return BUNDLE.getString("artifactId"); // NON-NLS
    }

    static String version() {
        //noinspection DuplicateStringLiteralInspection
        return BUNDLE.getString("version"); // NON-NLS
    }


    static String copyrightYear() {
        return BUNDLE.getString("copyrightYear"); // NON-NLS
    }
}
