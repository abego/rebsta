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
import org.abego.rebsta.core.RebstaException;
import org.abego.rebsta.core.RebstaParameters;
import org.abego.rebsta.core.RebstaParameters.ClassVisibility;
import org.abego.rebsta.core.RebstaParametersBuilder;

import java.io.File;
import java.nio.charset.Charset;

import static org.abego.commons.javalang.ClassName.className;
import static org.abego.rebsta.core.RebstaParameters.JAVA_FILE_CHARSET_DEFAULT;
import static org.abego.rebsta.core.RebstaParameters.RESOURCE_BUNDLE_CHARSET_DEFAULT;
import static org.abego.rebsta.core.RebstaParameters.TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT;

public class RebstaParametersBuilderImpl
        implements RebstaParametersBuilder {
    private File resourcesRoot;
    private File javaSourcesRoot;
    private FullyQualifiedName resourceBundleName;
    private ClassName textAccessClassName;
    private ClassVisibility textAccessClassVisibility =
            TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT;
    private Charset resourceBundleCharset = RESOURCE_BUNDLE_CHARSET_DEFAULT;
    private Charset javaFileCharset = JAVA_FILE_CHARSET_DEFAULT;

    private RebstaParametersBuilderImpl() {
    }

    public static RebstaParametersBuilder rebstaParametersBuilder() {
        return new RebstaParametersBuilderImpl();
    }


    @Override
    public RebstaParametersBuilder withResourcesRoot(File resourcesRoot) {
        this.resourcesRoot = resourcesRoot;
        return this;
    }

    @Override
    public RebstaParametersBuilderImpl withJavaSourcesRoot(
            File javaSourceRoot) {
        this.javaSourcesRoot = javaSourceRoot;
        return this;
    }

    @Override
    public RebstaParametersBuilder withResourceBundleName(
            FullyQualifiedName resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
        return this;
    }

    @Override
    public RebstaParametersBuilderImpl withTextAccessClassName(
            ClassName textAccessClassName) {
        this.textAccessClassName = textAccessClassName;
        return this;
    }

    @Override
    public RebstaParametersBuilderImpl withTextAccessClassVisibility(
            ClassVisibility textAccessClassVisibility) {
        this.textAccessClassVisibility = textAccessClassVisibility;
        return this;
    }

    @Override
    public RebstaParametersBuilderImpl withResourceBundleCharset(
            Charset resourceBundleCharset) {
        this.resourceBundleCharset = resourceBundleCharset;
        return this;
    }

    public RebstaParametersBuilderImpl withJavaFileCharset(
            Charset javaFileCharset) {
        this.javaFileCharset = javaFileCharset;
        return this;
    }

    public RebstaParameters build() {
        if (resourcesRoot == null) {
            throw new RebstaException(Texts.missingResourcesRootParameter());
        }
        if (resourceBundleName == null) {
            throw new RebstaException(Texts.missingResourceBundleNameParameter());
        }
        if (javaSourcesRoot == null) {
            throw new RebstaException(Texts.missingJavaSourcesRootParameter());
        }

        return RebstaParametersDefault.rebstaParametersDefault(
                resourcesRoot,
                javaSourcesRoot,
                resourceBundleName,
                textAccessClassName != null
                        ? textAccessClassName
                        : className(resourceBundleName.name()),
                textAccessClassVisibility,
                resourceBundleCharset,
                javaFileCharset);
    }
}