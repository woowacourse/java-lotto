package lotto.model;

import lotto.exception.NotSixNumbersException;

import java.util.List;

public class WinNumber extends LottoTicket {
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
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}