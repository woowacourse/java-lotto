package lottogame.domain.lotto;

import lottogame.domain.statistic.Rank;
import lottogame.domain.dto.LottoDto;
import lottogame.domain.statistic.LottoResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> values) {
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(new ArrayList<>(values));
        this.lottos = new ArrayList<>(allLottos);
    }

    public List<LottoDto> numbersOfLottos() {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.values()))
                .collect(Collectors.toList());
    }

    public LottoResults matchesLottos(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int count = lotto.matchNumberCount(winningLotto);
            boolean bonus = winningLotto.matchBonusBall(lotto);
            ranks.add(Rank.of(count, bonus));
        }
        return new LottoResults(ranks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
