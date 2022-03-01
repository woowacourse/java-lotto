package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.generator.Generator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseDto;

public class LottoGame {

    private static final Money LOTTO_PRICE = new Money(1000);

    private Lottos lottos = new Lottos(new ArrayList<>());

    public ResponsePurchaseDto purchase(Money money,
                                        List<List<LottoNumber>> manualLottoNumbers,
                                        Generator lottoGenerator) {
        int countOfPurchase = money.canBuyNumber(LOTTO_PRICE);
        int countOfManualPurchase = Math.min(countOfPurchase, manualLottoNumbers.size());
        int countOfAuthPurchase = countOfPurchase - countOfManualPurchase;
        List<Lotto> lottos = new ArrayList<>();
        purchaseManual(manualLottoNumbers, countOfManualPurchase, lottos);
        purchaseAuto(lottoGenerator, countOfAuthPurchase, lottos);
        this.lottos = new Lottos(lottos);
        return new ResponsePurchaseDto(
                Collections.unmodifiableList(this.lottos.getLottos()), countOfManualPurchase, countOfAuthPurchase);
    }

    private void purchaseManual(List<List<LottoNumber>> manualLottoNumbers, int countOfManualPurchase,
                                List<Lotto> lottos) {
        for (int i = 0; i < countOfManualPurchase; i++) {
            lottos.add(new Lotto(manualLottoNumbers.get(i)));
        }
    }

    private void purchaseAuto(Generator lottoGenerator, int countOfAuthPurchase, List<Lotto> lottos) {
        for (int i = 0; i < countOfAuthPurchase; i++) {
            lottos.add(lottoGenerator.generate());
        }
    }

    public LottoResults confirmWinnings(WinningNumbers winningNumbers) {
        return new LottoResults(lottos.confirmWinnings(winningNumbers), LOTTO_PRICE);
    }

    public boolean canBuyLotto(Money money) {
        return money.canBuyNumber(LOTTO_PRICE) > 0;
    }
}
