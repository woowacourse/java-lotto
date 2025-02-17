package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    public static final int LOTTO_PRIZE = 1000;

    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoShop(RandomNumbersGenerator randomNumbersGenerator) {
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public List<Lotto> buyLottos(int money) {
        List<Lotto> lottos = new ArrayList<>();

        int lottoCount = calculateLottoCount(money);
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = randomNumbersGenerator.generate();
            lottos.add(new Lotto(LottoNumber.from(numbers)));
        }

        return lottos;
    }

    public int calculateLottoCount(int money) {
        return money / LOTTO_PRIZE;
    }
}
