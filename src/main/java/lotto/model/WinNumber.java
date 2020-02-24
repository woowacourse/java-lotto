package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import lotto.utils.LottoRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinNumber {
    private final int LOTTO_NUMBER_LENGTH = 6;
    private final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";
    private List<Integer> winNumbers;

    public WinNumber(List<Integer> winningNumbers) {
        checkLottoLength(winningNumbers);
        winNumbers = winningNumbers;
    }

    public boolean contains(int inputNumber) {
        return winNumbers.contains(inputNumber);
    }

    private void checkLottoLength(List<Integer> inputs) {
        if (inputs.size() != LottoRules.LOTTO_NUMBER_LENGTH.getNumber()) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }
}