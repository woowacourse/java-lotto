package lotto.exception;

import lotto.view.ErrorView;

public class IllegalLottoCountException extends IllegalArgumentException {
    public IllegalLottoCountException() {
        ErrorView.printIllegalLottoCountMessage();
    }
}
