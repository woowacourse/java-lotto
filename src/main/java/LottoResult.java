import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private Map<LottoRank, Integer> resultMap;

    public LottoResult() {
        resultMap = new HashMap<>(Map.of(LottoRank.FIRST, 0, LottoRank.SECOND, 0,
                LottoRank.THIRD, 0, LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 0, LottoRank.NOTHING, 0));
    }

    public Prize getTotalPrize() {
        return resultMap.keySet().stream()
            .map(this::getTotalPrizeByRank)
            .reduce(Prize.ZERO, Prize::add);
    }

    private Prize getTotalPrizeByRank(LottoRank lottoRank) {
        return lottoRank.getPrize().multiply(getCountByRank(lottoRank));
    }

    public int getCountByRank(LottoRank rank) {
        return resultMap.get(rank);
    }

    public void add(LottoRank rank) {
        resultMap.put(rank, getCountByRank(rank) + 1);
    }
}
