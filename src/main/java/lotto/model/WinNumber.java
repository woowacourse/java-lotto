package lotto.model;

import java.util.List;

public class WinNumber extends LottoTicket {
    private List<Integer> winNumbers;

    public WinNumber(List<Integer> winningNumbers) {
        checkLottoLength(winningNumbers);
        for (int number : winningNumbers) {
            checkLottoNumberRange(number);
        }
        winNumbers = winningNumbers;
    }

    public boolean contains(int inputNumber) {
        return winNumbers.contains(inputNumber);
    }


}