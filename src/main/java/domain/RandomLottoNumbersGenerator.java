package domain;

import static domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static domain.LottoNumber.MINIMUM_LOTTO_NUMBER;
import static domain.Ticket.LOTTO_SIZE;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Set<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);
        return new TreeSet<>(lottoNumbers.subList(0, LOTTO_SIZE));
    }
}
