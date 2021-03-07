package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoResults getLottoResults(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, Collectors.counting()));

        Arrays.stream(LottoRank.values())
                .filter(lottoRank -> !results.containsKey(lottoRank))
                .forEach(lottoRank -> results.put(lottoRank, 0L));
        return new LottoResults(results);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public void addAll(Lottos additoryLottos) {
        this.lottos.addAll(additoryLottos.lottos);
    }
}
