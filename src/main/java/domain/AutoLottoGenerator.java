package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.Lotto.LOTTO_LENGTH;
import static domain.LottoNumber.MAX_LOTTO_NUMBER;
import static domain.LottoNumber.MIN_LOTTO_NUMBER;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        List<LottoNumber> numbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return new Lotto(numbers.stream()
                .limit(LOTTO_LENGTH)
                .collect(Collectors.toSet()));
    }
}
