package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> winningLotto, LottoNumber bonusBall) {
        super(winningLotto);
        if (winningLotto.contains(bonusBall)) {
            throw new InvalidBonusBallException("보너스 볼이 당첨 번호와 중복입니다.");
        }
        this.bonusBall = bonusBall;
    }

    public boolean isBonusContain(Lotto userLotto) {
        return userLotto.isContains(bonusBall);
    }
}
