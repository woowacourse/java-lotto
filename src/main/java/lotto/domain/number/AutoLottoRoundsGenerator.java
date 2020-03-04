package lotto.domain.number;

import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.Money;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoRoundsGenerator implements LottoRoundsGenerable {

    @Override
    public LottoRounds generate(Money money) {
        List<LottoRound> autoLottos = new ArrayList<>();
        while (money.isSubtractable(LottoRoundsGenerator.LOTTO_PRICE)) {
            money.subtract(LottoRoundsGenerator.LOTTO_PRICE);
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            autoLottos.add(new LottoRound(randomNumbers));
        }
        return new LottoRounds(autoLottos);
    }
}
