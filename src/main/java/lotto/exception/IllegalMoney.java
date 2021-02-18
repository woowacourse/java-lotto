package lotto.exception;

import lotto.view.ErrorView;
import lotto.view.OutputView;

public class IllegalMoney extends IllegalArgumentException{
    public IllegalMoney() {
        ErrorView.printIllegalMoneyErrorMessage();
    }
}
