package controller;

@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> {
    T get() throws E;
}