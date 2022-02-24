package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private Money money;
    private List<Lotto> lottos;
    private LottoResult result;

    public Lottos(Money money) {
        this.money = money;
        this.lottos = new ArrayList<>();
        this.result = new LottoResult();
        purchaseLotto();
    }

    private void purchaseLotto() {
        for (int i = 0; i < getLottoCount(money); i++) {
            lottos.add(new Lotto(new ChoiceNumber()));
        }
    }

    public double getYield() {
        return result.sumOfPrize() / money.getAmount();
    }


    private int getLottoCount(Money money) {
        return money.getAmount() / 1000;
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public LottoResult getResult(WinningNumber winningNumber) {
        for (Lotto lotto : lottos) {
            LottoRank lottoRank = winningNumber.findLottoRank(lotto.getChoiceNumber());
            if (lottoRank != null) {
                result.add(lottoRank);
            }
        }
        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
