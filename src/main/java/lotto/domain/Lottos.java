package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Prizes calculatePrize(WinningLotto winningLotto) {
        Lotto winningLottoNumber = winningLotto.getLotto();
        int bonusNumber = winningLotto.getBonusNumber();
        return matchNumber(winningLottoNumber, bonusNumber);
    }

    private Prizes matchNumber(Lotto winningLottoNumber, int bonusNumber) {
        List<Prize> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCount(winningLottoNumber);
            result.add(new Prize(matchCount, lotto.containsNumber(bonusNumber)));
        }
        return new Prizes(result);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.toString());
        }
        return stringBuilder.toString();
    }
}
