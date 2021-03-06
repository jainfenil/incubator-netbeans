/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.php.doctrine2.annotations.odm.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.php.api.annotation.util.AnnotationUtils;
import org.netbeans.modules.php.spi.annotation.AnnotationLineParser;
import org.netbeans.modules.php.spi.annotation.AnnotationParsedLine;

/**
 *
 * @author Ondrej Brejla <obrejla@netbeans.org>
 */
public class SimpleAnnotationLineParser implements AnnotationLineParser {

    private static final Set<String> ANNOTATIONS = new HashSet<>();
    static {
        ANNOTATIONS.add("Bin"); //NOI18N
        ANNOTATIONS.add("BinCustom"); //NOI18N
        ANNOTATIONS.add("BinFunc"); //NOI18N
        ANNOTATIONS.add("BinMD5"); //NOI18N
        ANNOTATIONS.add("BinUUID"); //NOI18N
        ANNOTATIONS.add("Boolean"); //NOI18N
        ANNOTATIONS.add("Date"); //NOI18N
        ANNOTATIONS.add("Distance"); //NOI18N
        ANNOTATIONS.add("EmbeddedDocument"); //NOI18N
        ANNOTATIONS.add("File"); //NOI18N
        ANNOTATIONS.add("Float"); //NOI18N
        ANNOTATIONS.add("Hash"); //NOI18N
        ANNOTATIONS.add("Id"); //NOI18N
        ANNOTATIONS.add("Increment"); //NOI18N
        ANNOTATIONS.add("Int"); //NOI18N
        ANNOTATIONS.add("Key"); //NOI18N
        ANNOTATIONS.add("MappedSuperclass"); //NOI18N
        ANNOTATIONS.add("NotSaved"); //NOI18N
        ANNOTATIONS.add("PreLoad"); //NOI18N
        ANNOTATIONS.add("PostLoad"); //NOI18N
        ANNOTATIONS.add("PostPersist"); //NOI18N
        ANNOTATIONS.add("PostRemove"); //NOI18N
        ANNOTATIONS.add("PostUpdate"); //NOI18N
        ANNOTATIONS.add("PrePersist"); //NOI18N
        ANNOTATIONS.add("PreRemove"); //NOI18N
        ANNOTATIONS.add("PreUpdate"); //NOI18N
        ANNOTATIONS.add("String"); //NOI18N
        ANNOTATIONS.add("Timestamp"); //NOI18N
        ANNOTATIONS.add("UniqueIndex"); //NOI18N
    }

    @Override
    public AnnotationParsedLine parse(String line) {
        AnnotationParsedLine result = null;
        String[] tokens = line.split("[ \t]+"); //NOI18N
        for (String annotationName : ANNOTATIONS) {
            if (tokens.length > 0 && AnnotationUtils.isTypeAnnotation(tokens[0], annotationName)) {
                String annotation = tokens[0].trim();
                String description = line.substring(annotation.length()).trim();
                Map<OffsetRange, String> types = new HashMap<>();
                types.put(new OffsetRange(0, annotation.length()), annotation);
                result = new AnnotationParsedLine.ParsedLine(annotationName, types, description, true);
                break;
            }
        }
        return result;
    }

}
