package lotto.utils;

import lotto.exception.UnderLottoUnitMoney;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {
    public static final int T = 6;
    private static final int ZERO = 0;
    private static final int LOTTO_UNIT = 1000;
    private static final int MIN_LOTTO_BALL = 1;
    private static final int MAX_LOTTO_BALL = 45;
    private static final int LOTTO_TICKET_SIZE = 6;

    public static void validateLottoBallOutOfRange(int lottoBall){
        if(lottoBall< MIN_LOTTO_BALL || lottoBall > MAX_LOTTO_BALL){
            throw new IllegalArgumentException("로또 볼의 범위를 벗어났습니다.");
        }
    }

    public static void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException("정수만 입력 가능합니다. 재입력 해주세요.");
        }
    }

    public static void validateIntegerNumberFormat(String[] input) {
        for(String balls: input) {
            validateIntegerNumberFormat(balls);
        }
    }

    public static void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException("음수입니다. 재입력 해주세요");
        }
    }

    public static void validatePositiveNumber(String[] input) {
        for(String balls: input) {
            validatePositiveNumber(balls);
        }
    }

    public static void validateLottoUnit(String input) {
        if (Integer.parseInt(input) < LOTTO_UNIT) {
            throw new UnderLottoUnitMoney("한장도 구매할수 없습니다. 재입력 해주세요");
        }
    }

    public static void validateLottoBallRange(int lottoBall){
        if(lottoBall< MIN_LOTTO_BALL || lottoBall > MAX_LOTTO_BALL){
            throw new IllegalArgumentException("로또 볼의 범위를 벗어났습니다.");
        }
    }

    public static void validateDuplicateNumber(String[] ticketNumber){
        Set<String> compare = new HashSet<>(Arrays.asList(ticketNumber));

        if (compare.size() != ticketNumber.length){
            throw new IllegalArgumentException("로또볼이 중복되었습니다. 재입력 해주세요.");
        }
    }

    public static void validateLottoTicketSize(int lottoTicketSize){
        if (lottoTicketSize != LOTTO_TICKET_SIZE){
            throw new IllegalArgumentException("로또볼의 개수를 확인하세요.");
        }
    }
}
