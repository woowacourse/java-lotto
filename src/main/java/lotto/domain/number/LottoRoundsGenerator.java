package lotto.domain.number;

import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoRoundsGenerator {
    public static List<LottoRound> createLottoRounds(Money money) {
        List<LottoRound> lottoRoundList = new ArrayList<>();
        for (int i = 0; i < money.calculateRound(); i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoRoundList.add(new LottoRound(randomNumbers));
        }
        return lottoRoundList;
    }
}
