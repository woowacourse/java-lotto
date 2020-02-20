package lotto.utils;

import lotto.domain.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    public List<LottoNumber> generateNumbers() {
        long seed = System.nanoTime();

        List<LottoNumber> randomNumbers = LottoNumberFactory.createLottoNumberList();
        Collections.shuffle(randomNumbers, new Random(seed));
        return randomNumbers.subList(0,6);
    }

    @Override
    public List<LottoNumber> generateNumbers(String Input) {
        return null;
    }
}
