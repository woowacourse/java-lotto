import java.util.Arrays;
import java.util.Map;

public class Statistic {
    private final Map<Rank, Integer> statistics;

    public Statistic(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }


    public double getProfitRate(Money money) {

        double totalWinning = 0;
        for (Map.Entry<Rank, Integer> rankIntegerEntry : statistics.entrySet()) {
            totalWinning += rankIntegerEntry.getKey().getWinningPrice() * rankIntegerEntry.getValue();
        }
        return totalWinning / money.getMoney();
    }
}
