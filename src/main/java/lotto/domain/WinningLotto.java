package lotto.domain;

import lotto.Exception.duplicateBonusBallException;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusBall;

    public WinningLotto(Lotto lotto, int bonusBall) {
        if(lotto.isContain(bonusBall)){
            throw new duplicateBonusBallException();
        }
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    @Override
    public String toString() {
        return lotto.toString()+"\n"+"보너스볼 : "+bonusBall;
    }
}
