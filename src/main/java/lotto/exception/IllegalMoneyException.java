package lotto.exception;

import lotto.view.ErrorView;

public class IllegalMoneyException extends IllegalArgumentException {
    public IllegalMoneyException(String message) {
        super(message);
        ErrorView.printErrorMessage(message);
    }
}
