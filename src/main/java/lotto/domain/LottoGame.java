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
        int totalCountOfPurchase = getTotalCountOfPurchase(money);
        int countOfManualPurchase = getCountOfManualPurchase(money, manualLottos);
        int countOfAutoPurchase = totalCountOfPurchase - countOfManualPurchase;

        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(purchaseManual(manualLottos, countOfManualPurchase));
        lottos.addAll(purchaseAuto(lottoGenerator, countOfAutoPurchase));
        this.lottos = new Lottos(lottos);

        return new ResponsePurchaseResultsDto(this.lottos.get(), countOfManualPurchase, countOfAutoPurchase);
    }

    private int getTotalCountOfPurchase(Money money) {
        return money.divide(LOTTO_PRICE);
    }

    private int getCountOfManualPurchase(Money money, List<Lotto> manualLottos) {
        return Math.min(getTotalCountOfPurchase(money), manualLottos.size());
    }

    private List<Lotto> purchaseManual(List<Lotto> manualLottos, int countOfManualPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfManualPurchase; i++) {
            lottos.add(manualLottos.get(i));
        }
        return lottos;
    }

    private List<Lotto> purchaseAuto(Generator lottoGenerator, int countOfAutoPurchase) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < countOfAutoPurchase; i++) {
            lottos.add(lottoGenerator.generate());
        }
        return lottos;
    }

    public ResponseWinningResultsDto confirmWinnings(WinningNumbers winningNumbers) {
        return new ResponseWinningResultsDto(lottos.confirmWinnings(winningNumbers));
    }

    public boolean canBuyLotto(Money money) {
        return getTotalCountOfPurchase(money) > 0;
    }
}
