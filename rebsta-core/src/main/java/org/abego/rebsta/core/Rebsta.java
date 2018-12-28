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

import java.io.File;

import static org.abego.rebsta.core.internal.RebstaImpl.rebstaImpl;

/**
 * Rebsta - ResourceBundle Static Text Access Class Generator
 *
 * <p>Use the factory method {@link #rebsta(RebstaParameters)} to get a Rebsta
 * instance. Then call {@link #generateTextAccessClass()} to actually generate
 * the access class to the texts in the resource bundle.</p>
 */
public interface Rebsta {

    static Rebsta rebsta(RebstaParameters parameters) {
        return rebstaImpl(parameters);
    }

    void generateTextAccessClass();

    File textAccessClassJavaFile();

}
