package lotto.exception;

import lotto.view.ErrorView;

public class IllegalWinningLottoException extends IllegalArgumentException {
    public IllegalWinningLottoException() {
        ErrorView.printIllegalWinningLottoMessage();
    }
}
