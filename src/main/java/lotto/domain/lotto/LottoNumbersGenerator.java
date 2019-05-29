package lotto.domain.lotto;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

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

    static List<LottoNumber> create() {
        Collections.shuffle(indexes);
        return create(indexes.subList(0, LottoTicket.LOTTO_SIZE - 1));
    }

    static List<LottoNumber> create(List<Integer> indexes) {
        List<LottoNumber> lottoNumbers = indexes.stream()
                .map(x -> LottoNumber.of(x))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(lottoNumbers);
    }
}
