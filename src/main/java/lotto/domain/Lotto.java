package lotto.domain;

import java.util.List;
import lotto.strategy.LottoBuyStrategy;

public class Lotto {
    private final Money money;
    private final List<ChoiceNumber> lotto;
    private final LottoResult result;

    public Lotto(Money money, LottoBuyStrategy lottoBuyStrategy) {
        this.money = money;
        this.lotto = lottoBuyStrategy.buyLotto(money.getCount());
        this.result = new LottoResult();
    }

    public Lotto(Money money, List<ChoiceNumber> choiceNumbers, LottoBuyStrategy lottoBuyStrategy) {
        this.money = money;
        this.lotto = choiceNumbers;
        this.lotto.addAll(lottoBuyStrategy.buyLotto(money.getCount() - choiceNumbers.size()));
        this.result = new LottoResult();
    }

    public LottoResult computeResult(WinningNumber winningNumber) {
        lotto.stream()
                .map(winningNumber::findLottoRank)
                .filter(lottoRank -> lottoRank != LottoRank.NOTHING)
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

