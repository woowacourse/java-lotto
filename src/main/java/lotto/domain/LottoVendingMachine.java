package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {

    public LottoPaper buyLotto(Money insertMoney, LottoOMRCard lottoOMRCard) {
        List<Lotto> lottos = new ArrayList<>();
        int totalLottoCount = insertMoney.howManyLotto() - lottoOMRCard.countOfLotto();

        for (int i = 0; i < totalLottoCount; i++) {
            lottos.add(automaticLotto());
        }

        return lottoOMRCard.getPaper().addLotto(lottos);
    }

    private Lotto automaticLotto() {
        return AutoLottoGenerator.makeLotto();
    }
}
