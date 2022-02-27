package domain;

import static domain.Ticket.LOTTO_SIZE;

import java.util.Set;
import java.util.TreeSet;
import utils.RandomNumberGenerator;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    @Override
    public Set<LottoNumber> generate() {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() != LOTTO_SIZE) {
            lottoNumbers.add(RandomNumberGenerator.generate());
        }
        return lottoNumbers;
    }
}
