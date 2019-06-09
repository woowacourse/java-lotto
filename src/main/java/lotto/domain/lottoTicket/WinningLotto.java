package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;
import lotto.domain.exception.OverlapLottoBonusNumberException;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonus;

    public WinningLotto(List<Integer> winningNumbers, int bonus) {
        if (winningNumbers.contains(bonus)) {
            throw new OverlapLottoBonusNumberException("당첨 번호와 중복된 번호 입니다.");
        }
        this.winningLotto = new Lotto(winningNumbers);
        this.bonus = new LottoNumber(bonus);
    }

    public boolean isContainBonus(Lotto lotto) {
        return lotto.isContainNumber(bonus);
    }

    public int matchLottoNumbers(Lotto lotto) {
        return lotto.matchLottoNumbers(winningLotto);
    }

    @Override
    public String toString() {
        return "WinningLotto : " +
                winningLotto +
                ", bonus : " + bonus;
    }
}
