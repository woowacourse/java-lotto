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

    public String compareNumbers(LottoTicket lottoTicket) {
        long winningCount = lottoTicket.list().stream()
            .filter(lottoNumber -> this.list().contains(lottoNumber))
            .count();

        boolean isBonus = lottoTicket.list().stream()
            .anyMatch(winningNumber -> winningNumber.equals(bonusNumber));

        return getResult(winningCount, isBonus);
    }

    public String getResult(long winningCount, boolean isBonus) {
        if (winningCount == 3) {
            return "5등!";
        }

        if (winningCount == 4) {
            return "4등!";
        }

        if (winningCount == 5 && isBonus) {
            return "2등!";
        }

        if (winningCount == 5) {
            return "3등!";
        }

        if (winningCount == 6) {
            return "1등!";
        }

        return "꽝..";
    }
}
