package lotto.model.winninglotto;

import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoTicket;
import lotto.model.winninglotto.exception.InvalidWinningLottoException;


public class WinningLotto {
    private final LottoTicket winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        checkValidWinningLotto(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(LottoTicket winningLotto, LottoNumber bonusNumber) {
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private void checkValidWinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidWinningLottoException("보너스 숫자가 로또 번호에 포함되어 있습니다.");
        }
    }

    public int matchCount(LottoTicket lottoTicket) {
        return (int) winningLotto.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public boolean hasBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusNumber);
    }

    public LottoTicket getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
