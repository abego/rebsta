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
import org.abego.rebsta.core.internal.RebstaParametersBuilderImpl;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.abego.rebsta.core.RebstaParameters.ClassVisibility.PUBLIC;

/**
 * The parameter object for {@link Rebsta}.
 */
public interface RebstaParameters {

    /**
     * The default {@link ClassVisibility} used for the
     * textAccessClassVisibility property.
     */
    ClassVisibility TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT = PUBLIC;
    /**
     * The default {@link Charset} used for the resourceBundleCharset property.
     *
     * <p>This is the "standard" encoding used by Properties.load. See
     * <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html#load(java.io.InputStream)">Properties.load(java.io.InputStream)</a>.</p>
     */
    Charset RESOURCE_BUNDLE_CHARSET_DEFAULT = StandardCharsets.ISO_8859_1;
    /**
     * The default {@link Charset} used for the javaFileCharset property.
     */
    Charset JAVA_FILE_CHARSET_DEFAULT = StandardCharsets.UTF_8;

    static RebstaParametersBuilder builder() {
        return RebstaParametersBuilderImpl.rebstaParametersBuilder();
    }

    /**
     * Return the directory containing resource files, including the
     * ResourceBundle.
     *
     * <p>This is the "root" directory, not including any package directory.</p>
     */
    File resourcesRoot();

    /**
     * Return the name of the ResourceBundle holding the texts to be
     * accessed through the text access class.
     */
    FullyQualifiedName resourceBundleName();

    /**
     * Return the directory containing the Java source files, including the
     * text access class.
     *
     * <p>This is the "root" directory, not including any package directory.</p>
     */
    File javaSourcesRoot();

    /**
     * Return the fully qualified name of the text access class.
     *
     * <p>Default: the (fully qualified) name of the ResourceBundle.</p>
     */
    default ClassName textAccessClassName() {
        return ClassName.className(resourceBundleName().name());
    }

    /**
     * Return the visibility of the text access class.
     *
     * <p>If you want to ensure only classes of a certain package can use the
     * texts via the text access class (e.g. the classes of a certain 'module')
     * make the text access class {@link ClassVisibility#PACKAGE_PRIVATE} and
     * put it in the same package as these classes. This way the compiler will
     * report an error when classes not belonging this package are trying to
     * (accidentally) accessing the texts.</p>
     * <p>
     * <p</>When using {@link ClassVisibility#PUBLIC} (the default)
     * every class can access the texts.</p>
     */
    default ClassVisibility textAccessClassVisibility() {
        return TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT;
    }

    /**
     * Return the {{@link Charset}} used for the resource bundle property files.
     *
     * <p>Default is {@link StandardCharsets#ISO_8859_1}, the "standard"
     * encoding used by {@link java.util.Properties#load(java.io.InputStream)}.</p>
     */
    default Charset resourceBundleCharset() {
        return RESOURCE_BUNDLE_CHARSET_DEFAULT;
    }

    /**
     * Return the {@link Charset} used for the test access class Java source
     * file.
     *
     * <p>Default is {@link StandardCharsets#UTF_8}.</p>
     */
    default Charset javaFileCharset() {
        return JAVA_FILE_CHARSET_DEFAULT;
    }

    /**
     * The visibility of a class.
     */
    enum ClassVisibility {
        /**
         * Visible to all other classes.
         *
         * <p>I.e. the class is defined with a '{@code public}' access
         * modifier. </p>
         */
        PUBLIC("public"), // NON-NLS
        /**
         * Visible to all classes in the package this class belongs to.
         *
         * <p>I.e. the class is defined with no '{@code public}' or
         * '{@code private}' modifier. </p>
         */
        PACKAGE_PRIVATE("");

        private final String javaModifier;

        ClassVisibility(String javaModifier) {
            this.javaModifier = javaModifier;
        }

        /**
         * Return the ClassVisibility as a Java modifier, e.g. "public".
         */
        public String javaModifier() {
            return javaModifier;
        }
    }
}
