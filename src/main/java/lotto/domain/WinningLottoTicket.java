package lotto.domain;

import java.util.List;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;

public class WinningLottoTicket extends LottoTicket {
    private final LottoNumber bonusNumber;

    public WinningLottoTicket(List<Integer> winningNumber, int bonusNumber) {
        super(winningNumber);
        validateDuplicatedBonusNumber(bonusNumber);
        this.bonusNumber = LottoNumberFactory.getInstance(bonusNumber);
    }

    private void validateDuplicatedBonusNumber(int bonusNumber) {
        LottoNumber bonusLottoNumber = LottoNumberFactory.getInstance(bonusNumber);

        boolean isDuplicated = this.list().stream()
            .anyMatch(winningNumber -> winningNumber.equals(bonusLottoNumber));

        if (isDuplicated) {
            throw new IllegalArgumentException(
                String.format("보너스 번호가 당첨 번호와 중복되었습니다. 중복된 번호 : %d", bonusNumber)
            );
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
