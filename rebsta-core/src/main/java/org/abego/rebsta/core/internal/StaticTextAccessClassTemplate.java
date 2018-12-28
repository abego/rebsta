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
import org.abego.commons.lang.ClassUtil;
import org.abego.rebsta.core.RebstaParameters.ClassVisibility;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.stream.Stream;

import static org.abego.commons.javalang.JavaLangUtil.toJavaIdentifier;
import static org.abego.commons.text.MessageFormatUtil.maxArgumentIndexInMessageFormatPattern;

class StaticTextAccessClassTemplate {

    private static final String CLASS_PATTERN_RESOURCE_NAME = "classPattern.txt"; // NON-NLS
    private static final String METHOD_BODY_PATTERN_RESOURCE_NAME = "methodBodyPattern.txt"; // NON-NLS
    private static final String ACCESS_METHOD_PATTERN_RESOURCE_NAME = "accessMethodPattern.txt"; // NON-NLS
    private static final String PARAMETER_NAME_PREFIX = "p"; // NON-NLS
    private static final String PARAMETER_TYPENAME = "Object"; // NON-NLS

    private final String resourceBundleName;
    private final Properties resourceBundleContent;

    StaticTextAccessClassTemplate(String resourceBundleName,
                                  Properties resourceBundleContent) {
        this.resourceBundleName = resourceBundleName;
        this.resourceBundleContent = resourceBundleContent;
    }

    private static String textOfResource(String name) {
        return ClassUtil.textOfResource(StaticTextAccessClassTemplate.class, name);
    }

    String javaClassText(ClassName className,
                         ClassVisibility testClassVisibility) {
        String modifier = testClassVisibility.javaModifier();

        if (!modifier.isEmpty()) {
            //noinspection StringConcatenation
            modifier = modifier + " ";
        }

        String messageBodyText = textOfResource(METHOD_BODY_PATTERN_RESOURCE_NAME);
        String classTextMessagePattern =
                textOfResource(CLASS_PATTERN_RESOURCE_NAME);

        return MessageFormat.format(classTextMessagePattern,
                className.packagePath(),
                className.simpleName(),
                modifier,
                resourceBundleName,
                classBody(modifier),
                messageBodyText);
    }

    private String classBody(String access) {
        StringBuilder result = new StringBuilder();

        Stream<Object> sortedKeys =
                resourceBundleContent.keySet().stream().sorted();
        sortedKeys.forEachOrdered(key -> {
            String keyString = key.toString();
            appendAccessMethod(
                    result,
                    keyString,
                    resourceBundleContent.getProperty(keyString),
                    access);
        });

        return result.toString();
    }

    private void appendAccessMethod(StringBuilder text, String key,
                                    String value, String access) {
        int maxArgumentIndex = maxArgumentIndexInMessageFormatPattern(value);
        StringBuilder parameterSignature = new StringBuilder();
        StringBuilder arguments = new StringBuilder();

        for (int i = 0; i <= maxArgumentIndex; i++) {
            String paramName = MessageFormat.format(
                    "{0}{1}", PARAMETER_NAME_PREFIX, i);

            if (parameterSignature.length() > 0) {
                parameterSignature.append(", ");
            }

            parameterSignature.append(MessageFormat.format("{0} {1}",
                    PARAMETER_TYPENAME, paramName));

            arguments.append(", ");
            arguments.append(paramName);
        }

        String accessMethodPattern =
                textOfResource(ACCESS_METHOD_PATTERN_RESOURCE_NAME);
        String methodText = MessageFormat.format(accessMethodPattern,
                access,
                toJavaIdentifier(key),
                parameterSignature.toString(),
                key,
                arguments);

        text.append(methodText);
    }
}
