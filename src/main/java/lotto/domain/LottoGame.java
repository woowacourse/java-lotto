package lotto.domain;

import static lotto.constants.ErrorConstants.ERROR_NULL_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.utils.LottoGenerateStrategy;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private Lottos lottos;
    private final Money money;

    public LottoGame(Money money) {
        Objects.requireNonNull(money, ERROR_NULL_MESSAGE);
        this.money = money;
    }

    public void purchase(List<List<Integer>> manualNumbers, LottoGenerateStrategy lottoGenerateStrategy) {
        Objects.requireNonNull(manualNumbers, ERROR_NULL_MESSAGE);
        List<Lotto> purchasedLottos = new ArrayList<>();
        purchaseManualLottos(purchasedLottos, manualNumbers);
        purchaseRandomLottos(purchasedLottos, lottoGenerateStrategy);
        this.lottos = new Lottos(purchasedLottos);
    }

    private void purchaseManualLottos(List<Lotto> origin, List<List<Integer>> manualNumbers) {
        if (manualNumbers.isEmpty()) {
            return;
        }

        for (List<Integer> manualNumber : manualNumbers) {
            origin.add(generateManualLotto(manualNumber));
        }
        money.minus(new Money(manualNumbers.size() * LOTTO_PRICE));
    }

    private Lotto generateManualLotto(List<Integer> manualNumber) {
        return new Lotto(manualNumber.stream()
                .map(LottoNumber::valueOf)
                .sorted()
                .collect(Collectors.toList()));
    }

    private void purchaseRandomLottos(List<Lotto> origin, LottoGenerateStrategy lottoGenerateStrategy) {
        int purchaseNumber = money.canBuyNumber(new Money(LOTTO_PRICE));
        for (int i = 0; i < purchaseNumber; i++) {
            origin.add(lottoGenerateStrategy.generate());
        }
    }

    public LottoResults confirmWinnings(WinningLotto winningLotto) {
        return new LottoResults(lottos.confirmWinnings(winningLotto));
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
