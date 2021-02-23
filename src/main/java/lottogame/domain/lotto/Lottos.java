package lottogame.domain.lotto;

import lottogame.domain.dto.LottoDto;
import lottogame.domain.statistic.LottoResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> values) {
        lottos.addAll(new ArrayList<>(values));
    }

    public List<LottoDto> numbersOfLottos() {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.values()))
                .collect(Collectors.toList());
    }

    public List<LottoResult> matchesLottos(WinningLotto winningLotto) {
        List<LottoResult> lottoResults = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int count = lotto.matchNumberCount(winningLotto);
            boolean bonus = winningLotto.matchBonusBall(lotto);
            lottoResults.add(new LottoResult(count, bonus));
        }
        return lottoResults;
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
