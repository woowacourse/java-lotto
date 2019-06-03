package lotto.domain.lottoresult;

import lotto.domain.lotto.InvalidLottoNumberException;
import lotto.domain.lotto.InvalidLottoTicketException;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class WinningLotto {
    private final LottoTicket winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        validateBonusNumber();
    }

    public static WinningLotto create(String winningNumbers, String bonusNumber) {
        try {
            return new WinningLotto(LottoTicket.create(winningNumbers), LottoNumber.of(bonusNumber));
        } catch (InvalidLottoTicketException e) {
            throw new InvalidWinningLottoException(e.getMessage());
        } catch (InvalidLottoNumberException e) {
            throw new InvalidWinningLottoException(e.getMessage());
        }
    }

    private void validateBonusNumber() {
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidWinningLottoException("당첨 번호에 포함된 번호입니다.");
        }
    }

    public LottoRank checkLottoRank(LottoTicket userLottoTicket) {
        return LottoRank
                .rankOf(winningLotto.countOfMatch(userLottoTicket),
                        userLottoTicket.contains(bonusNumber));
    }
}
