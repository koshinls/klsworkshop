package com.koshinls.klsworkshop.registry;

import java.util.function.Supplier;

public class SimpleRegistryEntry<T> implements RegistryEntry<T> {

    private final Supplier<T> supplier;

    public SimpleRegistryEntry(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return supplier.get();
    }
}
