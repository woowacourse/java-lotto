package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningTicket {
    private final Set<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningTicket(Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningTicket from(Set<Integer> winningNumberValues, int bonusNumber) {
        validateSize(winningNumberValues);
        Set<LottoNumber> winningNumbers = winningNumberValues.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());

        return new WinningTicket(winningNumbers, new LottoNumber(bonusNumber));
    }

    private static void validateSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LottoTicket.LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(LottoTicket.LOTTO_TICKET_SIZE_ERROR_MESSAGE);
        }
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
}
