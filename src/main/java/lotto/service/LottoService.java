package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.constant.WinningTier;
import lotto.utility.RandomGenerator;

public class LottoService {

    public int purchaseLotto(int purchaseAmount) {
        return purchaseAmount / Lotto.LOTTO_PRICE;
    }

    public List<Lotto> issueLottos(int count) {
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> randoms = randomGenerator.generateUniqueRandomNumbers(Lotto.MAX_LOTTO_NUMBER)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(randoms));
        }

        return lottos;
    }

    public List<WinningTier> findWinningTiers(List<Lotto> lottos, WinningLotto winningLotto) {
        List<WinningTier> winningTiers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            WinningTier tier = winningLotto.findWinningTier(lotto);
            winningTiers.add(tier);
        }
        return winningTiers;
    }

    public double calculateProfit(List<WinningTier> winningTiers, int purchaseAmount) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / purchaseAmount;
    }
}
