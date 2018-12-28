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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.FileUtil.contains;
import static org.abego.commons.io.FileUtil.ensureDirectoryExists;
import static org.abego.commons.io.FileUtil.setReadOnly;
import static org.abego.commons.test.JUnit5Util.assertTextOfFileEquals;
import static org.abego.rebsta.core.TestData.expectedResourceBundleSampleTextAccessClassJavaText;
import static org.abego.rebsta.core.TestData.expectedResourceBundleSampleTextAccessClassJavaText_packagePrivate;
import static org.abego.rebsta.core.TestData.rebstaParametersSample;
import static org.abego.rebsta.core.TestData.rebstaParametersSample_missingResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebstaTest {

    @Test
    void rebsta_ok() throws IOException {

        Rebsta r = Rebsta.rebsta(rebstaParametersSample());

        Assertions.assertNotNull(r);
    }

    @Test
    void generateTextAccessClass_ok() throws IOException {

        RebstaParameters parameters = TestData.rebstaParametersSampleBuilder()
                .withTextAccessClassName(ClassName.className("com.example.myapp.Texts"))
                .build();
        Rebsta r = Rebsta.rebsta(parameters);

        r.generateTextAccessClass();

        File javaFile = r.textAccessClassJavaFile();

        assertTrue(contains(parameters.javaSourcesRoot(), javaFile));
        assertTextOfFileEquals(expectedResourceBundleSampleTextAccessClassJavaText(), javaFile);
    }

    @Test
    void generateTextAccessClass_packagePrivate() throws IOException {
        RebstaParameters parameters = TestData.rebstaParametersSampleBuilder()
                .withTextAccessClassVisibility(RebstaParameters.ClassVisibility.PACKAGE_PRIVATE)
                .withTextAccessClassName(ClassName.className("com.example.myapp.Texts"))
                .build();
        Rebsta r = Rebsta.rebsta(parameters);

        r.generateTextAccessClass();

        File javaFile = r.textAccessClassJavaFile();

        assertTrue(contains(parameters.javaSourcesRoot(), javaFile));
        assertTextOfFileEquals(
                expectedResourceBundleSampleTextAccessClassJavaText_packagePrivate(), javaFile);
    }

    @Test
    void generateTextAccessClass_missingResourceBundle() {

        RebstaException e = assertThrows(RebstaException.class,
                () -> {

                    RebstaParameters parameters =
                            rebstaParametersSample_missingResourceBundle();
                    Rebsta r = Rebsta.rebsta(
                            parameters);

                    r.generateTextAccessClass();
                });

        assertEquals("Error when loading resource bundle `MISSING`", e.getMessage());
    }

    @Test
    void generateTextAccessClass_errorWritingTextsFile() throws IOException {

        RebstaParameters parameters = rebstaParametersSample();
        Rebsta r = Rebsta.rebsta(parameters);
        File textsFileParent = r.textAccessClassJavaFile().getParentFile();
        ensureDirectoryExists(textsFileParent);

        // make the directory containing the Texts file readonly
        // to force an error when writing the Texts.java file
        setReadOnly(textsFileParent);
        try {

            RebstaException e = assertThrows(RebstaException.class,
                    () -> {

                        Rebsta r1 = Rebsta.rebsta(parameters);

                        r1.generateTextAccessClass();

                        File javaFile = r1.textAccessClassJavaFile();

                        assertTrue(contains(parameters.javaSourcesRoot(), javaFile));
                        assertTextOfFileEquals(
                                expectedResourceBundleSampleTextAccessClassJavaText(),
                                javaFile);
                    });

            // replace the (always changing) file path with a static "..."
            String message = e.getMessage().replaceAll("`.+`", "`...`");
            assertEquals("Error when writing Java file `...` for ResourceBundle access class", message);

        } finally {
            setReadOnly(textsFileParent, false);
        }
    }


}