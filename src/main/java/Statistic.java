import java.util.Map;
import java.util.EnumMap;

public class Statistic {

    private final EnumMap<Rank, Integer> statistics;

    public Statistic(EnumMap<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public double getProfitRate(Money money) {
        double totalWinning = 0;
        for (Rank rank : statistics.keySet()) {
            totalWinning += rank.getWinningPrice() * statistics.get(rank);
        }
        return totalWinning / money.getMoney();
    }

    public EnumMap<Rank, Integer> getStatistics() {
        return statistics;
    }
}
