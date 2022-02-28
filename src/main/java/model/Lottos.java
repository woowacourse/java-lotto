package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.lottonumbergenerator.Generator;
import model.lottotickets.Lotto;
import model.winning.Rank;
import model.winning.Statistics;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(final int purchaseCount, final Generator generator) {
        lottos = IntStream
                .rangeClosed(1, purchaseCount)
                .mapToObj(index -> new Lotto(generator))
                .collect(Collectors.toList());
    }

    public Statistics winningResult(final List<Integer> winningNumbers, final Integer bonusNumber) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> result.put(rank, 0));

        for (Lotto lotto : lottos) {
            Rank key = lotto.compareWithWinningNumber(winningNumbers, bonusNumber);
            result.put(key, result.get(key) + 1);
        }
        return new Statistics(result);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
