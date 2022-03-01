package lotto.domain;

import java.util.List;
import lotto.strategy.LottoBuyStrategy;

public class Lotto {
    private final List<ChoiceNumber> lotto;
    private final LottoResult result;

    public Lotto(int count, LottoBuyStrategy lottoBuyStrategy) {
        this.lotto = lottoBuyStrategy.buyLotto(count);
        this.result = new LottoResult();
    }

    public Lotto(int count, List<ChoiceNumber> choiceNumbers, LottoBuyStrategy lottoBuyStrategy) {
        this.lotto = choiceNumbers;
        this.lotto.addAll(lottoBuyStrategy.buyLotto(count));
        this.result = new LottoResult();
    }

    public LottoResult computeResult(WinningNumber winningNumber) {
        lotto.stream()
                .map(winningNumber::findLottoRank)
                .filter(lottoRank -> lottoRank != LottoRank.NOTHING)
                .forEach(result::add);
        return result;
    }

    public double getYield(Money money) {
        return result.sumOfPrize() / money.getAmount();
    }

    public List<ChoiceNumber> getLotto() {
        return lotto;
    }
}

