package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningTicket {
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
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }

    public int compareMatchCount(LottoTicket lottoTicket) {
        Set<Integer> lottoNumbers = lottoTicket.getLottoNumberValues();
        List<Integer> winningNumbers = this.winningNumberValues();

        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean isMatchBonusNumber(LottoTicket lottoTicket) {
        Set<Integer> lottoNumbers = lottoTicket.getLottoNumberValues();
        return lottoNumbers.contains(bonusNumber.getValue());
    }

    private void checkSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LottoTicket.LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
