package lotto.view;

import lotto.exception.LottoException;

public class InvalidFormatException extends LottoException {

    public InvalidFormatException(String message) {
        super(message);
    }
}
