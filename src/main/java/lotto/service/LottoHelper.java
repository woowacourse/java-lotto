package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoNosManualGenerator;
import lotto.domain.generator.LottosGenerator;

import java.util.List;

public class LottoHelper {
    private LottoHelper() {
    }

    public static WinningLotto generateWinningLotto(String winningLotto, int bonusNumber) {
        Lotto winLotto = Lotto.of(new LottoNosManualGenerator(winningLotto).generate());
        LottoNo bonusNo = LottoNo.from(bonusNumber);
        return new WinningLotto(winLotto, bonusNo);
    }

    public static WinPrize generateWinPrize(final List<Lotto> userLottos, final WinningLotto winningLotto) {
        WinPrize winPrize = new WinPrize();
        for (final Lotto userLotto : userLottos) {
            winPrize.addWinCount(winningLotto.match(userLotto));
        }
        return winPrize;
    }
}
