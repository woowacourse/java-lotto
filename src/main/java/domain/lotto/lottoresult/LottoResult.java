package domain.lotto.lottoresult;

import java.util.Map;
import java.util.Objects;

public class LottoResult {
    public static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";

    private Map<LottoRank, ResultCount> result;

    public LottoResult(Map<LottoRank, ResultCount> result) {
        validateNull(result);
        this.result = result;
    }

    private void validateNull(Map<LottoRank, ResultCount> result) {
        if (Objects.isNull(result)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public long calculateEarning() {
        long earning = 0;
        for (LottoRank lottoRank : result.keySet()) {
            earning += result.get(lottoRank).multiply(lottoRank.getWinning());
        }
        return earning;
    }

    public Map<LottoRank, ResultCount> getResult() {
        return result;
    }
}
