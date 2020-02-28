package lotto.model;

import java.util.List;

public class WinNumber {
    private LottoTicket winNumbers;

    public WinNumber(List<Integer> winningNumbers) {
        winNumbers = new LottoTicket(winningNumbers);
        winNumbers.checkLottoLength(winningNumbers);
        for (int number : winningNumbers) {
            winNumbers.checkLottoNumberRange(number);
        }
    }

    public boolean contains(int inputNumber) {
        return winNumbers.matchesWithNumber(inputNumber);
    }


}