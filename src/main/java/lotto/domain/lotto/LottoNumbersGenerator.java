package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersGenerator {
    private static final List<Integer> indexes = new ArrayList<>();

    static {
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
            indexes.add(i);
        }
    }

    static List<LottoNumber> generate() {
        Collections.shuffle(indexes);
        return generate(indexes.subList(0, LottoNumberGroup.LOTTO_SIZE));
    }

    static List<LottoNumber> generate(List<Integer> indexes) {
        List<LottoNumber> lottoNumbers = indexes.stream()
                .map(x -> LottoNumber.of(x))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(lottoNumbers);
    }
}
