package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public Map<LottoRank, Long> getLottoResults(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, Collectors.counting()));

        Arrays.stream(LottoRank.values())
                .filter(lottoRank -> !results.containsKey(lottoRank))
                .forEach(lottoRank -> results.put(lottoRank, 0L));
        return results;
    }
}