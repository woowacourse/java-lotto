package Lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private static final String NEW_LINE = "\n";

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Ranks calculateMultipleRanks(WinningNumber winningNumber) {
        return new Ranks(lottos.stream()
                .map(t -> calculateSingleRank(t, winningNumber))
                .collect(Collectors.toList()));
    }

    private Rank calculateSingleRank(Lotto lotto, WinningNumber winningNumber) {
        int hitCount = winningNumber.countHit(lotto);
        boolean bonusNumberExist = winningNumber.checkBonusNumber(lotto);
        return Rank.getRank(hitCount, bonusNumberExist);
    }

    public String getLottos() {
        return lottos.stream()
                .map(Lotto::getLotto)
                .collect(Collectors.joining(NEW_LINE));
    }
}
