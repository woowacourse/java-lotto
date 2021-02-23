package lotto.ticket;

import lotto.game.LottoCount;

import java.util.List;

import static lotto.game.LottoCount.ZERO;

public class TicketValidation {
    public static final String ERROR_MESSAGE_DUPLICATED = "중복되는 숫자가 존재합니다.";
    public static final String ERROR_MESSAGE_INVALID_INPUT = "잘못된 입력입니다.";
    public static final String ERROR_MESSAGE_INVALID_SIZE = "숫자는 6개여야 합니다.";
    public static final String ERROR_MESSAGE_INVALID_RANGE = "숫자는 1부터 45사이여야 합니다.";
    public static final String ERROR_MESSAGE_INVALID_AMOUNT = "구매 금액보다 많이 구입할 수 없습니다.";

    private TicketValidation() {
    }

    public static void validateNumber(String value) {
        emptyNumberCheck(value);
        for (int i = 0; i < value.length(); i++) {
            char number = value.charAt(i);
            checkIsNumber(number);
        }
    }

    private static void emptyNumberCheck(String value) {
        if (value.length() == ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    private static void checkIsNumber(char number) {
        if (!Character.isDigit(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public static void validateNumberInRange(int number) {
        if (number < Ticket.MIN_NUMBER || number > Ticket.MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public static void validateSize(List<Number> value) {
        if (value.size() != Ticket.NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_SIZE);
        }
    }

    public static void validateDuplicated(List<Number> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public static void validateSameNumber(Number number, WinnerTicket winnerTicket) {
        if (winnerTicket.isSameNumber(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public static void validateAmount(int currentCount, LottoCount count) {
        if (!count.canPurchase(currentCount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_AMOUNT);
        }
    }
}