package lotto.model;

import java.util.Map;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class LottoMachine {

    private final Money money;
    private final Lottos lottos;

    private Map<Rank, Integer> rankCount;

    public LottoMachine(final LottoGenerator lottoGenerator, final Money money) {
        this.money = money;
        this.lottos = lottoGenerator.generateLottos(money.lottoCount(), LottoNumber.LOTTO_NUMBER_MINIMUM_RANGE,
                LottoNumber.LOTTO_NUMBER_MAXIMUM_RANGE, LottoNumbers.LOTTO_LENGTH);
    }

    public void calculateResult(final WinningLotto winningLotto) {
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
