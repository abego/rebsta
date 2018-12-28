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

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.FileUtil.tempDirectoryForRun;
import static org.abego.rebsta.core.TestData.RESOURCE_BUNDLE_NAME_SAMPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RebstaParametersBuilderTest {

    @Test
    void build_withRequiredParametersOnly() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        File sourceRoot = tempDirectoryForRun();

        RebstaParametersBuilder builder = RebstaParameters.builder();
        builder.withResourcesRoot(resourcesRoot);
        builder.withJavaSourcesRoot(sourceRoot);
        builder.withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE);

        RebstaParameters params = builder.build();

        assertEquals(params.resourcesRoot(), resourcesRoot);
        assertEquals(params.javaSourcesRoot(), sourceRoot);
        assertEquals(params.resourceBundleName(), RESOURCE_BUNDLE_NAME_SAMPLE);
    }

    @Test
    void build_withMissingResourcesRoot() throws IOException {
        // omit stmt to force error:
        // File resourcesRoot = tempDirectoryForRun();
        File sourceRoot = tempDirectoryForRun();

        RebstaParametersBuilder builder =
                RebstaParameters.builder();
        // omit stmt to force error:
        // builder.withResourcesRoot(resourcesRoot);
        builder.withJavaSourcesRoot(sourceRoot);
        builder.withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE);

        RebstaException e = assertThrows(RebstaException.class, builder::build);
        assertEquals("Missing resourcesRoot parameter", e.getMessage());
    }

    @Test
    void build_withMissingJavaSourcesRoot() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        // omit to force error:
        // File sourceRoot = tempDirectoryForRun();

        RebstaParametersBuilder builder =
                RebstaParameters.builder();
        builder.withResourcesRoot(resourcesRoot);
        // omit to force error:
        //builder.withJavaSourcesRoot(sourceRoot);
        builder.withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE);

        RebstaException e = assertThrows(RebstaException.class, builder::build);
        assertEquals("Missing javaSourcesRoot parameter", e.getMessage());
    }

    @Test
    void build_withMissingResourceBundleName() throws IOException {
        File resourcesRoot = tempDirectoryForRun();
        File sourceRoot = tempDirectoryForRun();

        RebstaParametersBuilder builder =
                RebstaParameters.builder();
        builder.withResourcesRoot(resourcesRoot);
        builder.withResourcesRoot(resourcesRoot);
        builder.withJavaSourcesRoot(sourceRoot);
        // omit stmt to force error:
        //builder.withResourceBundleName(RESOURCE_BUNDLE_NAME_SAMPLE);

        RebstaException e = assertThrows(RebstaException.class, builder::build);
        assertEquals("Missing resourceBundleName parameter", e.getMessage());
    }
}