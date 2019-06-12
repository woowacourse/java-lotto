package lotto.util;

import lotto.LottoDto;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
    private static final String DELIMITER = ",";

    public Lotto parseLotto(final String input) {
        LottoFactory lottoFactory = new LottoFactory();
        return lottoFactory.create(splitWithComma(input));
    }

    public LottoDto parseLottoDto(final String input) {
        Lotto lotto = parseLotto(input);
        return LottoDto.of(lotto);
    }

    public LottoNumber parseLottoNumber(final int lottoNumber) {
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
