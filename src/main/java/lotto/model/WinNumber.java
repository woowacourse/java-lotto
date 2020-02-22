package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinNumber {

    private final int LOTTO_NUMBER_LENGTH = 6;
    private final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";
    private final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하셔야 합니다.";
    private List<Integer> winNumbers;

    public WinNumber(String winNumber) { // TODO : 생성자에서 예외처리를 해야 하는가, 입력단에서 예외처리를 해야 하는가.
        List<Integer> winningNumbers = makeWinNumbers(makeNumbers(winNumber));
        isSizeSix(winningNumbers);
        winNumbers = winningNumbers;
    }

    public boolean contains(int inputNumber) {
        return winNumbers.contains(inputNumber);
    }

    private List<String> makeNumbers(String winNumber) {
        return Arrays.asList(winNumber.split(","));
    }

    private void isSizeSix(List<Integer> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
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

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }
}