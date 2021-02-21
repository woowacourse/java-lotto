package lotto.domain.ticket;

import lotto.domain.Prize;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumberFactory;

import java.util.List;

public class WinningLottoTicket {
    public static final String BONUS_DUPLICATE_WINNING_NUMBER = "보너스 번호가 당첨 번호와 중복되었습니다. 중복된 번호 : %d";
    private final LottoNumber bonusNumber;
    private final LottoTicket winningLottoTicket;

    public WinningLottoTicket(List<Integer> winningNumber, int bonusNumber) {
        winningLottoTicket = LottoTicket.createLottoTicket(winningNumber);
        validateDuplicatedBonusNumber(bonusNumber);
        this.bonusNumber = LottoNumberFactory.of(bonusNumber);
    }

    private void validateDuplicatedBonusNumber(int bonusNumber) {
        LottoNumber bonusLottoNumber = LottoNumberFactory.of(bonusNumber);

        boolean isDuplicated = winningLottoTicket.list().stream()
                .anyMatch(winningNumber -> winningNumber.equals(bonusLottoNumber));

        if (isDuplicated) {
            throw new IllegalArgumentException(
                    String.format(BONUS_DUPLICATE_WINNING_NUMBER, bonusNumber)
            );
        }
    }

    public Prize compareNumbers(LottoTicket lottoTicket) {
        long winningCount = lottoTicket.list().stream()
                .filter(lottoNumber -> winningLottoTicket.list().contains(lottoNumber))
                .count();

        boolean isBonus = lottoTicket.list().stream()
                .anyMatch(winningNumber -> winningNumber.equals(bonusNumber));

        return getResult((int)winningCount, isBonus);
    }

    private Prize getResult(int winningCount, boolean isBonus) {
        return Prize.findByMatchCount(winningCount, isBonus);
    }
}
