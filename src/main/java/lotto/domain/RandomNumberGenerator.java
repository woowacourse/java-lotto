package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    private final List<LottoNumberGroup> lottoNumberPool = new LinkedList<>();

    public RandomNumberGenerator() {
        for (LottoNumberGroup lottoNumber : LottoNumberGroup.values()) {
            lottoNumberPool.add(lottoNumber);
        }
        Collections.shuffle(lottoNumberPool);
    }

    public LottoNumberGroup generate() {
        return lottoNumberPool.remove(0);
    }

    public List<LottoNumberGroup> generateNumbers() {
        List<LottoNumberGroup> randomNumbers = new ArrayList<>();
        for (int i = 0; i < LottoNumbers.LOTTO_NUMBER_AMOUNT; i++) {
            randomNumbers.add(generate());
        }
        return randomNumbers;
    }
}
