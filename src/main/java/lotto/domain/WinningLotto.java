package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final Number bonusNum;

    public WinningLotto(Lotto lotto, Number number) {
        validate(lotto, number);
        this.winningLotto = lotto;
        this.bonusNum = number;
    }

    private void validate(Lotto lotto, Number number) {
        if (lotto.isContain(number)) {
            throw new IllegalArgumentException("로또 번호화 중복된 숫자입니다.");
        }
    }

    public WinningType matchLotto(Lotto lotto) {
        return WinningType.valueOf(countMatchNum(lotto), isMatchBonus(lotto));
    }

    private boolean isMatchBonus(Lotto lotto) {
        return lotto.isContain(bonusNum);
    }

    private int countMatchNum(Lotto lotto) {
        List<Number> winningLotto = this.winningLotto.getLotto();
        winningLotto.retainAll(lotto.getLotto());
        return winningLotto.size();
    }


}
