package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(Money money) {
        this.lottos = new ArrayList<>();
        int amount = money.getValue();
        while (amount >= Lotto.PRICE) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
            amount -= Lotto.PRICE;
        }
    }

    public Result getResult(List<Number> winningNumbers, Number bonusNumber) {
        Result result = new Result();

        for (Lotto lotto : lottos) {
            WinningPrice winningPrice = lotto.getWinningPrice(winningNumbers, bonusNumber);
            result.add(winningPrice);
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
