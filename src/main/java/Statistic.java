import java.util.LinkedHashMap;
import java.util.Map;

public class Statistic {
    private final LinkedHashMap<Rank, Integer> statistics;

    public Statistic(LinkedHashMap<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public double getProfitRate(Money money) {

        double totalWinning = 0;
        for (Map.Entry<Rank, Integer> rankIntegerEntry : statistics.entrySet()) {
            totalWinning += rankIntegerEntry.getKey().getWinningPrice() * rankIntegerEntry.getValue();
        }
        return totalWinning / money.getMoney();
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
