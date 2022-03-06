package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;
    private final LottoResult result;
    private int passiveLottoCount;

    public Lottos() {
        this.lottos = new ArrayList<>();
        this.result = new LottoResult();
        this.passiveLottoCount = 0;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public int getPassiveLottoCount() {
        return passiveLottoCount;
    }

    public LottoResult getResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            checkLottoResult(winningLotto, lotto.getPickedNumbers());
        }
        return result;
    }

    public void purchaseAutoLotto(Money money) {
        for (int i = 0; i < money.getLottoCount(); i++) {
            lottos.add(new Lotto(new PickedNumbers()));
        }
    }

    public void purchaseLotto(PickedNumbers pickedNumbers) {
        lottos.add(new Lotto(pickedNumbers));
        passiveLottoCount++;
    }

    private void checkLottoResult(WinningLotto winningLotto, PickedNumbers pickedNumbers) {
        winningLotto.addLottoResult(pickedNumbers, result);
    }

    public double getYield(Money money) {
        return (double) result.sumOfPrize() / (double) money.getInitialMoney();
    }
}
