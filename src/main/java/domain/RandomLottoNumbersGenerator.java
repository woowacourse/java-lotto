package domain;

import static domain.Ticket.LOTTO_SIZE;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private final List<LottoNumber> lottoNumbers;

    public RandomLottoNumbersGenerator() {
        lottoNumbers = IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public Set<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);
        return new TreeSet<>(lottoNumbers.subList(0, LOTTO_SIZE));
    }
}
