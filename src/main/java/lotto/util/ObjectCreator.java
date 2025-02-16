package lotto.util;

import java.util.Optional;
import java.util.function.Supplier;
import lotto.view.OutputView;

public class ObjectCreator {
    public static <T> Optional<T> useInputToCreateObject(Supplier<T> creator) {
        try {
            return Optional.of(creator.get());
        } catch (IllegalArgumentException e) {
            OutputView.writeErrorMessage(e);
            return Optional.empty();
        }
    }
}
