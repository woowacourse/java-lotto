package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoGenerator {

    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBER_LIMIT = 6;
    private static final String DELIMITER = ",";
    private final List<Integer> numbers = new ArrayList<>();

    public LottoGenerator() {
        for (int i = 1; i <= MAXIMUM_NUMBER; i++) {
            numbers.add(i);
        }
    }

    public Lotto generateAuto() {
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = numbers.subList(0, LOTTO_NUMBER_LIMIT);
        Set<LottoNumber> lotto = lottoNumbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toSet());
        return new Lotto(lotto);
    }

    public Lotto generateManual(String input) {
        Set<LottoNumber> lottoNumber = new HashSet<>();
        for (String value : input.split(DELIMITER, -1)) {
            value = value.trim();
            lottoNumber.add(LottoNumber.of(value));
        }
        return new Lotto(lottoNumber);
    }
}
