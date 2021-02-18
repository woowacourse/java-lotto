package lotto.exception;

import lotto.view.ErrorView;

public class IllegalLottoNumber extends IllegalArgumentException {
    public IllegalLottoNumber() {
        ErrorView.printIllegalLottoNumberMessage();
    }
}
