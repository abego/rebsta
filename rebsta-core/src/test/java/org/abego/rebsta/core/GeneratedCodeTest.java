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

package org.abego.rebsta.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneratedCodeTest {

    @Test
    void happyPath() {
        assertEquals("foo.", com.example.myapp.Texts.key1());
        assertEquals("bar A baz.", com.example.myapp.Texts.key2("A"));
        assertEquals("foo D bar A baz B quux C.",
                com.example.myapp.Texts.key3("A", "B", "C", "D"));

        assertEquals("bar  baz.", com.example.myapp.Texts.key2(""));
        assertEquals("foo  bar  baz  quux .",
                com.example.myapp.Texts.key3("", "", "", ""));
    }

    @Test
    void missingKey() {
        // To test the behavior when accessing a missing key an extra
        // "missingKey" method was added to the "generated" class that has no
        // corresponding key in the resource bundle.
        assertEquals("??missingKey??", com.example.myapp.Texts.missingKey());
    }
}
