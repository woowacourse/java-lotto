package lotto.model;

import java.util.Map;
import lotto.model.generator.LottoGenerator;

public class LottoMachine {

    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private final Money money;
    private final Lottos lottos;

    private Map<Rank, Integer> rankCount;

    public LottoMachine(final LottoGenerator lottoGenerator, final Money money) {
        this.money = money;
        this.lottos = lottoGenerator.generateLottos(money.lottoCount(), LOTTO_MINIMUM_NUMBER, LOTTO_MAXIMUM_NUMBER, LOTTO_LENGTH);
    }

    public void calculateResult(WinningLotto winningLotto) {
        this.rankCount = winningLotto.checkRank(lottos);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double) sum / money.getMoney());
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
