package util;

import view.OutputView;

import java.util.function.Supplier;

public class Repeater {
    private static OutputView outputView = OutputView.getInstance();

    public static <T> T repeatFunctionOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            outputView.printError(e);
            return repeatFunctionOnError(supplier);
        }
    }
}
