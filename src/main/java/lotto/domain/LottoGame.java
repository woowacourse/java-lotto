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
import lotto.dto.ResponsePurchaseResults;
import lotto.dto.ResponseWinningResultsDto;

public class LottoGame {

    private Lottos lottos = new Lottos(new ArrayList<>());

    public ResponsePurchaseResults purchaseManual(List<Lotto> manualLottos, Money money) {
        int countOfPurchase = getCountOfManualPurchase(money, manualLottos);

        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < countOfPurchase; i++) {
            newLottos.add(manualLottos.get(i));
        }
        addAllLottos(newLottos);

        Money changes = getChanges(money, countOfPurchase);
        return new ResponsePurchaseResults(Collections.unmodifiableList(newLottos), changes);
    }

    public ResponsePurchaseResults purchaseAuto(Generator lottoGenerator, Money money) {
        int countOfPurchase = getTotalCountOfPurchase(money);

        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < countOfPurchase; i++) {
            newLottos.add(lottoGenerator.generate());
        }
        addAllLottos(newLottos);

        Money changes = getChanges(money, countOfPurchase);
        return new ResponsePurchaseResults(Collections.unmodifiableList(newLottos), changes);
    }

    private void addAllLottos(List<Lotto> newLottos) {
        List<Lotto> ownLottos = new ArrayList<>(lottos.get());
        ownLottos.addAll(newLottos);
        lottos = new Lottos(ownLottos);
    }

    private int getTotalCountOfPurchase(Money money) {
        return money.divide(LOTTO_PRICE);
    }

    private int getCountOfManualPurchase(Money money, List<Lotto> manualLottos) {
        return Math.min(getTotalCountOfPurchase(money), manualLottos.size());
    }

    private Money getChanges(Money money, int count) {
        Money totalPrice = new Money(LOTTO_PRICE.get() * count);
        return money.subtract(totalPrice);
    }

    public ResponseWinningResultsDto confirmWinnings(WinningNumbers winningNumbers) {
        return new ResponseWinningResultsDto(lottos.confirmWinnings(winningNumbers));
    }

    public boolean canBuyLotto(Money money) {
        return getTotalCountOfPurchase(money) > 0;
    }
}
