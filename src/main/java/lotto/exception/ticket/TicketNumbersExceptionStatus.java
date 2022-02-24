package lotto.exception.ticket;

import lotto.exception.LottoExceptionStatus;

public enum TicketNumbersExceptionStatus implements LottoExceptionStatus {

    NUMBERS_IS_NULL("숫자 요소는 NULL이 아니어야 합니다."),
    NUMBERS_OUT_OF_SIZE("숫자 요소는 6개여야 합니다."),
    NUMBERS_DUPLICATED("숫자 요소는 중복될 수 없습니다.");

    private final String message;

    TicketNumbersExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
