package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.model.generator.AutoLottoNumbersGenerator;

public class LottoMachine {

    private Money money;
    private final Lottos lottos;
//    private final WinningLotto winningLotto;

    private Map<Rank, Integer> rankCount;

    public LottoMachine(final Money money) {
        this.money = money;
        this.lottos = makeLottos();
    }

    private Lottos makeLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.count(); i++) {
            lottos.add(new Lotto(new AutoLottoNumbersGenerator()));
        }
        return new Lottos(lottos);
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
        return ((double) sum / (lottos.size() * Money.PRICE_PER_LOTTO));
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
