package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final LottoReferee referee;
    private final ResultStatistics resultStatistics = new ResultStatistics();

    public LottoGame(Lottos lottos, LottoReferee referee) {
        this.lottos = lottos;
        this.referee = referee;
        analyzeLottos();
    }

    private void analyzeLottos() {
        List<LottoResult> results = getLottoResults();

        resultStatistics.getLottoResultKeys()
                .forEach(key -> resultStatistics.collectAndUpdateStats(results, key));
    }

    private List<LottoResult> getLottoResults() {
        return lottos.getLottos()
                .stream()
                .map(referee::getLottoResult)
                .collect(Collectors.toList());
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return Collections.unmodifiableMap(resultStatistics.getResultStatistics());
    }

    public float calculatePrizePriceRatio() {
        int totalPrize = resultStatistics.calculateTotalPrize();

        return (float) totalPrize / getLottoPrice();
    }

    private int getLottoPrice() {
        return lottos.getLottos().size() * LOTTO_PRICE;
    }

    @Override
    public String toString() {
        return "LottoGame{" +
                "lottos=" + lottos +
                ", referee=" + referee +
                ", resultStatistics=" + resultStatistics +
                '}';
    }
}
