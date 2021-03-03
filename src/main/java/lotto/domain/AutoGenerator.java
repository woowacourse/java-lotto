package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoGenerator extends LottoGenerator<Integer> {

    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private final List<Integer> numbers = new ArrayList<>();

    public AutoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            numbers.add(i);
        }
    }

    @Override
    public LottoGroup group(Integer count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generate());
        }
        return new LottoGroup(lottos);
    }

    @Override
    protected Lotto generate() {
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(0, LOTTO_NUMBER_LIMIT);
        Set<LottoNumber> lotto = lottoNumbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
        return new Lotto(lotto);
    }
}
