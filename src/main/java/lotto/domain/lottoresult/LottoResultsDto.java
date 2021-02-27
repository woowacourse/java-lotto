package lotto.domain.lottoresult;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lotto.domain.LottoRank;

public class LottoResultsDto {

    private final Map<LottoRank, Integer> result;
    private final double earningRate;

    private LottoResultsDto(Map<LottoRank, Integer> result, double earningRate) {
        this.result = result;
        this.earningRate = earningRate;
    }

    public static LottoResultsDto from(LottoResults lottoResults) {
        Map<LottoRank, Integer> result = new HashMap<>();
        for (Entry<LottoRank, Integer> entry : lottoResults.toMap().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        double earningRate = lottoResults.getEarningsRate();
        return new LottoResultsDto(result, earningRate);
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
