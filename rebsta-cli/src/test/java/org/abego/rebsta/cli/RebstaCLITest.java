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
import org.abego.commons.io.PrintStreamToBuffer;
import org.abego.commons.lang.exception.MustNotInstantiateException;
import org.abego.commons.misc.RunOnClose;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.FileUtil.file;
import static org.abego.commons.io.PrintStreamToBuffer.printStreamToBuffer;
import static org.abego.commons.lang.ArrayUtil.array;
import static org.abego.commons.lang.SystemUtil.systemOutRedirect;
import static org.abego.commons.test.JUnit5Util.assertTextOfFileEquals;
import static org.abego.rebsta.cli.TestData.RESOURCE_BUNDLE_SAMPLE_JAVA_RESOURCE_NAME;
import static org.abego.rebsta.cli.TestData.RESOURCE_BUNDLE_SAMPLE_NAME;
import static org.abego.rebsta.cli.TestData.RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH;
import static org.abego.rebsta.cli.TestData.createResourceBundleSampleInDirectory;
import static org.abego.rebsta.cli.TestData.expectedResourceBundleSampleJavaText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebstaCLITest {

    @Test
    void constructor() {
        assertThrows(MustNotInstantiateException.class, RebstaCLI::new);
    }

    @Test
    void runCommand_withOnErrorAndBadCommandOption() {

        PrintStreamToBuffer out = printStreamToBuffer();
        PrintStreamToBuffer err = printStreamToBuffer();
        boolean[] wasOnErrorCalled = new boolean[]{false};
        Runnable onError = () -> wasOnErrorCalled[0] = true;

        RebstaCLI.runCommand(array("--bad_option", "foo",
                "foo", "foo", "foo"), out, err, onError);

        assertEquals("", out.toString());
        assertEquals("Error in command: Unrecognized option: --bad_option\n",
                err.toString());
        assertTrue(wasOnErrorCalled[0]);
    }

    @Test
    void runCommand_withoutOnErrorButBadCommandOption() {

        PrintStreamToBuffer out = printStreamToBuffer();
        PrintStreamToBuffer err = printStreamToBuffer();

        RebstaCLI.runCommand(array("--bad_option", "foo",
                "foo", "foo", "foo"), out, err);

        assertEquals("", out.toString());
        assertEquals("Error in command: Unrecognized option: --bad_option\n",
                err.toString());
    }

    @Test
    void main_ok() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        try (RunOnClose r = systemOutRedirect(out)) {
            RebstaCLI.main(array(
                    resourcesDir.getAbsolutePath(),
                    srcDir.getAbsolutePath(),
                    RESOURCE_BUNDLE_SAMPLE_NAME));
        }

        // as the "main" method does not give us access to the
        // "textAccessClassJavaFile" we need to construct it manually, according
        // to the parameters we passed to the main method
        File packageDirectory = file(srcDir, RESOURCE_BUNDLE_SAMPLE_PACKAGE_DIRECTORY_PATH);
        File textAccessClassJavaFile = file(packageDirectory, RESOURCE_BUNDLE_SAMPLE_JAVA_RESOURCE_NAME);

        assertEquals("", out.toString());
        assertTextOfFileEquals(expectedResourceBundleSampleJavaText(), textAccessClassJavaFile);
    }

}
