package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Rank> calculateRank(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> winningLotto.calculateRank(lotto))
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos joinLottos(Lottos otherLottos) {
        return otherLottos.addLottos(this.lottos);
    }

    private Lottos addLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            this.lottos.add(lotto);
        }
        return new Lottos(this.lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" +
                lottos +
                '}';
    }


}
