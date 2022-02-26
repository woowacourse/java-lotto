package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Result getResult(WinningNumbers winningNumbers) {
        Result result = new Result();

        for (Lotto lotto : lottos) {
            Optional<WinningPrice> winningPrice = winningNumbers.getWinningPrice(lotto);
            winningPrice.ifPresent(result::add);
        }

        return result;
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
    }
}
