package util;

import java.util.function.Supplier;

public class RetryHandler {
    public static <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
