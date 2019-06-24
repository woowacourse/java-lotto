package lotto.domain;

import lotto.domain.exception.InvalidBounusBallException;

import java.util.List;

public class BonusBall {
    private LottoNumber bonus;

    public BonusBall(List<LottoNumber> winningLotto, String bonus) {
        try {
            this.bonus = invalidBonusBall(winningLotto, Integer.parseInt(bonus));
        } catch (NumberFormatException e) {
            throw new InvalidBounusBallException(e);
        }

    }

    private LottoNumber invalidBonusBall(List<LottoNumber> winningLotto, int bonus) {
        LottoNumber bonusNumber = new LottoNumber(bonus);
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidBounusBallException(bonusNumber);
        }
        return bonusNumber;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return bonus.toString();
    }
}
