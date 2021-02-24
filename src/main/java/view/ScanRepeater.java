package view;

import java.util.function.Supplier;

public class ScanRepeater {
    private static OutputView outputView = OutputView.getInstance();

    public static <T> T scanOrRepeatOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            outputView.printError(e);
            return scanOrRepeatOnError(supplier);
        }
    }
}
