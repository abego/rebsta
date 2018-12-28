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

package com.example.myapp;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class Texts {
    private static final String BUNDLE_NAME = "com.example.ResourceBundleSample";

    private static final ResourceBundle BUNDLE = getBundle(BUNDLE_NAME);

    public static String key1() {
        return textFor("key1"); //NON-NLS
    }

    public static String key2(Object p0) {
        return textFor("key2", p0); //NON-NLS
    }

    public static String key3(Object p0, Object p1, Object p2, Object p3) {
        return textFor("key3", p0, p1, p2, p3); //NON-NLS
    }

    // Manually added method after class generation to allow testing
    // the behaviour of "missing/deleted" keys.
    public static String missingKey() {
        return textFor("missingKey"); //NON-NLS
    }

    private static String textFor(String key, Object... arguments) {
        return BUNDLE.containsKey(key)
                ? new MessageFormat(BUNDLE.getString(key)).format(arguments)
                : MessageFormat.format("??{0}??", key); //NON-NLS
    }
}
