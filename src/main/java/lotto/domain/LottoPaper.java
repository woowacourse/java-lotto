package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoPaper {
    private List<Lotto> lottos;

    public LottoPaper(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Lotto, Rank> matchLotto(WinningLotto winningLotto){
        Map<Lotto, Rank> result = new HashMap<>();
        lottos.forEach(lotto -> result.put(lotto, winningLotto.match(lotto)));

        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        return lottos.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }
}
