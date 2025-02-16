package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.utility.RandomGenerator;

public class LottoMachine {

    private final RandomGenerator randomGenerator;

    public LottoMachine(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public List<Lotto> purchaseLotto(int purchaseAmount) {
        int purchaseCount = calculatePurchaseCount(purchaseAmount);
        return issueLottos(purchaseCount);
    }

    public List<WinningTier> findWinningTiers(List<Lotto> lottos, WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::findWinningTier)
                .filter(tier -> tier != WinningTier.EMPTY)
                .toList();
    }

    public double calculateProfit(List<WinningTier> winningTiers, int purchaseAmount) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / purchaseAmount;
    }

    private int calculatePurchaseCount(int purchaseAmount) {
        return purchaseAmount / Lotto.LOTTO_PRICE;
    }

    private List<Lotto> issueLottos(int count) {
        return IntStream.range(0, count)
                .mapToObj(index -> issueLotto())
                .toList();
    }

    private Lotto issueLotto() {
        List<Integer> randoms = randomGenerator.generateUniqueRandomNumbers(
                        Lotto.MAX_LOTTO_NUMBER, Lotto.LOTTO_NUMBER_COUNT)
                .stream()
                .sorted()
                .toList();
        return new Lotto(randoms);
    }
}
