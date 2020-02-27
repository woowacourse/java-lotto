package lotto.domain.number;

import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoRoundsGenerator {
    public static List<LottoRound> createLottoRounds(Money money, List<LottoRound> manualLottoRounds) {
        List<LottoRound> lottoRounds = new ArrayList<>(manualLottoRounds);
        int autoLottoSize = money.calculateRound() - manualLottoRounds.size();
        for (int i = 0; i < autoLottoSize; i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoRounds.add(new LottoRound(randomNumbers));
        }
        return lottoRounds;
    }
}
