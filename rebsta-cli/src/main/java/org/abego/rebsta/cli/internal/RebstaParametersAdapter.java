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

import org.abego.commons.lang.exception.MustNotInstantiateException;
import org.abego.rebsta.core.RebstaException;
import org.abego.rebsta.core.RebstaParameters;
import org.abego.rebsta.core.RebstaParametersBuilder;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import static org.abego.commons.io.FileUtil.file;
import static org.abego.commons.javalang.ClassName.className;
import static org.abego.commons.javalang.FullyQualifiedName.fullyQualifiedName;

class RebstaParametersAdapter {

    RebstaParametersAdapter() {
        throw new MustNotInstantiateException();
    }

    /**
     * Return the <code>rebstaCommandArguments</code> as {@link RebstaParameters}
     */
    static RebstaParameters rebstaParameters(RebstaCommandArguments rebstaCommandArguments) {
        RebstaParametersBuilder paramsBuilder = RebstaParameters.builder();

        if (rebstaCommandArguments.resourceFileCharSetName() != null) {
            paramsBuilder.withResourceBundleCharset(
                    charset(rebstaCommandArguments.resourceFileCharSetName()));
        }
        if (rebstaCommandArguments.javaFileCharSetName() != null) {
            paramsBuilder.withJavaFileCharset(
                    charset(rebstaCommandArguments.javaFileCharSetName()));
        }
        if (rebstaCommandArguments.withPackagePrivate()) {
            paramsBuilder.withTextAccessClassVisibility(
                    RebstaParameters.ClassVisibility.PACKAGE_PRIVATE);
        }
        if (rebstaCommandArguments.textAccessClassName() != null) {
            paramsBuilder.withTextAccessClassName(
                    className(rebstaCommandArguments.textAccessClassName()));
        }

        paramsBuilder.withJavaSourcesRoot(file(rebstaCommandArguments.javaSourceRoot()));
        paramsBuilder.withResourcesRoot(file(rebstaCommandArguments.resourcesRoot()));
        paramsBuilder.withResourceBundleName(fullyQualifiedName(rebstaCommandArguments.resourceBundleName()));

        return paramsBuilder.build();
    }

    private static Charset charset(String charsetName) {
        try {
            return Charset.forName(charsetName);
        } catch (UnsupportedCharsetException e) {
            throw new RebstaException(Texts.charsetNotSupported(charsetName));
        }
    }


}
