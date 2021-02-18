package lotto.exception;

import lotto.view.ErrorView;

public class IllegalLottoNumbers extends IllegalArgumentException{
    public IllegalLottoNumbers() {
        ErrorView.printIllegalLottoNumbersMessage();
    }
}
