package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    public LottoPaper buyLotto(Money insertMoney, LottoOMRCard lottoOMRCard) {
        List<Lotto> autoLottos = new ArrayList<>();
        int totalLottoCount = insertMoney.howManyLotto() - lottoOMRCard.countOfLotto();

        for (int i = 0; i < totalLottoCount; i++) {
            autoLottos.add(new AutoLottoGenerator().makeLotto());
        }

        return lottoOMRCard.generatePaper(autoLottos);
    }
}
