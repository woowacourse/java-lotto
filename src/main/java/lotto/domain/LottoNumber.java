package lotto.domain;

import java.util.List;

public class LottoNumber {
    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = 5;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final String NOT_IN_SCOPE_NUMBERS_ERROR_MSG = "로또 번호의 범위가 잘못되었습니다.";
    private static final String NOT_NUMBER_MSG = "정수로 입력하셔야 합니다.";

    public static void validateNumberScope(List<Integer> invalidNumbersUnderScope) {
        invalidNumbersUnderScope.sort(Integer::compareTo);
        if(invalidNumbersUnderScope.get(FIRST_INDEX) < MIN_LOTTO_NUMBER
                || invalidNumbersUnderScope.get(LAST_INDEX) > MAX_LOTTO_NUMBER ){
            throw new IllegalArgumentException(NOT_IN_SCOPE_NUMBERS_ERROR_MSG);
        }
    }
    public static void validateNumber(String invalidInputMoney) {
        try{
            Integer.parseInt(invalidInputMoney);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }
}

