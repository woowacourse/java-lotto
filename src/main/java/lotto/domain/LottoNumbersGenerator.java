package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoNumbersGenerator {
    private static Random random = new Random();

    static List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoNumberConfig.SIZE; i++) {
            int lottoNumber = random.nextInt(LottoNumberConfig.MAX - LottoNumberConfig.MIN) + LottoNumberConfig.MIN;
            lottoNumbers.add(lottoNumber);
        }
        return lottoNumbers;
    }
}
