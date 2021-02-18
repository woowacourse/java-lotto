package domain.lotto;

import domain.result.LottoRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        ArrayList<Lotto> copy = new ArrayList<>(this.lottos);
        return Collections.unmodifiableList(copy);
    }

    public List<LottoRank> findMatches(WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = new ArrayList<>();
        lottos.stream()
                .map(lotto -> lotto.findMatchesNumber(winningLotto))
                .forEach(match -> {
                    lottoRanks.add(match);
                });
        return lottoRanks;
    }
}
