package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    WinningRanks produceWinningRanks(Lotto winningLotto, LottoNumber bonusNumber) {
        WinningRanks winningRanks = new WinningRanks(new HashMap<>());
        for (Lotto lotto : lottos) {
            int matchingNumber = lotto.matchWinningNumbers(winningLotto);
            addWinningRank(matchingNumber, lotto.matchBonusNumber(bonusNumber), winningRanks);
        }
        return winningRanks;
    }

    private void addWinningRank(int matchingNumber, boolean matchBonusNumber, WinningRanks winningRanks) {
        if (Rank.isValid(matchingNumber)) {
            Rank rank = Rank.valueOf(matchingNumber, matchBonusNumber);
            winningRanks.addWinningRanks(rank);
        }
    }

    Lottos concat(Lottos lottosAutomatic) {
        return new Lottos(Stream.concat(this.lottos.stream(), lottosAutomatic.lottos.stream()).collect(Collectors.toList()));
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
