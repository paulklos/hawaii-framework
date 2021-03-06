/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hawaiiframework.sample.service;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * @author Marcel Overdijk
 */
@Service
public class HelloService {

    private final MessageSource messageSource;

    @Autowired
    public HelloService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String greet(String name, Language language) {
        Locale locale = (language == null) ? Language.HAWAIIAN.getLocale() : language.getLocale();
        if (StringUtils.isBlank(name)) {
            name = messageSource.getMessage("stranger", null, locale);
        }
        Object[] args = new Object[] {name};
        return messageSource.getMessage("greet", args, locale);
    }
}
