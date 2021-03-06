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

package org.hawaiiframework.web.resource;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for components that convert an object into a resource.
 *
 * The object to convert can be any type, but typically this will be a domain object widely used by
 * the application.
 *
 * The resource should be a type that is only used in the REST layer of the application and which
 * represents the data to be send to the consumer. This is typically a POJO containing Jackson
 * annotations if needed.
 *
 * @param <T> the type of the object to convert
 * @param <D> the type of the resource
 * @author Marcel Overdijk
 * @since 2.0.0
 */
public interface ResourceAssembler<T, D> {

    /**
     * Converts the given object into a resource.
     */
    D toResource(T object);

    /**
     * Converts all given objects into resources.
     *
     * @param objects must not be {@literal null}.
     * @see #toResource(Object)
     */
    default List<D> toResources(Iterable<? extends T> objects) {
        requireNonNull(objects, "'objects' must not be null");
        List<D> result = new ArrayList<D>();
        for (T object : objects) {
            result.add(toResource(object));
        }
        return result;
    }
}
