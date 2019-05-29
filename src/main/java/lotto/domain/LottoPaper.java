package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoPaper {
    private static final String NEW_LINE = "\n";

    private List<Lotto> lottos;

    LottoPaper(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Rank> matchLotto(WinningLotto winningLotto) {
        List<Rank> result = new ArrayList<>();
        lottos.forEach(lotto -> result.add(winningLotto.match(lotto)));
        return result;
    }

    int countOfLotto() {
        return lottos.size();
    }

    LottoPaper addLotto(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
        return this;
    }

    @Override
    public String toString() {
        return lottos.stream().map(Lotto::toString).collect(Collectors.joining(NEW_LINE));
    }
}
