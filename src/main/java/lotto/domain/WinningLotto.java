package lotto.domain;

import lotto.Exception.duplicateBonusBallException;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusBall;

    public WinningLotto(Lotto lotto, int bonusBall) {
        if(lotto.isContainLottoNumber(LottoNumber.getLottoNumber(bonusBall))){
            throw new duplicateBonusBallException();
        }
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    @Override
    public String toString() {
        return lotto.toString()+"\n"+"보너스볼 : "+bonusBall;
    }

    public int getMatchCount(Lotto lotto) {
        return this.lotto.getMatchCount(lotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.isContainLottoNumber(LottoNumber.getLottoNumber(bonusBall));
    }
}
