package lotto.util;

import java.util.function.Supplier;
import lotto.view.OutputView;

public class ObjectCreator {
    
    public static <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.writeErrorMessage(e);
            }
        }
    }
}
