package lotto.utils;

import java.util.Set;
import java.util.TreeSet;

import lotto.domain.LottoNumber;

public class BasicLottoNumberGenerator implements LottoNumbersGenerator {
    @Override
    public Set<LottoNumber> generate(int size) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        for (int i = 1; i < size + 1; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return lottoNumbers;
    }
}
