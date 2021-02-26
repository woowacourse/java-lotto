package lotto.domain.ticket;

import lotto.domain.Prize;
import lotto.domain.number.LottoNumber;

public class WinningLottoTicket {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLottoTicket(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        validateDuplicatedBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedBonusNumber(LottoNumber bonusNumber) {
        boolean isDuplicated = this.winningTicket.containsLottoNumber(bonusNumber);

        if (isDuplicated) {
            throw new IllegalArgumentException(
                String.format("보너스 번호가 당첨 번호와 중복되었습니다. 중복된 번호 : %s", bonusNumber.toString())
            );
        }
    }

    public Prize calculatePrize(LottoTicket lottoTicket) {
        int winningNumberCount = lottoTicket.compareWinningNumber(winningTicket);
        boolean isBonus = lottoTicket.containsLottoNumber(bonusNumber);

        return Prize.findByMatchCount(winningNumberCount, isBonus);
    }
}
