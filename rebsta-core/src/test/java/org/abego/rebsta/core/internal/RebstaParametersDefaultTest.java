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
import org.abego.rebsta.core.RebstaParameters.ClassVisibility;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.abego.commons.io.FileUtil.file;
import static org.abego.commons.javalang.ClassName.className;
import static org.abego.commons.javalang.FullyQualifiedName.fullyQualifiedName;
import static org.abego.rebsta.core.internal.RebstaParametersDefault.rebstaParametersDefault;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RebstaParametersDefaultTest {

    private static final File RESOURCES_ROOT = file("src/main/res");
    private static final File JAVA_SOURCES_ROOT = file("src/main/j");
    private static final FullyQualifiedName RESOURCE_BUNDLE_NAME =
            fullyQualifiedName("x.y.Texts");
    private static final ClassName TEXT_ACCESS_CLASSNAME =
            className("a.b.Msg");
    private static final ClassVisibility TEXT_ACCESS_CLASS_VISIBILITY =
            ClassVisibility.PUBLIC;
    private static final Charset RESOURCE_BUNDLE_CHARSET =
            StandardCharsets.UTF_16BE;
    private static final Charset JAVA_FILE_CHARSET = StandardCharsets.UTF_16LE;

    private static RebstaParameters sample() {
        return rebstaParametersDefault(
                RESOURCES_ROOT,
                JAVA_SOURCES_ROOT,
                RESOURCE_BUNDLE_NAME,
                TEXT_ACCESS_CLASSNAME,
                TEXT_ACCESS_CLASS_VISIBILITY,
                RESOURCE_BUNDLE_CHARSET,
                JAVA_FILE_CHARSET);
    }

    @Test
    void resourcesRoot_ok() {
        assertEquals(RESOURCES_ROOT, sample().resourcesRoot());
    }

    @Test
    void resourceBundleName_ok() {
        assertEquals(RESOURCE_BUNDLE_NAME, sample().resourceBundleName());
    }

    @Test
    void textAccessClassName_ok() {
        assertEquals(TEXT_ACCESS_CLASSNAME, sample().textAccessClassName());
    }

    @Test
    void javaSourcesRoot_ok() {
        assertEquals(JAVA_SOURCES_ROOT, sample().javaSourcesRoot());
    }

    @Test
    void textAccessClassVisibility_ok() {
        assertEquals(TEXT_ACCESS_CLASS_VISIBILITY, sample().textAccessClassVisibility());
    }

    @Test
    void resourceBundleCharset_ok() {
        assertEquals(RESOURCE_BUNDLE_CHARSET, sample().resourceBundleCharset());
    }

    @Test
    void javaFileCharset_ok() {
        assertEquals(JAVA_FILE_CHARSET, sample().javaFileCharset());
    }
}