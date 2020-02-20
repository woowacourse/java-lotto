package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private static RandomGenerator randomGenerator = new RandomGenerator();
    public static List<Lotto> createLotties(Payment payment) {
        List<Lotto> lotties = new ArrayList<>();

        for (int i = 0; i < payment.getLottoCount(); i++) {
            lotties.add(new Lotto(createLottoNumbers()));
        }
        return lotties;
    }

    public static List<LottoNumber> createLottoNumbers() {
        List<Integer> randomNumbers = randomGenerator.getRandomNumbers();
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(new LottoNumber(randomNumbers.get(i)));
        }
        return lottoNumbers;
    }
}
