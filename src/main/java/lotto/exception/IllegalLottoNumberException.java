package lotto.exception;

import lotto.view.ErrorView;

public class IllegalLottoNumberException extends IllegalArgumentException {
    public IllegalLottoNumberException() {
        ErrorView.printIllegalLottoNumberMessage();
    }
}
