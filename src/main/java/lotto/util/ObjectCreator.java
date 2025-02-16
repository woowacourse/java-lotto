package lotto.util;

import java.util.function.Supplier;
import lotto.view.OutputView;

public class ObjectCreator {
    public static <T> T useInputToCreateObject(Supplier<T> creator) {
        try {
            return creator.get();
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e);
            return null;
        }
    }
}
