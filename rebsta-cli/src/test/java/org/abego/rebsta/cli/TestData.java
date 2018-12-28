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

package org.abego.rebsta.cli;

import org.abego.commons.io.FileUtil;

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.FileUtil.file;
import static org.abego.commons.io.InputStreamUtil.textOf;
import static org.abego.commons.lang.ClassUtil.textOfResource;

class TestData {
    static final String RESOURCE_BUNDLE_SAMPLE_JAVA_RESOURCE_NAME = "ResourceBundleSample.java";
    static final String RESOURCE_BUNDLE_SAMPLE_NAME = "com.example.ResourceBundleSample";
    static final String RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH = "com/example";

    private static final String RESOURCE_BUNDLE_SAMPLE_PROPERTIES_SIMPLE_RESOURCE_NAME = "ResourceBundleSample.properties";


    static String expectedHelpText() {
        return textOf(TestData.class.getResourceAsStream("expectedHelpText.txt"));
    }

    static String expectedVersionText() {
        return textOf(TestData.class.getResourceAsStream("expectedVersionText.txt"));
    }

    static String expectedResourceBundleSampleJavaText() {
        return textOfResource(TestData.class, "ResourceBundleSample.java.txt");
    }

    static String expectedResourceBundleSampleWithClassNameJavaText() {
        return textOfResource(TestData.class, "ResourceBundleSample-Texts.java.txt");
    }

    static String expectedResourceBundleSamplePackagePrivateJavaText() {
        return textOfResource(TestData.class, "ResourceBundleSample-package-private.java.txt");
    }

    static void createResourceBundleSampleInDirectory(File resourcesDir)
            throws IOException {
        File bundleParentDir = file(resourcesDir, RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH);
        File file = file(bundleParentDir, RESOURCE_BUNDLE_SAMPLE_PROPERTIES_SIMPLE_RESOURCE_NAME);

        FileUtil.copyResourceToFile(
                RebstaCommandTest.class, RESOURCE_BUNDLE_SAMPLE_PROPERTIES_SIMPLE_RESOURCE_NAME,
                file);
    }

}
