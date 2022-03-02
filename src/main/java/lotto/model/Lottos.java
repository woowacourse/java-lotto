package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.prize.MatchResult;

/*
 * 구입한 로또들을 담는 일급 컬렉션 Class
 */
public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<MatchResult> match(WinningLotto winningLotto) {
        return this.lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public int getAutoCount() {
        return (int) this.lottos.stream()
                .filter(Lotto::isAuto)
                .count();
    }

    public int getManualCount() {
        return (int) this.lottos.stream()
                .filter(lotto -> !lotto.isAuto())
                .count();
    }
}
