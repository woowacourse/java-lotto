package converter;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;

public class StringToLottoConverter implements Converter<String, Lotto> {

    private final static String DELIMITER = ",";

    public Lotto convert(String source) {
        validate(source);
        List<LottoNumber> numbers = Arrays.stream(source.split(DELIMITER))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(LottoNumber::new)
                .toList();
        return new Lotto(numbers);
    }

    private void validate(String rawWinningNumbers) {
        String[] values = rawWinningNumbers.split(DELIMITER);
        for (String value : values) {
            validateNotStringNumber(value);
        }
    }

    private void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
