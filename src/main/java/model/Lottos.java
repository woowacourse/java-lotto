package model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Map<Prize, Integer> getResult(WinningLotto winningLotto) {
        Map<Prize, Integer> result = new TreeMap<>();
        Arrays.stream(Prize.values()).forEach(prize -> result.put(prize, 0));
        lottos.forEach(
                lotto -> lotto
                        .calculatePrize(winningLotto)
                        .ifPresent(prize -> result.put(prize, result.get(prize) + 1))
        );
        return result;
    }

}
