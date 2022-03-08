package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public static Lottos generateLottos(List<LottoGenerator> lottoGenerators) {
        return lottoGenerators.stream()
                .map(LottoGenerator::generateLotto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Lottos::new));
    }

    public Statistic getWinningStatistics(WinningLotto winningNumber) {
        List<Rank> ranks = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.toList());
        return Statistic.valueOf(ranks);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}