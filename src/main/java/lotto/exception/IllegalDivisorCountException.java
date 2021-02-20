package lotto.exception;

import lotto.view.ErrorView;

public class IllegalDivisorCountException  extends IllegalArgumentException {
    public IllegalDivisorCountException() {
        ErrorView.printIllegalDivisorCountMessage();
    }
}
