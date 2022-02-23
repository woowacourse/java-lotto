import java.util.LinkedHashMap;
import java.util.Map;

public class Statistic {

    private final LinkedHashMap<Rank, Integer> statistics;

    public Statistic(LinkedHashMap<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public double getProfitRate(Money money) {
        double totalWinning = 0;
        for (Rank rank : statistics.keySet()) {
            totalWinning += rank.getWinningPrice() * statistics.get(rank);
        }
        return totalWinning / money.getMoney();
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
