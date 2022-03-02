package lotto.exception;

import lotto.utils.BallNumberRange;
import lotto.utils.MoneyUnit;
import lotto.utils.TicketSize;

public enum LottoExceptionStatus {

    MONEY_MUST_BE_NUMERIC("구입 금액은 숫자여야 합니다."),
    MONEY_MUST_BE_POSITIVE("구입 금액은 양수여야 합니다."),
    MONEY_MUST_BE_DIVISIBLE(String.format("구입 금액은 %d원으로 나누어 떨어져야 합니다.", MoneyUnit.DIVIDING_UNIT.getUnit())),

    MANUAL_TICKET_COUNT_CANNOT_BE_MORE_THAT_TOTAL_TICKET_COUNT(
            "수동으로 구매할 로또의 개수는 구매 가능한 전체 로또의 개수보다 많을 수 없습니다."),

    TICKET_COUNT_MUST_BE_NUMERIC("로또 개수는 숫자여야 합니다."),
    TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE(
            String.format("로또 번호는 %d개로 구성되어야 합니다.", TicketSize.DEFAULT_SIZE.getSize())),
    TICKET_NUMBERS_CANNOT_BE_DUPLICATED("로또 번호는 중복될 수 없습니다."),

    BALL_NUMBER_MUST_BE_NUMERIC("번호는 숫자여야 합니다."),
    BALL_NUMBER_CANNOT_BE_OUT_OF_RANGE(String.format("번호의 범위는 %d부터 %d까지여야 합니다.",
            BallNumberRange.INCLUSIVE_RANGE_START.getNumber(), BallNumberRange.INCLUSIVE_RANGE_END.getNumber())
    );

    private final String message;

    LottoExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
