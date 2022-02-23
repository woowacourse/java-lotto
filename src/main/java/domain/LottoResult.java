package domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class LottoResult {
    private final List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public Map<Rank, Long> countRank() {
        return ranks.stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}
