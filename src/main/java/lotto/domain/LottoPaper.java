package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoPaper {
    private List<Lotto> lottos;

    LottoPaper(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Rank> matchLotto(WinningLotto winningLotto) {
        List<Rank> result = new ArrayList<>();
        lottos.forEach(lotto -> result.add(winningLotto.match(lotto)));

        return result;
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }
}
