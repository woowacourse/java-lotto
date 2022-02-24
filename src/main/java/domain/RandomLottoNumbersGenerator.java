package domain;

import java.util.Set;
import java.util.TreeSet;

import utils.RandomNumberGenerator;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    private static final int LOTTO_SIZE = 6;

    @Override
    public Set<LottoNumber> generate() {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() != LOTTO_SIZE) {
            lottoNumbers.add(RandomNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
