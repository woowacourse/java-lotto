package verus.common;

import lotto.model.exception.LottoException;

public class MockRunnable extends Mock {

    public void run() {
        call();
    }

    public void throwLottoException() {
        call();
        throw new LottoException() {
        };
    }

    public void throwRuntimeException(String message) {
        call();
        throw new RuntimeException(message);
    }
}
