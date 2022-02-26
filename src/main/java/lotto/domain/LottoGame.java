package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.generator.Generator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;

public class LottoGame {

    private static final Money LOTTO_PRICE = new Money(1000);

    private Lottos lottos = new Lottos(new ArrayList<>());

    public void purchase(Money money, Generator lottoGenerator) {
        int countOfPurchase = money.canBuyNumber(LOTTO_PRICE);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfPurchase; i++) {
            lottos.add(lottoGenerator.generate());
        }
        this.lottos = new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos.getLottos());
    }

    public LottoResults confirmWinnings(WinningNumbers winningNumbers) {
        return new LottoResults(lottos.confirmWinnings(winningNumbers), LOTTO_PRICE);
    }

    public boolean hasLottoTickets() {
        return !lottos.getLottos().isEmpty();
    }
}
