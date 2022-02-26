package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class LottoGenerator implements Generator {

    private static final List<LottoNumber> INIT_LOTTO_NUMBERS = IntStream
            .rangeClosed(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toUnmodifiableList());

    public LottoGenerator() {
    }

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = new ArrayList<>(INIT_LOTTO_NUMBERS);
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = numbers.subList(0, Lotto.LOTTO_SIZE);
        Collections.sort(lottoNumbers);

        return new Lotto(lottoNumbers);
    }
}
