package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final int INITIAL_NUMBER = 0;
    private Map<Integer, Integer> lottoResult;

    public LottoResult() {
        Map<Integer, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND.getRank(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST.getRank(), INITIAL_NUMBER);
        this.lottoResult = lottoResult;
    }

    public int getKey(int key) {
        return lottoResult.get(key);
    }

    public void putValue(int key, int value) {
        this.lottoResult.put(key, value);
    }
}
