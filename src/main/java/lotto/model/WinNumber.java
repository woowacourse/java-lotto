package lotto.model;

import java.util.List;

public class WinNumber {
    private LottoTicket winNumbers;

    public WinNumber(List<LottoNumber> winningNumbers) {
        this.winNumbers = new LottoTicket(winningNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winNumbers.matchesWithNumber(lottoNumber);
    }
}