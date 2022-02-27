package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final LottoReferee referee;
    private final ResultStatistics resultStatistics;

    public LottoGame(Lottos lottos, LottoReferee referee) {
        this.lottos = lottos;
        this.referee = referee;
        this.resultStatistics =  new ResultStatistics(getLottoResults());
    }

    private List<LottoResult> getLottoResults() {
        return lottos.getLottos()
                .stream()
                .map(referee::getLottoResult)
                .collect(Collectors.toList());
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return resultStatistics.getResultStatistics();
    }

    public float calculatePrizePriceRatio() {
        int totalPrize = resultStatistics.calculateTotalPrize();

        return (float) totalPrize / getLottoPrice();
    }

    private int getLottoPrice() {
        return getLottosSize() * LOTTO_PRICE;
    }

    private int getLottosSize() {
        return lottos.getLottos().size();
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
