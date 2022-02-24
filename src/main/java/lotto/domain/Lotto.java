package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final int UNIT_AMOUNT = 1000;

    private final Money money;
    private final List<ChoiceNumber> lotto;
    private final LottoResult result;

    public Lotto(Money money) {
        this.money = money;
        this.lotto = new ArrayList<>();
        this.result = new LottoResult();
        buyLotto();
    }

    private void buyLotto() {
        int bound = getLottoCount(money);
        for (int i = 0; i < bound; i++) {
            lotto.add((new ChoiceNumber()));
        }
    }

    public LottoResult computeResult(WinningNumber winningNumber) {
        lotto.stream()
                .map(winningNumber::findLottoRank)
                .filter(Objects::nonNull)
                .forEach(result::add);
        return result;
    }

    public double getYield() {
        return result.sumOfPrize() / money.getAmount();
    }

    private int getLottoCount(Money money) {
        return money.getAmount() / UNIT_AMOUNT;
    }

    public int getLottoSize() {
        return lotto.size();
    }

    public List<ChoiceNumber> getLotto() {
        return lotto;
    }
}
