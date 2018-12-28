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
import org.abego.rebsta.core.Rebsta;
import org.abego.rebsta.core.RebstaException;
import org.abego.rebsta.core.RebstaParameters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import static org.abego.commons.io.FileUtil.write;
import static org.abego.commons.util.PropertiesUtil.readProperties;
import static org.abego.rebsta.core.RebstaParameters.ClassVisibility;

public class RebstaImpl implements Rebsta {
    private final RebstaParameters parameters;

    private RebstaImpl(RebstaParameters parameters) {
        this.parameters = parameters;
    }

    public static RebstaImpl rebstaImpl(RebstaParameters parameters) {
        return new RebstaImpl(parameters);
    }

    @Override
    public void generateTextAccessClass() {
        StaticTextAccessClassTemplate template =
                new StaticTextAccessClassTemplate(
                        resourceBundleName().name(),
                        getResourceBundleContent());

        String classText =
                template.javaClassText(textAccessClassName(),
                        textAccessClassVisibility());
        try {
            write(textAccessClassJavaFile(), classText, javaFileCharset());
        } catch (IOException ex) {
            throw new RebstaException(Texts.errorWhenWritingJavaFile(
                    textAccessClassJavaFile()
                            .getAbsolutePath()),
                    ex);
        }
    }

    @Override
    public File textAccessClassJavaFile() {
        return textAccessClassName().javaFileInSourceRoot(javaSourcesRoot());
    }


    private Properties getResourceBundleContent() {

        try {
            File parent = resourceBundleName().parentDirectory(resourcesRoot());
            File propertiesFile =
                    new File(parent,
                            resourceBundleName().simpleName() + ".properties");
            return readProperties(propertiesFile, resourceBundleCharset());

        } catch (Exception ex) {
            throw new RebstaException(Texts.errorWhenLoadingResourceBundle(resourceBundleName()),
                    ex);
        }
    }

    private File resourcesRoot() {
        return parameters.resourcesRoot();
    }

    private FullyQualifiedName resourceBundleName() {
        return parameters.resourceBundleName();
    }

    private Charset resourceBundleCharset() {
        return parameters.resourceBundleCharset();
    }

    private ClassName textAccessClassName() {
        return parameters.textAccessClassName();
    }

    private File javaSourcesRoot() {
        return parameters.javaSourcesRoot();
    }

    private Charset javaFileCharset() {
        return parameters.javaFileCharset();
    }

    private ClassVisibility textAccessClassVisibility() {
        return parameters.textAccessClassVisibility();
    }

}
