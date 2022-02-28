package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Money;
import lotto.domain.Result;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(Money money) {
        int amount = money.getValue();
        while (amount >= Lotto.PRICE) {
            lottos.add(Lotto.auto());
            amount -= Lotto.PRICE;
        }
    }

    public Lottos(Lotto lotto) {
        lottos.add(lotto);
    }

    public Result getResult(WinningLotto winningNumbers) {
        return new Result(this, winningNumbers);
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
