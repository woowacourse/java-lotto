package lotto.exception;

import lotto.view.ErrorView;

public class IllegalLottoNumberException extends IllegalArgumentException {
    public IllegalLottoNumberException(String message) {
        super(message);
        ErrorView.printErrorMessage(message);
    }
}
