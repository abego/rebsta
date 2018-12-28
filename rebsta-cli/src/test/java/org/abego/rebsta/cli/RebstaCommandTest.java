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
import org.abego.rebsta.core.RebstaException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.abego.commons.io.PrintStreamToBuffer.printStreamToBuffer;
import static org.abego.commons.test.JUnit5Util.assertTextOfFileEquals;
import static org.abego.rebsta.cli.TestData.RESOURCE_BUNDLE_SAMPLE_NAME;
import static org.abego.rebsta.cli.TestData.createResourceBundleSampleInDirectory;
import static org.abego.rebsta.cli.TestData.expectedResourceBundleSampleJavaText;
import static org.abego.rebsta.cli.TestData.expectedResourceBundleSamplePackagePrivateJavaText;
import static org.abego.rebsta.cli.TestData.expectedResourceBundleSampleWithClassNameJavaText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RebstaCommandTest {


    @Test
    void run_ok() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand(
                resourcesDir.getAbsolutePath(),
                srcDir.getAbsolutePath(),
                RESOURCE_BUNDLE_SAMPLE_NAME);
        r.run(out);

        assertEquals("", out.toString());
        assertTextOfFileEquals(
                expectedResourceBundleSampleJavaText(), r.textAccessClassJavaFile());
    }


    @Test
    void run_withClassname() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand(
                "--classname",
                "com.example.myapp.Texts",
                resourcesDir.getAbsolutePath(),
                srcDir.getAbsolutePath(),
                RESOURCE_BUNDLE_SAMPLE_NAME);
        r.run(out);

        assertEquals("", out.toString());
        assertTextOfFileEquals(
                expectedResourceBundleSampleWithClassNameJavaText(),
                r.textAccessClassJavaFile());
    }

    @Test
    void run_withJavaFileCharset_supportedCharset() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand(
                "--javafile-charset",
                "UTF-8",
                resourcesDir.getAbsolutePath(),
                srcDir.getAbsolutePath(),
                RESOURCE_BUNDLE_SAMPLE_NAME);
        r.run(out);

        assertEquals("", out.toString());
        assertTextOfFileEquals(
                expectedResourceBundleSampleJavaText(), r.textAccessClassJavaFile());
    }

    @Test
    void run_withJavaFileCharset_unsupportedCharset() {

        RebstaException e =
                assertThrows(RebstaException.class, () -> {
                    RebstaCommand r = RebstaCommand.rebstaCommand(
                            "--javafile-charset", "WRONG",
                            "foo", "foo", "foo");
                    r.run(null);
                });

        assertEquals(
                "Error when running rebsta command: Charset WRONG not supported.",
                e.getMessage());
    }

    @Test
    void run_withResourceBundleCharset_supportedCharset() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand(
                "--resourcebundle-charset",
                "ISO-8859-1",
                resourcesDir.getAbsolutePath(),
                srcDir.getAbsolutePath(),
                RESOURCE_BUNDLE_SAMPLE_NAME);
        r.run(out);

        assertEquals("", out.toString());
        assertTextOfFileEquals(
                expectedResourceBundleSampleJavaText(), r.textAccessClassJavaFile());
    }

    @Test
    void run_withResourceBundleCharset_unsupportedCharset() {

        RebstaException e =
                assertThrows(RebstaException.class, () -> {
                    RebstaCommand r = RebstaCommand.rebstaCommand(
                            "--resourcebundle-charset", "WRONG",
                            "foo", "foo", "foo");
                    r.run(null);
                });

        assertEquals(
                "Error when running rebsta command: Charset WRONG not supported.",
                e.getMessage());
    }

    @Test
    void run_withPackagePrivate() throws IOException {

        File resourcesDir = FileUtil.tempDirectoryForRun();
        createResourceBundleSampleInDirectory(resourcesDir);
        File srcDir = FileUtil.tempDirectoryForRun();
        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand(
                "--package-private",
                resourcesDir.getAbsolutePath(),
                srcDir.getAbsolutePath(),
                RESOURCE_BUNDLE_SAMPLE_NAME);
        r.run(out);

        assertEquals("", out.toString());
        assertTextOfFileEquals(
                expectedResourceBundleSamplePackagePrivateJavaText(),
                r.textAccessClassJavaFile());
    }

    @Test
    void run_withVersion() {

        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand("--version");
        r.run(out);

        assertEquals(TestData.expectedVersionText(), out.toString());
    }

    @Test
    void run_withHelp() {

        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand("--help");
        r.run(out);

        assertEquals(TestData.expectedHelpText(), out.toString());
    }

    @Test
    void run_withoutArguments() {

        PrintStreamToBuffer out = printStreamToBuffer();

        RebstaCommand r = RebstaCommand.rebstaCommand();
        r.run(out);

        assertEquals(TestData.expectedHelpText(), out.toString());
    }

    @Test
    void rebstaCommand_withTooFewArguments() {

        RebstaException e =
                assertThrows(RebstaException.class,
                        () -> RebstaCommand.rebstaCommand("foo", "foo"));

        assertEquals("Missing arguments", e.getMessage());

    }

    @Test
    void rebstaCommand_withTooManyArguments() {

        RebstaException e =
                assertThrows(RebstaException.class, () -> RebstaCommand
                        .rebstaCommand("foo", "foo", "foo", "foo"));


        assertEquals("Too many arguments", e.getMessage());
    }

    @Test
    void rebstaCommand_withBadCommandOption() {

        RebstaException e =
                assertThrows(RebstaException.class, () -> RebstaCommand
                        .rebstaCommand("--bad_option", "foo", "foo", "foo",
                                "foo"));

        assertEquals("Error in command: Unrecognized option: --bad_option",
                e.getMessage());
    }
}