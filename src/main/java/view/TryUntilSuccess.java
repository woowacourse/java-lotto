package view;

import java.util.function.Supplier;

public class TryUntilSuccess {
    public static <T> T run(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            return run(supplier);
        }
    }
}