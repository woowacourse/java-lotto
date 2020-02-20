package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PaidPrice;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static RandomGenerator randomGenerator = new RandomGenerator();

    public static List<Lotto> createLottos(PaidPrice paidPrice) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < paidPrice.getLottoCount(); i++) {
            lottos.add(new Lotto(createLottoNumbers()));
        }
        return lottos;
    }

    public static List<LottoNumber> createLottoNumbers() {
        List<Integer> randomNumbers = randomGenerator.getRandomNumbers();
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(randomNumbers.get(i)));
        }
        return lottoNumbers;
    }
}
