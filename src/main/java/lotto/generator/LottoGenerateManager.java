package lotto.generator;

import lotto.domain.*;

import java.util.*;

public class LottoGenerateManager {

    public static Lottos createLottos(PaidPrice paidPrice, LottoGenerator lottoGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = paidPrice.getLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoGenerator.create());
        }
        return new Lottos(lottos);
    }

    public static WinningLotto createWinningLotto(LottoGenerator lottoGenerator, String bonusNumber) {
        Lotto lotto = lottoGenerator.create();
        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }
}
