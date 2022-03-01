package lotto.domain;

import static lotto.domain.vo.Lotto.LOTTO_PRICE;

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

    private Lottos lottos = new Lottos(new ArrayList<>());

    public ResponsePurchaseResultsDto purchase(Money money, List<Lotto> manualLottos, Generator lottoGenerator) {
        int countOfPurchase = money.divide(LOTTO_PRICE);
        int countOfManualPurchase = Math.min(countOfPurchase, manualLottos.size());
        int countOfAuthPurchase = countOfPurchase - countOfManualPurchase;
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(purchaseManual(manualLottos, countOfManualPurchase));
        lottos.addAll(purchaseAuto(lottoGenerator, countOfAuthPurchase));
        this.lottos = new Lottos(lottos);
        return new ResponsePurchaseResultsDto(
                Collections.unmodifiableList(this.lottos.get()), countOfManualPurchase, countOfAuthPurchase);
    }

    private List<Lotto> purchaseManual(List<Lotto> manualLottos, int countOfManualPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfManualPurchase; i++) {
            lottos.add(manualLottos.get(i));
        }
        return lottos;
    }

    private List<Lotto> purchaseAuto(Generator lottoGenerator, int countOfAuthPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfAuthPurchase; i++) {
            lottos.add(lottoGenerator.generate());
        }
        return lottos;
    }

    public ResponseWinningResultsDto confirmWinnings(WinningNumbers winningNumbers) {
        return new ResponseWinningResultsDto(lottos.confirmWinnings(winningNumbers));
    }

    public boolean canBuyLotto(Money money) {
        return money.divide(LOTTO_PRICE) > 0;
    }
}
