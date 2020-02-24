package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final int INITIAL_NUMBER = 0;
    private Map<Integer, Integer> lottoResult;

    public LottoResult() {
        Map<Integer, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH.getCorrect(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH.getCorrect(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD.getCorrect(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND.getCorrect(), INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST.getCorrect(), INITIAL_NUMBER);
        this.lottoResult = lottoResult;
    }

    public int getKey(int key) {
        return lottoResult.get(key);
    }

    public void putValue(int key, int value) {
        this.lottoResult.put(key, value);
    }
}
