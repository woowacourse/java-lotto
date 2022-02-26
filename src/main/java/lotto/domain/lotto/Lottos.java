package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Money;
import lotto.domain.Result;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        int amount = money.getValue();
        while (amount >= Lotto.PRICE) {
            lottos.add(Lotto.auto());
            amount -= Lotto.PRICE;
        }
    }

    public Result getResult(LottoWinningNumber winningNumbers) {
        Result result = new Result();

        for (Lotto lotto : lottos) {
            result.add(winningNumbers.getLottoRanking(lotto));
        }

        return result;
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
