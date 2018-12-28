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

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.FileUtil.copyResourceToFile;
import static org.abego.commons.io.FileUtil.file;
import static org.abego.commons.io.FileUtil.tempDirectoryForRun;
import static org.abego.commons.javalang.ClassName.className;
import static org.abego.commons.javalang.FullyQualifiedName.fullyQualifiedName;
import static org.abego.commons.lang.ClassUtil.textOfResource;


class TestData {
    static final FullyQualifiedName RESOURCE_BUNDLE_NAME_SAMPLE = fullyQualifiedName("com.example.ResourceBundleSample");
    static final ClassName TEXT_ACCESS_CLASS_NAME_SAMPLE = className("com.example.myapp.Texts");

    private static final String RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH = "com/example";
    private static final String RESOURCE_BUNDLE_SAMPLE_FILE_NAME = "ResourceBundleSample.properties";
    private static final FullyQualifiedName RESOURCE_BUNDLE_NAME_SAMPLE_MISSING = fullyQualifiedName("MISSING");

    static RebstaParameters rebstaParametersSample() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        File javaSourcesRoot = tempDirectoryForRun();

        createResourceBundleSample(resourcesRoot);

        return new RebstaParameters() {

            @Override
            public File resourcesRoot() {
                return resourcesRoot;
            }

            @Override
            public FullyQualifiedName resourceBundleName() {
                return RESOURCE_BUNDLE_NAME_SAMPLE;
            }

            @Override
            public File javaSourcesRoot() {
                return javaSourcesRoot;
            }
        };
    }

    static RebstaParameters rebstaParametersSample_missingResourceBundle()
            throws IOException {
        return rebstaParametersSampleBuilder()
                .withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE_MISSING)
                .build();
    }

    static RebstaParametersBuilder rebstaParametersSampleBuilder(
            File resourcesRoot, File javaSourcesRoot) throws IOException {
        createResourceBundleSample(resourcesRoot);

        return RebstaParameters.builder().withResourcesRoot(resourcesRoot)
                .withJavaSourcesRoot(javaSourcesRoot)
                .withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE);
    }

    static RebstaParametersBuilder rebstaParametersSampleBuilder() throws IOException {
        return rebstaParametersSampleBuilder(tempDirectoryForRun(), tempDirectoryForRun());
    }

    static String expectedResourceBundleSampleTextAccessClassJavaText() {
        return textOfResource(TestData.class,
                "expectedResourceBundleSampleTextAccessClassJavaText.txt");
    }

    static String expectedResourceBundleSampleTextAccessClassJavaText_packagePrivate() {
        return textOfResource(TestData.class,
                "expectedResourceBundleSampleTextAccessClassJavaText_packagePrivate.txt");
    }

    private static void createResourceBundleSample(File resourcesDir)
            throws IOException {
        File bundleParentDir = file(resourcesDir, RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH);
        File resourceBundleFile = file(bundleParentDir, RESOURCE_BUNDLE_SAMPLE_FILE_NAME);
        copyResourceToFile(TestData.class, "/com/example/ResourceBundleSample.properties", resourceBundleFile);
    }

}
