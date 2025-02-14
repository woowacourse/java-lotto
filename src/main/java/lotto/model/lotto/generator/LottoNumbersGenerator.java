package lotto.model.lotto.generator;

import static lotto.model.lotto.Lotto.LOTTO_SIZE;
import static lotto.model.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.model.lotto.LottoNumber.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            lottoNumbers.add(number);
        }
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_SIZE);
    }

}
