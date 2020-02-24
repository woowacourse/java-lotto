package lotto.generator;

import lotto.domain.*;

import java.util.*;

public class LottoGenerator {

    public static Lottos createLottos(PaidPrice paidPrice, LottoNumberGenerator lottoNumberGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = paidPrice.getLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(lottoNumberGenerator.create()));
        }
        return new Lottos(lottos);
    }

    public static WinningLotto createWinningLotto(LottoNumberGenerator lottoNumberGenerator, String bonusNumber) {
        Lotto lotto = new Lotto(lottoNumberGenerator.create());
        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }
}
