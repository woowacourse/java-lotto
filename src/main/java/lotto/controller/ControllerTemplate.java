package lotto.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;
import lotto.model.exception.LottoException;

public class ControllerTemplate {

    private ControllerTemplate() {

    }

    public static <T> T supplierTemplate(Supplier<T> supplier, Consumer<Exception> exceptionHandler) {
        try {
            return supplier.get();
        } catch (LottoException e) {
            exceptionHandler.accept(e);
            return supplierTemplate(supplier, exceptionHandler);
        }
    }

}
