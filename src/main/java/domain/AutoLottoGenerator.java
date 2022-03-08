package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Lotto.LOTTO_LENGTH;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        List<LottoNumber> numbers = LottoNumber.values();
        Collections.shuffle(numbers);
        return new Lotto(numbers.stream()
                .limit(LOTTO_LENGTH)
                .collect(Collectors.toSet()));
    }
}
