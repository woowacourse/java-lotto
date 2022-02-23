package domain;

import java.util.Set;
import java.util.TreeSet;

import utils.RandomNumberGenerator;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    @Override
    public Set<LottoNumber> generate() {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() != 6) {
            lottoNumbers.add(RandomNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
