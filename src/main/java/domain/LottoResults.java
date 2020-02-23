package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResults {
    private Map<RankType, Integer> lottoResults;

    public LottoResults() {
        this.lottoResults = new HashMap<>();
    }

    public Map<RankType, Integer> getLottoResults() {
        return lottoResults;
    }

    private int getCountSameLottoNumber(List<LottoCount> lottoCounts, int number) {
        return Math.toIntExact(lottoCounts.stream()
                .filter(lottoCount -> lottoCount.isCorrectCount(number))
                .count());
    }

    public void putLottoResults(List<LottoCount> lottoCounts) {
        for (RankType rankType : RankType.values()) {
            this.lottoResults.put(rankType, getCountSameLottoNumber(lottoCounts, rankType.getNumber()));
        }
    }
}
