package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningStat {

    private final Map<LottoRank, Integer> stats;

    public WinningStat(Map<LottoRank, Integer> ranks) {
        stats = ranks;
    }

    public Map<LottoRank, Integer> getStat() {
        return stats;
    }

    public double calculateProfit(int ticketPrice) {

        int num = stats.values().stream()
                .reduce(0, Integer::sum);

        List<LottoRank> wonLottoRanks = stats.entrySet().stream()
                .filter(rank -> rank.getValue() != 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return (double) sumPrize(wonLottoRanks) / (num * ticketPrice);
    }

    private long sumPrize(List<LottoRank> wonLottoRanks) {
        return Arrays.stream(LottoRank.values())
                .filter(wonLottoRanks::contains)
                .mapToLong(rank -> (long) rank.getPrice() * stats.get(rank))
                .reduce(0, Long::sum);
    }
}
