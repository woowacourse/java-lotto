package lotto.domain.lotto;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lotto.domain.number.PayOut;
import lotto.domain.rank.RankFactory;
import lotto.domain.rank.Rank;

public class AnalysedLottos {

    private final List<Rank> ranks;
    private final double yield;

    public AnalysedLottos(Map<RankFactory, Long> gameResult, PayOut payOut) {
        this.ranks = Arrays.stream(RankFactory.values())
                .filter(rank -> !rank.equals(RankFactory.FAIL))
                .map(key -> RankFactory.createRanking(key, gameResult.getOrDefault(key, 0L)))
                .sorted(comparingInt(Rank::getRank))
                .collect(toList());

        this.yield = ranks.stream()
                .mapToDouble(r -> r.getWinnings() * r.getCount())
                .sum() / payOut.getValueAsInt();
    }

    public List<Rank> getRankings() {
        return Collections.unmodifiableList(ranks);
    }

    public double getYield() {
        return yield;
    }
}