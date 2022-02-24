package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningStat {

    public static final int DEFAULT_VALUE = 0;

    private final Map<LottoRank, Integer> stat;

    public WinningStat(Map<LottoRank, Integer> ranks) {
        stat = ranks;
    }

    public Map<LottoRank, Integer> getStat() {
        return new HashMap<>(stat);
    }

    public double calculateProfit(int ticketPrice) {
        int num = stat.values().stream()
                .reduce(DEFAULT_VALUE, Integer::sum);

        List<LottoRank> wonLottoRanks = stat.entrySet().stream()
                .filter(rank -> rank.getValue() != DEFAULT_VALUE)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return (double) sumPrize(wonLottoRanks) / (num * ticketPrice);
    }

    private long sumPrize(List<LottoRank> wonLottoRanks) {
        return Arrays.stream(LottoRank.values())
                .filter(wonLottoRanks::contains)
                .mapToLong(rank -> (long) rank.getPrize() * stat.get(rank))
                .reduce(DEFAULT_VALUE, Long::sum);
    }
}
