package model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningTicket {
    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningTicket(List<Integer> winningNumbers, int bonusNumber) {
        checkSize(winningNumbers);
        this.winningNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void checkSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }
}
