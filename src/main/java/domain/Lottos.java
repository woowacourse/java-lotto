package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.generateLottoNumbers(new RandomNumbersGenerator()));
        }
        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public Statistic getWinningStatistics(WinningLotto winningNumber) {
        List<Rank> ranks = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.toList());
        return Statistic.valueOf(ranks);
    }

    public Lottos add(Lottos lottos) {
        this.lottos.addAll(lottos.getLottos());
        return this;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}