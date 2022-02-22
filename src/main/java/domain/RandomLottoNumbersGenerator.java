package domain;

import java.util.ArrayList;
import java.util.List;

import utils.RandomNumberGenerator;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(RandomNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
