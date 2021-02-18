package lotto.exception;

import lotto.view.ErrorView;
import lotto.view.OutputView;

public class IllegalMoneyException extends IllegalArgumentException{
    public IllegalMoneyException() {
        ErrorView.printIllegalMoneyErrorMessage();
    }
}
