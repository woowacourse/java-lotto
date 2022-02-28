package lotto.domain;

import lotto.Constant;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;
    private final LottoResult result;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        this.result = new LottoResult();
        purchaseLotto(money);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public LottoResult getResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            checkLottoResult(winningLotto, lotto.getPickedNumbers());
        }
        return result;
    }

    private void checkLottoResult(WinningLotto winningLotto, PickedNumbers pickedNumbers) {
        winningLotto.addLottoResult(pickedNumbers, result);
    }

    public double getYield(Money money) {
        return (double) result.sumOfPrize() / (double) money.getMoney();
    }

    private void purchaseLotto(Money money) {
        for (int i = 0; i < getLottosCount(money); i++) {
            lottos.add(new Lotto(new PickedNumbers()));
        }
    }

    private int getLottosCount(Money money) {
        return money.getMoney() / Constant.UNIT_PRICE;
    }
}
