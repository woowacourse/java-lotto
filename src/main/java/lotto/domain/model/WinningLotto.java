package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {

    private Lotto winningLotto;
    private Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        this.winningLotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int matchCount(Lotto purchasedLotto) {
        List<Number> myLotto = new ArrayList<>(purchasedLotto.getLotto());
        List<Number> winLotto = new ArrayList<>(winningLotto.getLotto());
        myLotto.retainAll(winLotto);
        return myLotto.size();
    }

    public boolean matchBonusNumber(Lotto purchasedLotto) {
        return purchasedLotto.getLotto().contains(bonusNumber);
    }
}
