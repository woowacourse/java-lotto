package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Reward, Integer> results;

    public LottoResult(Map<Reward, Integer> results) {
        this.results = results;
    }

    public LottoResult() {
        results = new LinkedHashMap<>();
        for (Reward reward : Reward.values()) {
            results.put(reward, 0);
        }
    }

    public void plus(Reward reward) {
        if (results.containsKey(reward)) {
            results.put(reward, results.get(reward) + 1);
        }
    }

    private String status(Map.Entry<Reward, Integer> entry) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Reward, Integer> entry : results.entrySet()) {
            stringBuilder.append(status(entry));
        }

        return "당첨통계\n" +
                "--------\n" +
                stringBuilder.toString();
    }
}
