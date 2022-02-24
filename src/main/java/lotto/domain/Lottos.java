package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int PRICE = 1000;

    private final List<Lotto> lottos;

    public Lottos(Money money) {
        int amount = money.getValue() / PRICE;

        this.lottos = new ArrayList<>();
        while (amount-- > 0) {
            Lotto lotto = new Lotto();
            lottos.add(lotto);
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
        return Collections.unmodifiableList(lottos);
    }
}
