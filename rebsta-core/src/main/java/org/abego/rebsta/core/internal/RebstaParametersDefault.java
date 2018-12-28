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

import org.abego.commons.javalang.ClassName;
import org.abego.commons.javalang.FullyQualifiedName;
import org.abego.rebsta.core.RebstaParameters;

import java.io.File;
import java.nio.charset.Charset;

public class RebstaParametersDefault implements RebstaParameters {
    private final FullyQualifiedName resourceBundleName;
    private final ClassName textAccessClassName;
    private final File resourcesRoot;
    private final File javaSourceRoot;
    private final ClassVisibility textAccessClassVisibility;
    private final Charset resourceBundleCharset;
    private final Charset javaFileCharset;

    private RebstaParametersDefault(File resourcesRoot,
                                    File javaSourcesRoot,
                                    FullyQualifiedName resourceBundleName,
                                    ClassName textAccessClassName,
                                    ClassVisibility textAccessClassVisibility,
                                    Charset resourceBundleCharset,
                                    Charset javaFileCharset) {
        this.resourcesRoot = resourcesRoot;
        this.resourceBundleName = resourceBundleName;
        this.textAccessClassName = textAccessClassName;
        this.javaSourceRoot = javaSourcesRoot;
        this.textAccessClassVisibility = textAccessClassVisibility;
        this.resourceBundleCharset = resourceBundleCharset;
        this.javaFileCharset = javaFileCharset;
    }

    static RebstaParametersDefault rebstaParametersDefault(
            File resourcesRoot,
            File javaSourcesRoot,
            FullyQualifiedName resourceBundleName,
            ClassName textAccessClassName,
            ClassVisibility textAccessClassVisibility,
            Charset resourceBundleCharset,
            Charset javaFileCharset) {
        return new RebstaParametersDefault(resourcesRoot, javaSourcesRoot,
                resourceBundleName,
                textAccessClassName,
                textAccessClassVisibility,
                resourceBundleCharset,
                javaFileCharset);
    }

    @Override
    public File resourcesRoot() {
        return resourcesRoot;
    }

    @Override
    public FullyQualifiedName resourceBundleName() {
        return resourceBundleName;
    }

    @Override
    public ClassName textAccessClassName() {
        return textAccessClassName;
    }

    @Override
    public File javaSourcesRoot() {
        return javaSourceRoot;
    }

    @Override
    public ClassVisibility textAccessClassVisibility() {
        return textAccessClassVisibility;
    }

    @Override
    public Charset resourceBundleCharset() {
        return resourceBundleCharset;
    }

    @Override
    public Charset javaFileCharset() {
        return javaFileCharset;
    }
}
