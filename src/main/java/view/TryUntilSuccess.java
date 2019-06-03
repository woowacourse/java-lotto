package view;

import java.util.function.Supplier;

public class TryUntilSuccess<T> {
    private final Supplier<T> supplier;

    public TryUntilSuccess(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            return get();
        }
    }
}