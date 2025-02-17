package lotto.domain;

import lotto.constant.WinningTier;
import lotto.utility.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Vendor {

    private static final int MAX_RANDOM_VALUE = 45;
    private static final int LOTTO_PRICE = 1000;
    private final RandomGenerator randomGenerator;
    private final int purchaseAmount;

    public Vendor(RandomGenerator randomGenerator, int purchaseAmount) {
        this.validatePurchaseAmount(purchaseAmount);
        this.randomGenerator = randomGenerator;
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0 || purchaseAmount <= 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d단위 입니다.", LOTTO_PRICE));
        }
    }

    public int calculateLottoCount() {
        return this.purchaseAmount / LOTTO_PRICE;
    }

    public List<Lotto> issueLottos() {
        final int LOTTO_NUMBERS = 6;
        int lottoCount = this.calculateLottoCount();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randoms = this.randomGenerator.generateNumbers(MAX_RANDOM_VALUE, LOTTO_NUMBERS);
            lottos.add(new Lotto(randoms));
        }

        return lottos;
    }

    public double calculateProfit(List<WinningTier> winningTiers) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / this.purchaseAmount;
    }
}
