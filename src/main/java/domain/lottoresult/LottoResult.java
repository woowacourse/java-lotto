package domain.lottoresult;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    public static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";
    Map<LottoRank, ResultCount> result = new EnumMap<>(LottoRank.class);

    public LottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, new ResultCount());
        }
    }

    private void validateNull(LottoRank lottoRank) {
        if (Objects.isNull(lottoRank)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public void add(LottoRank lottoRank) {
        validateNull(lottoRank);
        result.get(lottoRank).increase();
    }

    public ResultCount get(LottoRank lottoRank) {
        validateNull(lottoRank);
        return result.get(lottoRank);
    }
}
