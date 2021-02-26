package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos from(int payCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < payCount; i++) {
            Lotto lotto = Lotto.fromGenerator(new RandomNumberGenerator());
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> toInts() {
        return lottos.stream()
                     .map(Lotto::toInts)
                     .collect(Collectors.toList());
    }

    public LottoStatisticResult match(WinningLotto winningLotto) {
        Map<Rank, Long> rankCount = lottos.stream()
                                              .collect(Collectors.groupingBy(winningLotto::match,
                                                  Collectors.counting()));

        return new LottoStatisticResult(rankCount);
    }
}
