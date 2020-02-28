package lotto.domain;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.util.InputValidationUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoBalls {
    private static final String DELIMITER = ",";
    private static final int LOTTO_BALLS_LENGTH = 6;
    private static final String NUMBER_OUT_OF_RANGE_ERROR_MESSAGE = String.format("%d개의 숫자를 입력해주세요. "
            , LOTTO_BALLS_LENGTH);
    private static final String DUPLICATION_ERROR_MASSAGE = "중복값이 존재합니다.";

    private LottoBalls() {
    }

    public static Set<LottoBall> generateLottoBalls(String lottoBallsInput) {
        String[] splittedLottoBalls = lottoBallsInput.split(DELIMITER);
        validateLottoBallsNumber(splittedLottoBalls);
        validateLottoBallsLength(splittedLottoBalls);
        return validateLottoBallsDuplicationWithGenerateSet(splittedLottoBalls);
    }

    private static void validateLottoBallsNumber(String[] splittedLottoBalls) {
        for (String lottoBall : splittedLottoBalls) {
            InputValidationUtil.returnNumberWithNumberCheck(lottoBall);
        }
    }

    private static void validateLottoBallsLength(String[] splittedLottoBalls) {
        if (splittedLottoBalls.length != LOTTO_BALLS_LENGTH) {
            throw new NumberOutOfRangeException(NUMBER_OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    private static Set<LottoBall> validateLottoBallsDuplicationWithGenerateSet(String[] splittedLottoBalls) {
        Set<LottoBall> lottoBalls = Arrays.stream(splittedLottoBalls)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toSet());
        if (lottoBalls.size() != LOTTO_BALLS_LENGTH) {
            throw new DuplicationException(DUPLICATION_ERROR_MASSAGE);
        }
        return lottoBalls;
    }
}