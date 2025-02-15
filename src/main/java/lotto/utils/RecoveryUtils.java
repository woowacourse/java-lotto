package lotto.utils;

import java.util.function.Supplier;
import lotto.view.OutputView;

public final class RecoveryUtils {

    private RecoveryUtils() {
    }

    public static <T> T executeWithRetry(Supplier<T> inputSupplier) {
        try {
            return inputSupplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return executeWithRetry(inputSupplier);
        }
    }

}
