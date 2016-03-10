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

package org.hawaiiframework.boot.autoconfigure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hawaiiframework.web.exception.HawaiiResponseEntityExceptionHandler;
import org.hawaiiframework.web.resource.PropertyNameConverter;
import org.hawaiiframework.web.resource.ValidationErrorResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcel Overdijk
 * @since 2.0.0
 */
@Configuration
@ConditionalOnWebApplication
public class HawaiiRestAutoConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public PropertyNameConverter propertyNameConverter() {
        return new PropertyNameConverter(objectMapper);
    }

    @Bean
    public ValidationErrorResourceAssembler validationErrorResourceAssembler() {
        return new ValidationErrorResourceAssembler(propertyNameConverter());
    }

    @Bean
    public HawaiiResponseEntityExceptionHandler hawaiiResponseEntityExceptionHandler() {
        return new HawaiiResponseEntityExceptionHandler(validationErrorResourceAssembler());
    }
}
