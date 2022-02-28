package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LottoResult {
    private static final int INIT_VALUE = 0;
    private final HashMap<LottoRank, Integer> result;

    public LottoResult() {
        this.result = new LinkedHashMap<>();
        Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.NOTHING)
                .forEach(lottoRank -> result.put(lottoRank, INIT_VALUE));
    }

    public void add(LottoRank lottoRank) {
        result.put(lottoRank, result.get(lottoRank) + 1);
    }

    public int sumOfPrize() {
        int sum = 0;
        for (LottoRank lottoRank : result.keySet()) {
            sum += result.get(lottoRank) * lottoRank.getPrizeAmount();
        }
        return sum;
    }

    public HashMap<LottoRank, Integer> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "LottoResult{" +
                "result=" + result +
                '}';
    }
}
