package lotto.domain;

import static lotto.constants.ErrorConstants.ERROR_NULL_MESSAGE;
import static lotto.utils.LottoNumbersGenerator.generateManualLottoNumbers;
import static lotto.utils.LottoNumbersGenerator.generateRandomLottoNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private Lottos lottos;
    private final Money money;

    public LottoGame(Money money) {
        Objects.requireNonNull(money, ERROR_NULL_MESSAGE);
        this.money = money;
    }

    public void purchase(List<List<Integer>> manualNumbers) {
        Objects.requireNonNull(manualNumbers, ERROR_NULL_MESSAGE);
        purchaseLottos(manualNumbers);
    }

    private void purchaseLottos(List<List<Integer>> manualNumbers) {
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchaseManualLottos(purchasedLottos, manualNumbers);
        purchaseRandomLottos(purchasedLottos);
        this.lottos = new Lottos(purchasedLottos);
    }

    private void purchaseManualLottos(List<Lotto> origin, List<List<Integer>> manualNumbers) {
        if (manualNumbers.isEmpty()) {
            return;
        }

        for (List<Integer> manualNumber : manualNumbers) {
            origin.add(new Lotto(generateManualLottoNumbers(manualNumber)));
        }
        money.minus(new Money(manualNumbers.size() * LOTTO_PRICE));
    }

    private void purchaseRandomLottos(List<Lotto> origin) {
        int purchaseNumber = money.canBuyNumber(new Money(LOTTO_PRICE));
        for (int i = 0; i < purchaseNumber; i++) {
            origin.add(new Lotto(generateRandomLottoNumbers()));
        }
    }

    public LottoResults confirmWinnings(WinningLotto winningLotto) {
        return new LottoResults(lottos.confirmWinnings(winningLotto));
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
