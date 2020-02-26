package lotto.model;

import lotto.exception.NotSixNumbersException;

import java.util.List;

public class WinNumber extends LottoTicket {
    private List<Integer> winNumbers;

    public WinNumber(List<Integer> winningNumbers) {
        checkLottoLength(winningNumbers);
        winNumbers = winningNumbers;
    }

    public boolean contains(int inputNumber) {
        return winNumbers.contains(inputNumber);
    }


}