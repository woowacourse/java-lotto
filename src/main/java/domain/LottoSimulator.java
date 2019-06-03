package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoSimulator {
    public static LottoGroup purchase(List<Lotto> nonRandomLottos, Money money) {
        money = money.minus(Lotto.PRICE.times(nonRandomLottos.size()));

        List<Lotto> randomLottos = new ArrayList<>();
        while (canBuy(money)) {
            money = money.minus(Lotto.PRICE);

            randomLottos.add(LottoFactory.createRandomLotto());
        }
        return LottoGroup.of(nonRandomLottos, randomLottos);
    }

    private static boolean canBuy(Money money) {
        return Lotto.PRICE.lessThan(money) || Lotto.PRICE.equals(money);
    }

    public static RankAnalysis analyze(WinningLotto winningLotto, LottoGroup lottoGroup) {
        RankAnalysisBuilder analysisBuilder = new RankAnalysisBuilder(winningLotto);
        for (Lotto lotto : lottoGroup) {
            analysisBuilder.add(lotto);
        }
        return analysisBuilder.toRankAnalysis();
    }
}
