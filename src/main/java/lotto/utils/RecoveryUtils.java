package lotto.utils;

import java.util.function.Function;
import java.util.function.Supplier;
import lotto.view.OutputView;

public class RecoveryUtils {

    private RecoveryUtils() {
    }

    public static <T, R> R executeWithRetry(Supplier<T> inputSupplier, Function<T, R> processFunction) {
        while (true) {
            try {
                return processFunction.apply(inputSupplier.get());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);

            }
        }
    }


}
