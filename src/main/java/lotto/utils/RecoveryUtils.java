package lotto.utils;

import java.util.function.Function;
import java.util.function.Supplier;
import lotto.view.OutputView;

public final class RecoveryUtils {

    private static final int MAX_ATTEMPTS = 5;

    private RecoveryUtils() {
    }

    public static <T, R> R executeWithRetry(
            Supplier<T> inputSupplier,
            Function<T, R> processFunction
    ) {
        return tryCatchLoop(inputSupplier, processFunction, MAX_ATTEMPTS);
    }

    private static <T, R> R tryCatchLoop(
            Supplier<T> inputSupplier,
            Function<T, R> processFunction,
            int attemptsLeft
    ) {
        if (attemptsLeft <= 0) {
            throw new IllegalArgumentException("최대 재시도 횟수를 초과했습니다.");
        }

        try {
            return processFunction.apply(inputSupplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return tryCatchLoop(inputSupplier, processFunction, attemptsLeft - 1);
        }
    }
}
