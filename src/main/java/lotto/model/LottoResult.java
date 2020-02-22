package lotto.model;

import java.util.Map;

public class LottoResult {
    private Map<String, Integer> resultCount;

    public LottoResult(Map<String, Integer> resultCount) {
        this.resultCount = resultCount;
    }

    public int getKey(String key) {
        return resultCount.get(key);
    }

    public void putValue(String key, int value) {
        this.resultCount.put(key, value);
    }
}
