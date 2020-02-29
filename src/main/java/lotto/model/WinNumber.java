package lotto.model;

import java.util.List;

public class WinNumber {
    private LottoTicket winNumbers;

    public WinNumber(List<Integer> winningNumbers) {
        ManualTicket manualTicket = new ManualTicket();
        winNumbers = manualTicket.createManualTicket(winningNumbers);
        winNumbers.checkLottoLength(winningNumbers);
        for (int number : winningNumbers) {
            winNumbers.checkLottoNumberRange(number);
        }
    }

    public boolean contains(int inputNumber) {
        return winNumbers.matchesWithNumber(inputNumber);
    }


}