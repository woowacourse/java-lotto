package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    public static final int LOTTO_PRIZE = 1000;

    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoShop(RandomNumbersGenerator randomNumbersGenerator) {
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public List<Lotto> buyLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();

        int lottoCount = calculateLottoCount(money);
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = randomNumbersGenerator.generate();
            lottos.add(new Lotto(LottoNumber.from(numbers)));
        }

        return lottos;
    }

    public int calculateLottoCount(Money money) {
        return money.amount() / LOTTO_PRIZE;
    }
}
