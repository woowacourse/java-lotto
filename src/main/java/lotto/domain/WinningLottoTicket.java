package lotto.domain;

import java.util.List;

public class WinningLottoTicket extends LottoTicket {
    private final LottoNumber bonusNumber;

    public WinningLottoTicket(List<Integer> winningNumber, int bonusNumber) {
        super(winningNumber);
        validateDuplicatedBonusNumber(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateDuplicatedBonusNumber(int bonusNumber) {
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        boolean isDuplicated = this.list().stream()
            .anyMatch(winningNumber -> winningNumber.equals(bonusLottoNumber));

        if (isDuplicated) {
            throw new IllegalArgumentException();
        }
    }

    public Prize compareNumbers(LottoTicket lottoTicket) {
        long winningCount = lottoTicket.list().stream()
            .filter(lottoNumber -> this.list().contains(lottoNumber))
            .count();

        boolean isBonus = lottoTicket.list().stream()
            .anyMatch(winningNumber -> winningNumber.equals(bonusNumber));

        return getResult(winningCount, isBonus);
    }

    public Prize getResult(long winningCount, boolean isBonus) {
        return Prize.findByMatchCount((int) winningCount, isBonus);
    }
}
