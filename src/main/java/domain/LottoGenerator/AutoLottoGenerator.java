package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int LOTTO_SIZE = 6;
    private static final String MANUAL_LOTTO_GENERATE_LIMIT = "자동 로또 생성기에서는 수동 로또 생성을 할 수 없습니다.";

    @Override
    public Lotto generateLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> collect = lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

        return new Lotto(collect);
    }

    @Override
    public Lotto generateLotto(List<Integer> numbers) {
        throw new IllegalArgumentException(MANUAL_LOTTO_GENERATE_LIMIT);
    }
}
