package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DELIMITER = ",";

    public Lotto makeLotto(final String input) {
        LottoFactory lottoFactory = new LottoFactory();
        return lottoFactory.create(splitWithComma(input));
    }

    public LottoNumber makeLottoNumber(final int lottoNumber) {
        return LottoNumber.of(lottoNumber);
    }

    private List<Integer> splitWithComma(final String input) {
        List<String> strings = Arrays.asList(input.split(DELIMITER));
        return strings.stream()
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
    }
}
