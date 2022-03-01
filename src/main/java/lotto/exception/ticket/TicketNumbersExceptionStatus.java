package lotto.exception.ticket;

import lotto.exception.LottoExceptionStatus;

public enum TicketNumbersExceptionStatus implements LottoExceptionStatus {

    TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE("로또 번호는 6개로 구성되어야 합니다."),
    TICKET_NUMBERS_CANNOT_BE_DUPLICATED("로또 번호는 중복될 수 없습니다.");

    private final String message;

    TicketNumbersExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
