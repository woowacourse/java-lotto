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

    public static Lottos generateAutoLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        LottoGenerator autoLottoGenerator = new AutoLottoGenerator();
        for (int i = 0; i < count; i++) {
            lottos.add(autoLottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public Statistic getWinningStatistics(WinningLotto winningNumber) {
        List<Rank> ranks = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.toList());
        return Statistic.valueOf(ranks);
    }

    public Lottos concat(Lottos lottos) {
        this.lottos.addAll(lottos.getLottos());
        return this;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}