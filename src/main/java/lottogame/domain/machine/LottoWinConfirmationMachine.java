package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;

public class LottoWinConfirmationMachine {

    public static final double LOTTO_NUMBER_COUNT = 6;

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinConfirmationMachine(final LottoNumbers winningNumbers,
        final LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateCount(winningNumbers);
        validateDuplicate(winningNumbers, bonusNumber);
    }

    private void validateCount(final LottoNumbers winningNumbers) {
        if (winningNumbers.toList().size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호 개수는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(final LottoNumbers winningNumbers,
        final LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 당첨번호와 중복됩니다.");
        }
    }

    public int countMatchedWinningNumber(final LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoNumbers()
            .stream()
            .filter(winningNumbers::contains)
            .count();
    }

    public boolean isMatchBonusNumber(final LottoTicket lottoTicket) {
        return lottoTicket.getLottoNumbers().contains(bonusNumber);
    }
}
