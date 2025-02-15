package lotto.service;

import lotto.domain.Lotto;
import lotto.utility.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

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
}
