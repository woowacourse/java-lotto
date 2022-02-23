package domain;

import java.util.HashSet;
import java.util.Set;

import utils.RandomNumberGenerator;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    @Override
    public Set<LottoNumber> generate() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() != 6) {
            lottoNumbers.add(RandomNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
