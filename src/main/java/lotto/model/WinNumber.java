package lotto.model;

import java.util.List;

public class WinNumber {
    private LottoTicket winNumbers;

    public WinNumber(LottoTicket winNumbers) {
        this.winNumbers = winNumbers;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winNumbers.matchesWithNumber(lottoNumber);
    }
}