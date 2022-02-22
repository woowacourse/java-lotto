import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private Map<LottoRank, Integer> resultMap;

    public LottoResult() {
        resultMap = new HashMap<>(Map.of(LottoRank.FIRST, 0, LottoRank.SECOND, 0,
                LottoRank.THIRD, 0, LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0, LottoRank.NOTHING, 0));
    }

    public Prize getTotalPrizeAmount() {
        Prize sumPrizeAmount = Prize.ZERO;
        for (LottoRank lottoRank : resultMap.keySet()) {
            sumPrizeAmount = sumPrizeAmount.add(getTotalPrizeByRank(lottoRank));
        }
        return sumPrizeAmount;
    }

    private Prize getTotalPrizeByRank(LottoRank lottoRank) {
        return lottoRank.getPrize().multiply(getCountByRank(lottoRank));
    }

    public int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    public void add(LottoRank rank) {
        resultMap.put(rank, 1);
    }
}
