package model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningTicket {
    private static final int TICKET_SIZE = 6;
    private static final int SECOND_WINNING_COUNT = 5;

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
        if (winningNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException();
        }
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
        List<Integer> lottoNumbers = lottoTicket.lottoNumberValues();
        List<Integer> winningNumbers = this.winningNumberValues();

        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean matchBonusNumber(LottoTicket lottoTicket) {
        List<Integer> lottoNumbers = lottoTicket.lottoNumberValues();
        return lottoNumbers.contains(bonusNumberValue());
    }

    public boolean isSecondWinning(LottoTicket lottoTicket) {
        return compareMatchCount(lottoTicket) == SECOND_WINNING_COUNT &&
               matchBonusNumber(lottoTicket);
    }
}
