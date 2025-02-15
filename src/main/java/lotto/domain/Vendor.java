package lotto.domain;

import lotto.constant.WinningTier;
import lotto.utility.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Vendor {

    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;

    public Vendor(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int calculateLottoCount() {
        return this.purchaseAmount / LOTTO_PRICE;
    }

    public List<Lotto> issueLottos() {
        int lottoCount = this.calculateLottoCount();
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randoms = randomGenerator.generateUniqueRandomNumbers(Lotto.MAX_LOTTO_NUMBER)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(randoms));
        }

        return lottos;
    }

    public double calculateProfit(List<WinningTier> winningTiers) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / this.purchaseAmount;
    }
}
