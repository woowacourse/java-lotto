package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();
    private RandomNumber randomNumber;

    public Lottos(int lottoCounts, RandomNumber randomNumber) {
        this.randomNumber = randomNumber;
        generateLottos(lottoCounts);
    }

    private void generateLottos(int lottoCounts) {
        for (int i = 0; i < lottoCounts; i ++) {
            lottos.add(new Lotto(randomNumber));
        }
    }

    public Prizes calculatePrize(WinningLotto winningLotto) {
        Lotto winningLottoNumber = winningLotto.getLotto();
        int bonusNumber = winningLotto.getBonusNumber();

        return matchNumber(winningLottoNumber, bonusNumber);

    }
    private Prizes matchNumber(Lotto winningLottoNumber, int bonusNumber) {
        List<Prize> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.match(winningLottoNumber);
            result.add(new Prize(matchCount, lotto.checkBonusNumberMatch(bonusNumber)));
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
