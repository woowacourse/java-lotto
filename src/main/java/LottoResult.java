import java.util.HashMap;
import java.util.Map;

public record LottoResult(
        Map<LottoRanking, Integer> result
) {
    public void add(LottoRanking lottoRanking) {
        result.compute(lottoRanking, (key, value) -> value + 1);
    }

    public static LottoResult initialize() {
        Map<LottoRanking, Integer> initialResult = new HashMap<>();
        for (LottoRanking ranking : LottoRanking.values()) {
            initialResult.put(ranking, 0);
        }
        return new LottoResult(initialResult);
    }
}
