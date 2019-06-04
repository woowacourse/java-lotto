package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Reward, Integer> results;

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

    private double incomeRate() {
        double totalIncome = 0.0;
        int totalLottoCount = 0;
        for (Map.Entry<Reward, Integer> entry : results.entrySet()) {
            totalIncome += entry.getKey().money() * entry.getValue();
            totalLottoCount += entry.getValue();
        }
        return totalIncome / totalLottoCount / 1000;
    }

    @Override

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Reward, Integer> entry : results.entrySet()) {
            stringBuilder.append(status(entry));
        }
        stringBuilder.append("총 수익률은 ").append(incomeRate()).append(" 입니다.");

        return "당첨통계\n" +
                "--------\n" +
                stringBuilder.toString();
    }
}
