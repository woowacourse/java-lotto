package lotto.exception;

import lotto.view.ErrorView;
import lotto.view.OutputView;

public class IllegalManualLottoAmountException extends IllegalArgumentException {
    public IllegalManualLottoAmountException(String message) {
        super(message);
        ErrorView.printErrorMessage(message);
    }
}
