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

import org.abego.rebsta.core.RebstaParameters.ClassVisibility;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.abego.commons.io.FileUtil.tempDirectoryForRun;
import static org.abego.rebsta.core.RebstaParameters.JAVA_FILE_CHARSET_DEFAULT;
import static org.abego.rebsta.core.RebstaParameters.RESOURCE_BUNDLE_CHARSET_DEFAULT;
import static org.abego.rebsta.core.RebstaParameters.TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT;
import static org.abego.rebsta.core.TestData.RESOURCE_BUNDLE_NAME_SAMPLE;
import static org.abego.rebsta.core.TestData.TEXT_ACCESS_CLASS_NAME_SAMPLE;
import static org.abego.rebsta.core.TestData.rebstaParametersSample;
import static org.abego.rebsta.core.TestData.rebstaParametersSampleBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RebstaParametersTest {

    @Test
    void constants_ok() {
        assertEquals(ClassVisibility.PUBLIC, TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT);
        assertEquals(StandardCharsets.ISO_8859_1, RESOURCE_BUNDLE_CHARSET_DEFAULT);
        assertEquals(StandardCharsets.UTF_8, JAVA_FILE_CHARSET_DEFAULT);
    }

    @Test
    void resourcesRoot_ok() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        File sourceRoot = tempDirectoryForRun();
        RebstaParameters rebstaParameters = rebstaParametersSampleBuilder(resourcesRoot, sourceRoot)
                .build();

        assertEquals(resourcesRoot, rebstaParameters.resourcesRoot());
    }

    @Test
    void javaSourceRoot_ok() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        File sourceRoot = tempDirectoryForRun();
        RebstaParameters rebstaParameters = rebstaParametersSampleBuilder(resourcesRoot, sourceRoot)
                .build();

        assertEquals(sourceRoot, rebstaParameters.javaSourcesRoot());
    }


    @Test
    void textAccessClassName_defaultValue() throws IOException {
        RebstaParameters rebstaParameters = rebstaParametersSample();

        assertEquals(RESOURCE_BUNDLE_NAME_SAMPLE,
                rebstaParameters.textAccessClassName());
    }

    @Test
    void resourceBundleCharset_defaultValue() throws IOException {
        RebstaParameters rebstaParameters = rebstaParametersSample();

        assertEquals(RESOURCE_BUNDLE_CHARSET_DEFAULT,
                rebstaParameters.resourceBundleCharset());
    }

    @Test
    void javaFileCharset_defaultValue() throws IOException {
        RebstaParameters rebstaParameters = rebstaParametersSample();

        assertEquals(JAVA_FILE_CHARSET_DEFAULT,
                rebstaParameters.javaFileCharset());
    }

    @Test
    void textAccessClassVisibility_defaultValue() throws IOException {
        RebstaParameters rebstaParameters = rebstaParametersSample();

        assertEquals(TEXT_ACCESS_CLASS_VISIBILITY_DEFAULT,
                rebstaParameters.textAccessClassVisibility());
    }

    @Test
    void textAccessClassName_ok() throws IOException {
        RebstaParameters rebstaParameters = TestData.rebstaParametersSampleBuilder()
                .withTextAccessClassName(TEXT_ACCESS_CLASS_NAME_SAMPLE)
                .build();

        assertEquals(TEXT_ACCESS_CLASS_NAME_SAMPLE, rebstaParameters.textAccessClassName());
    }


    @Test
    void javaFileCharset_ok() throws IOException {
        RebstaParameters rebstaParameters = TestData.rebstaParametersSampleBuilder()
                .withJavaFileCharset(StandardCharsets.ISO_8859_1)
                .build();

        assertEquals(StandardCharsets.ISO_8859_1, rebstaParameters.javaFileCharset());
    }

    @Test
    void textAccessClassVisibility_ok() throws IOException {
        RebstaParameters rebstaParameters = TestData.rebstaParametersSampleBuilder()
                .withTextAccessClassVisibility(ClassVisibility.PACKAGE_PRIVATE)
                .build();

        assertEquals(ClassVisibility.PACKAGE_PRIVATE, rebstaParameters.textAccessClassVisibility());
    }

    @Test
    void resourceBundleCharset_ok() throws IOException {
        RebstaParameters params = TestData.rebstaParametersSampleBuilder()
                .withResourceBundleCharset(StandardCharsets.UTF_8)
                .build();

        assertEquals(StandardCharsets.UTF_8, params.resourceBundleCharset());
    }

}