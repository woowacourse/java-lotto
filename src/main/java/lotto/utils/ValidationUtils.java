package lotto.utils;

import lotto.exception.UnderLottoUnitMoney;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {
    private static final int ZERO = 0;
    private static final int LOTTO_UNIT = 1000;
    private static final int MIN_LOTTO_BALL = 1;
    private static final int MAX_LOTTO_BALL = 45;
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final String OUT_OF_RANGE_LOTTO_BALL = "로또 볼의 범위를 벗어났습니다.";
    private static final String POSSIBLE_ONLY_INTEGER = "정수만 입력 가능합니다. 재입력 해주세요.";
    private static final String NEGATIVE_NUMBER = "음수입니다. 재입력 해주세요";
    private static final String UNDER_LOTTO_UNIT_RETURN_CHANGE = "한장도 구매할수 없습니다. 거스름돈 %s원 반환 재입력 해주세요";
    private static final String DUPLICATE_LOTTO_BALL = "로또볼을 중복입력 하였습니다. 재입력 해주세요.";
    private static final String ILLEGAL_LOTTO_BALL_COUNT = "로또볼의 개수를 확인하세요. 재입력 해주세요";
    private static final String OVERFLOW = "OverFlow 발생";
    private static final String UNDERFLOW = "UnderFlow 발생";

    public static void validateLottoBallOutOfRange(String lottoBall) {
        if (Integer.parseInt(lottoBall) < MIN_LOTTO_BALL || Integer.parseInt(lottoBall) > MAX_LOTTO_BALL) {
            throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_BALL);
        }
    }

    public static void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException(POSSIBLE_ONLY_INTEGER);
        }
    }

    public static void validateIntegerNumberFormat(String[] input) {
        for (String balls : input) {
            validateIntegerNumberFormat(balls);
        }
    }

    public static void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER);
        }
    }

    public static void validatePositiveNumber(String[] input) {
        for (String balls : input) {
            validatePositiveNumber(balls);
        }
    }

    public static void validateLottoUnit(String input) {
        if (Integer.parseInt(input) < LOTTO_UNIT) {
            throw new UnderLottoUnitMoney(String.format(UNDER_LOTTO_UNIT_RETURN_CHANGE, input));
        }
    }

    public static void validateDuplicateNumber(String[] ticketNumber) {
        Set<String> compare = new HashSet<>(Arrays.asList(ticketNumber));

        if (compare.size() != ticketNumber.length) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_BALL);
        }
    }

    public static void validateDuplicateNumber(int lottoBall, int bonusBall) {
        if (lottoBall == bonusBall) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_BALL);
        }
    }

    public static void validateLottoTicketSize(int lottoTicketSize) {
        if (lottoTicketSize != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(ILLEGAL_LOTTO_BALL_COUNT);
        }
    }

    public static void validateUnderFlowOrOverFlow(double totalMoney) {
        if (Double.isInfinite(totalMoney)) {
            throw new IllegalArgumentException(OVERFLOW);
        }
        if (Double.isNaN(totalMoney)) {
            throw new IllegalArgumentException(UNDERFLOW);
        }
    }
}
