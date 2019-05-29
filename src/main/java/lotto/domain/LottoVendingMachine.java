package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {

    public LottoPaper buyLotto(Money insertMoney, LottoPaper userLottoPaper) {
        List<Lotto> lottos = new ArrayList<>();
        int totalLottoCount = insertMoney.howManyLotto() - userLottoPaper.countOfLotto();

        for (int i = 0; i < totalLottoCount; i++) {
            lottos.add(automaticLotto());
        }

        return userLottoPaper.addLotto(lottos);
    }

    private Lotto automaticLotto() {
        return AutoLottoGenerator.makeLotto();
    }
}
