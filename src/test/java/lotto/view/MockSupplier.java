package lotto.view;

import lotto.model.exception.LottoException;

class MockSupplier extends Mock {

    public Object get() {
        call();
        return new Object();
    }

    public Object throwLottoException() {
        call();
        throw new LottoException() {
        };
    }

    public Object throwRuntimeException(String message) {
        call();
        throw new RuntimeException(message);
    }
}
