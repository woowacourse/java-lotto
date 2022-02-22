import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private Map<LottoRank, Integer> resultMap;

    public LottoResult() {
        resultMap = new HashMap<>(Map.of(LottoRank.FIRST, 0, LottoRank.SECOND, 0,
                LottoRank.THIRD, 0, LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0, LottoRank.NOTHING, 0));
    }

    public BigInteger getTotalPrizeAmount() {
        BigInteger sumPrizeAmount = BigInteger.ZERO;
        for (LottoRank lottoRank : resultMap.keySet()) {
            sumPrizeAmount = sumPrizeAmount.add(BigInteger.valueOf(getTotalPrizeByRank(lottoRank)));
        }
        return sumPrizeAmount;
    }

    private int getTotalPrizeByRank(LottoRank lottoRank) {
        return getCountByRank(lottoRank) * lottoRank.getPrizeAmount();
    }

    public int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    public void add(LottoRank rank) {
        resultMap.put(rank, 1);
    }
}
