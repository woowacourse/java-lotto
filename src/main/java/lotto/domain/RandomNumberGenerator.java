package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    private final List<LottoNumber> lottoNumberPool = new LinkedList<>();

    public RandomNumberGenerator() {
        LottoNumber.getLottoNumberMapper().forEach((integer, lottoNumber) ->
                lottoNumberPool.add(lottoNumber));
        Collections.shuffle(lottoNumberPool);
    }

    public LottoNumber generate() {
        return lottoNumberPool.remove(0);
    }

    public List<LottoNumber> generateNumbers() {
        List<LottoNumber> randomNumbers = new ArrayList<>();
        for (int i = 0; i < LottoNumbers.LOTTO_NUMBER_AMOUNT; i++) {
            randomNumbers.add(generate());
        }
        return randomNumbers;
    }
}
