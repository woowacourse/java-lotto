package model;

import java.util.*;
import java.util.stream.Stream;

public class LottoResultTable implements Iterable<Map.Entry<LottoRank, Integer>> {
    private final Map<LottoRank, Integer> table;

    public LottoResultTable(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.table = Collections.unmodifiableMap(new LinkedHashMap<LottoRank, Integer>() {{
            Stream.of(LottoRank.values()).forEach(rank -> put(rank, 0));
            lottos.forEach(lotto -> lotto.match(winningNumbers).map(x -> put(x, get(x) + 1)));
        }});
    }

    public Money totalEarnings() {
        return new Money(
                this.table.entrySet().stream()
                            .mapToInt(x -> x.getKey().prize().amount() * x.getValue())
                            .sum()
        );
    }

    @Override
    public Iterator<Map.Entry<LottoRank, Integer>> iterator() {
        return table.entrySet().iterator();
    }
}