package domain;

import static constant.LottoConstants.LOTTO_PRICE;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LottoGame {

    private final Lottos lottos;
    private final LottoReferee referee;
    private final TreeMap<LottoResult, Integer> resultsStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(key -> key, value -> 0, (o1, o2) -> o1, TreeMap::new));

    public LottoGame(Lottos lottos, LottoReferee referee) {
        this.lottos = lottos;
        this.referee = referee;
        analyzeLottos();
    }

    private void analyzeLottos() {
        for (Lotto lotto : lottos.getLottos()) {
            LottoResult result = referee.getLottoResult(lotto);
            if (result == null) {
                continue;
            }
            resultsStatistics.put(result, resultsStatistics.get(result) + 1);
        }
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return resultsStatistics;
    }

    public float calculateProfitRatio() {
        Set<LottoResult> lottoResultKeys = resultsStatistics.keySet();

        int totalPrize = lottoResultKeys.stream()
                .mapToInt(result -> sum(result, resultsStatistics.get(result)))
                .sum();

        return (float) totalPrize / getLottoPrice();
    }

    private int sum(LottoResult lottoResult, int count) {
        return lottoResult.getPrize() * count;
    }

    private int getLottoPrice() {
        return lottos.getLottos().size() * LOTTO_PRICE;
    }

    @Override
    public String toString() {
        return "LottoGame{" +
                "lottos=" + lottos +
                ", referee=" + referee +
                ", resultsStatistics=" + resultsStatistics +
                '}';
    }
}
