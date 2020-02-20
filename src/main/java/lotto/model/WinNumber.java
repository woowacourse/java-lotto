package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinNumber {

    private static final String COMMA = ",";
    private static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하셔야 합니다.";
    private static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";

    public static List<Integer> winNumbers;

    public WinNumber(String winNumber) {
        List<Integer> winNumbers = makeWinNumbers(makeSplitNumbers(winNumber));
        checkLottoNumbersLength(winNumbers);
        this.winNumbers = winNumbers;
    }

    private List<String> makeSplitNumbers(String winNumber) {
        return Arrays.asList(winNumber.split(COMMA));
    }

    private List<Integer> makeWinNumbers(List<String> inputs) {
        List<Integer> numbers = new ArrayList<>();
        for (String input : inputs) {
            numbers.add(isNumberFormat(input));
        }
        return numbers;
    }

    private int isNumberFormat(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    private void checkLottoNumbersLength(List<Integer> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}