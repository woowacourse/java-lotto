package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.strategy.LottoBuyStrategy;

public class Lotto {

    private final Money money;
    private final List<ChoiceNumber> lotto;
    private final LottoResult result;

    public Lotto(Money money, LottoBuyStrategy lottoBuyStrategy) {
        this.money = money;
        this.lotto = lottoBuyStrategy.buyLotto(money);
        this.result = new LottoResult();
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

    public List<ChoiceNumber> getLotto() {
        return lotto;
    }
}
