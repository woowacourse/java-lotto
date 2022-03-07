package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultStatus {
    private final Map<Rank, Integer> resultStatistics;

    public ResultStatus() {
        resultStatistics = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            resultStatistics.put(value, 0);
        }
        resultStatistics.remove(Rank.NONE);
    }

    public void judgeResult(List<Rank> results) {
        for (Rank rank : results) {
            updateRecord(rank);
        }
    }

    public void updateRecord(Rank rank) {
        if (resultStatistics.containsKey(rank)) {
            resultStatistics.put(rank, resultStatistics.get(rank) + 1);
        }
    }

    public double calculateTotalIncome() {
        double totalIncome = 0;
        for (Rank rank : resultStatistics.keySet()) {
            totalIncome += rank.getReward() * resultStatistics.get(rank);
        }
        return totalIncome;
    }

    public Map<Rank, Integer> getResultStatistics() {
        return Collections.unmodifiableMap(resultStatistics);
    }
}
