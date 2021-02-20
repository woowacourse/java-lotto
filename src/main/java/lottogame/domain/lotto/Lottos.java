package lottogame.domain.lotto;

import lottogame.domain.Money;
import lottogame.domain.dto.LottoResult;
import lottogame.domain.dto.LottoResults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();
    private LottoResults lottoResults;

    public Lottos(List<Lotto> values) {
        lottos.addAll(new ArrayList<>(values));
    }

    public List<List<Integer>> numbersOfLottos() {
        return lottos.stream()
                .map(lotto -> lotto.values())
                .collect(Collectors.toList());
    }

//    public LottoResults findMatchLottos(WinningLotto winningLotto, Money money) {
//        matchLottos(winningLotto);
//        lottoResults.matchedLottos();
//        lottoResults.calculateProfit(money);
//        return lottoResults;
//    }
//
//    private void matchLottos(WinningLotto winningLotto) {
//        lottoResults = new LottoResults();
//        for (Lotto lotto : lottos) {
//            int count = lotto.match(winningLotto.values());
//            boolean bonus = lotto.containsBonus(winningLotto);
//            lottoResults.add(new LottoResult(count, bonus));
//        }
//    }
}
