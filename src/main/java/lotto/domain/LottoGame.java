package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.generator.Generator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseResultsDto;
import lotto.dto.ResponseWinningResultsDto;

public class LottoGame {

    public static final Money LOTTO_PRICE = new Money(1000);

    private Lottos lottos = new Lottos(new ArrayList<>());

    public ResponsePurchaseResultsDto purchase(Money money,
                                               List<Lotto> manualLottos,
                                               Generator lottoGenerator) {
        int countOfPurchase = money.canBuyNumber(LOTTO_PRICE);
        int countOfManualPurchase = Math.min(countOfPurchase, manualLottos.size());
        int countOfAuthPurchase = countOfPurchase - countOfManualPurchase;
        List<Lotto> lottos = new ArrayList<>();
        purchaseManual(manualLottos, countOfManualPurchase, lottos);
        purchaseAuto(lottoGenerator, countOfAuthPurchase, lottos);
        this.lottos = new Lottos(lottos);
        return new ResponsePurchaseResultsDto(
                Collections.unmodifiableList(this.lottos.getLottos()), countOfManualPurchase, countOfAuthPurchase);
    }

    private void purchaseManual(List<Lotto> manualLottos, int countOfManualPurchase, List<Lotto> lottos) {
        for (int i = 0; i < countOfManualPurchase; i++) {
            lottos.add(manualLottos.get(i));
        }
    }

    private void purchaseAuto(Generator lottoGenerator, int countOfAuthPurchase, List<Lotto> lottos) {
        for (int i = 0; i < countOfAuthPurchase; i++) {
            lottos.add(lottoGenerator.generate());
        }
    }

    public ResponseWinningResultsDto confirmWinnings(WinningNumbers winningNumbers) {
        return new ResponseWinningResultsDto(lottos.confirmWinnings(winningNumbers));
    }

    public boolean canBuyLotto(Money money) {
        return money.canBuyNumber(LOTTO_PRICE) > 0;
    }
}
