package lotto.domain.lotto;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import lotto.domain.number.PayOut;
import lotto.domain.rank.RankFactory;
import lotto.domain.rank.Rank;

public class AnalysedLottos {

    private final List<Rank> ranks;
    private final double yield;

    public AnalysedLottos(Map<Integer, Long> gameResult, PayOut payOut) {
        this.ranks = IntStream.range(1, RankFactory.values().length)
            .boxed()
            .map(key -> RankFactory.createRanking(key, gameResult.getOrDefault(key, 0L)))
            .sorted(comparingInt(Rank::getRank))
            .collect(toList());

        this.yield = ranks.stream()
            .mapToDouble(r -> r.getWinnings() * r.getCount())
            .sum() / payOut.toInt();
    }

    public List<Rank> getRankings() {
        return Collections.unmodifiableList(ranks);
    }

    public double getYield() {
        return yield;
    }
}