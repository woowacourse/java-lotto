package lotto.exception.ball;

import lotto.exception.LottoExceptionStatus;

public enum BallNumberExceptionStatus implements LottoExceptionStatus {

    BALL_IS_NOT_NUMERIC("번호는 숫자여야 합니다."),
    BALL_IS_NOT_IN_RANGE("번호의 범위는 1부터 45까지여야 합니다.");

    private final String message;

    BallNumberExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
