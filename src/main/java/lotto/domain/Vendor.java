package lotto.domain;

import lotto.constant.WinningTier;
import lotto.utility.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class Vendor {

    private static final int MAX_RANDOM_VALUE = 45;
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBERS = 6;
    private final LottoNumberGenerator randomGenerator;
    private final int purchaseAmount;

    public Vendor(LottoNumberGenerator randomGenerator, int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.randomGenerator = randomGenerator;
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0 || purchaseAmount <= 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d단위 입니다.", LOTTO_PRICE));
        }
    }

    private int calculateLottoCount() {
        return this.purchaseAmount / LOTTO_PRICE;
    }

    public Lottos issueLottos() {
        int lottoCount = calculateLottoCount();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randoms = this.randomGenerator.generateNumbers(MAX_RANDOM_VALUE, LOTTO_NUMBERS);
            lottos.add(new Lotto(randoms));
        }

        return new Lottos(lottos);
    }

    public double calculateProfit(List<WinningTier> winningTiers) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / this.purchaseAmount;
    }
}
