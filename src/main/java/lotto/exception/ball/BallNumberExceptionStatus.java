package lotto.exception.ball;

import lotto.exception.LottoExceptionStatus;

public enum BallNumberExceptionStatus implements LottoExceptionStatus {

    BALL_MUST_BE_NUMERIC("번호는 숫자여야 합니다."),
    BALL_CANNOT_BE_OUT_OF_RANGE("번호의 범위는 1부터 45까지여야 합니다.");

    private final String message;

    BallNumberExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
