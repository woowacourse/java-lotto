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

    public Statistic getWinningStatistics(WinningLotto winningNumber) {
        List<Rank> ranks = lottos.stream()
                .map(winningNumber::match)
                .collect(Collectors.toList());
        return Statistic.valueOf(ranks);
    }

    public static Lottos add(ManualLottos manualLottos, AutoLottos autoLottos) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos.getLottos());
        lottos.addAll(autoLottos.getLottos());
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}