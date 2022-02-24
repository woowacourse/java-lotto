package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int LOTTO_SIZE = 6;

    @Override
    public Lotto generateLotto(List<Integer> numbers) {
        Collections.shuffle(numbers);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .sorted(Comparator.comparing(LottoNumber::number))
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
