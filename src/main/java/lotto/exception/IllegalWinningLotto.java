package lotto.exception;

import lotto.view.ErrorView;

public class IllegalWinningLotto extends IllegalArgumentException {
    public IllegalWinningLotto() {
        ErrorView.printIllegalWinningLottoMessage();
    }
}
