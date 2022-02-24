package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos purchase(int count) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }

        return new Lottos(lottos);
    }

    public List<MatchResult> match(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return this.lottos.stream()
                .map(lotto -> MatchResult.of(lotto, winningNumbers, bonusNumber))
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }
}
