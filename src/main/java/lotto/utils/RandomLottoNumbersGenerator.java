package lotto.utils;

import java.util.Set;
import java.util.TreeSet;

import lotto.domain.LottoNumber;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {

    @Override
    public Set<LottoNumber> generate(int size) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() != size) {
            lottoNumbers.add(RandomLottoNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
