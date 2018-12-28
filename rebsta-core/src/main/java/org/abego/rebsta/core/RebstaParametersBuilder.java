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

import org.abego.commons.javalang.ClassName;
import org.abego.commons.javalang.FullyQualifiedName;
import org.abego.rebsta.core.RebstaParameters.ClassVisibility;

import java.io.File;
import java.nio.charset.Charset;

/**
 * A builder for the parameter object ({@link RebstaParameters}) of {@link Rebsta}.
 */
public interface RebstaParametersBuilder {

    RebstaParametersBuilder withResourceBundleName(
            FullyQualifiedName resourceBundleName);

    RebstaParametersBuilder withResourcesRoot(
            File resourcesRoot);

    RebstaParametersBuilder withTextAccessClassName(
            ClassName textAccessClassName);

    RebstaParametersBuilder withJavaSourcesRoot(
            File javaSourceRoot);

    RebstaParametersBuilder withTextAccessClassVisibility(
            ClassVisibility textAccessClassVisibility);

    RebstaParametersBuilder withResourceBundleCharset(
            Charset resourceBundleCharset);

    RebstaParametersBuilder withJavaFileCharset(Charset javaFileCharset);

    RebstaParameters build();
}