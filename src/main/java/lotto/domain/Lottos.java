package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;
    private LottoResult result;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        this.result = new LottoResult();
        purchaseLotto(money);
    }

    private void purchaseLotto(Money money) {
        for (int i = 0; i < getLottoCount(money); i++) {
            lottos.add(new Lotto(new PickedNumbers()));
        }
    }

    public double getYield(Money money) {
        return result.sumOfPrize() / money.getMoney();
    }


    private int getLottoCount(Money money) {
        return money.getMoney() / 1000;
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public LottoResult getResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            result.add(winningLotto.findLottoRank(lotto.getPickedNumbers()));
        }
        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
