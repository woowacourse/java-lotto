package domain;

import java.util.*;

public class WinningStat {

    private final Map<LottoRank, Integer> stat;

    public WinningStat(Map<LottoRank, Integer> ranks) {
        stat = ranks;
    }

    public double calculateProfit(int ticketPrice) {
        long totalPrize = Arrays.stream(LottoRank.values())
                .mapToLong(rank -> (long) rank.getPrize() * stat.get(rank))
                .reduce(0, Long::sum);

        int num = stat.values().stream()
                .reduce(0, Integer::sum);

        return (double) totalPrize / (num * ticketPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningStat that = (WinningStat) o;
        return stat.equals(that.stat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stat);
    }

    public Map<LottoRank, Integer> getStat() {
        return Collections.unmodifiableMap(stat);
    }
}
