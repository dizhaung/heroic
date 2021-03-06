/*
 * Copyright (c) 2015 Spotify AB.
 *
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

package com.spotify.heroic.test;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValueSuppliers {
    private final List<ValueSupplier> suppliers;

    @java.beans.ConstructorProperties({ "suppliers" })
    public ValueSuppliers(
        final List<ValueSupplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Optional<Object> lookup(
        final Type type, final boolean secondary, final String name
    ) {
        return suppliers
            .stream()
            .map(s -> s.supply(type, secondary, name))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
    }

    public static ValueSuppliers empty() {
        return new ValueSuppliers(ImmutableList.of());
    }

    @FunctionalInterface
    public interface ValueSupplier {
        Optional<Object> supply(Type type, boolean secondary, String name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<ValueSupplier> suppliers = new ArrayList<>();

        public Builder supplier(final ValueSupplier supplier) {
            suppliers.add(supplier);
            return this;
        }

        public ValueSuppliers build() {
            return new ValueSuppliers(ImmutableList.copyOf(suppliers));
        }
    }
}
