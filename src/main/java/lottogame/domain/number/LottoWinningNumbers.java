package lottogame.domain.number;

import java.util.Set;
import java.util.stream.Collectors;
import lottogame.domain.ticket.LottoTicket;

public class LottoWinningNumbers {

    public static final int WINNING_NUMBERS_COUNT = 6;

    private final Set<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinningNumbers(final Set<Integer> winningNumbers, final int bonusNumber) {
        this.winningNumbers = convertWinningNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
        validateNumbersCount(this.winningNumbers);
        validateDuplicate(this.winningNumbers, this.bonusNumber);
    }

    private Set<LottoNumber> convertWinningNumbers(Set<Integer> winningNumbers) {
        return winningNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet());
    }

    private void validateNumbersCount(final Set<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_COUNT) {
            throw new IllegalArgumentException("로또 당첨 번호는 " + WINNING_NUMBERS_COUNT + "개여야 합니다.");
        }
    }

    private void validateDuplicate(final Set<LottoNumber> winningNumbers,
        final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 포함되어 있습니다.");
        }
    }

    public int countMatchedWinningNumber(final LottoTicket lottoTicket) {
        return (int) this.winningNumbers
            .stream()
            .filter(lottoTicket::contains)
            .count();
    }

    public boolean hasBonusNumber(final LottoTicket lottoTicket) {
        return lottoTicket.contains(this.bonusNumber);
    }
}
