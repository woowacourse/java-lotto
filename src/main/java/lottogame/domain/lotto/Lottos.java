package lottogame.domain.lotto;

import lottogame.domain.dto.LottoResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> values) {
        lottos.addAll(new ArrayList<>(values));
    }

    public List<List<Integer>> numbersOfLottos() {
        return lottos.stream()
                .map(lotto -> lotto.values())
                .collect(Collectors.toList());
    }

    public List<LottoResult> matchesLottos(WinningLotto winningLotto) {
        List<LottoResult> lottoResults = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int count = lotto.matchCount(winningLotto);
            boolean bonus = winningLotto.matchBonusBall(lotto);
            lottoResults.add(new LottoResult(count, bonus));
        }
        return lottoResults;
    }
}
