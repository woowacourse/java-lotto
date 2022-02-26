package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningTicket {
    private static final int TICKET_SIZE = 6;

    private final Set<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningTicket(Set<Integer> winningNumbers, int bonusNumber) {
        checkSize(winningNumbers);
        this.winningNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public List<Integer> winningNumberValues() {
        return winningNumbers.stream()
                .map(LottoNumber::value)
                .collect(Collectors.toList());
    }

    public Integer bonusNumberValue() {
        return bonusNumber.value();
    }

    public int compareMatchCount(LottoTicket lottoTicket) {
        Set<Integer> lottoNumbers = lottoTicket.lottoNumberValues();
        List<Integer> winningNumbers = this.winningNumberValues();

        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean matchBonusNumber(LottoTicket lottoTicket) {
        Set<Integer> lottoNumbers = lottoTicket.lottoNumberValues();
        return lottoNumbers.contains(bonusNumberValue());
    }

    private void checkSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
