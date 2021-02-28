package lotto.util;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManualNumberGenerator implements LottoNumberGenerator {

    private static final String DELIMITER = ",";
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "로또 번호는 %d개 모두 숫자여야합니다.";

    private final List<LottoNumber> lottoNumbers;

    public ManualNumberGenerator(final String input) {
        Objects.requireNonNull(input, LottoTicket.NULL_ERROR_MESSAGE);
        requireNonEmpty(input, LottoTicket.EMPTY_ERROR_MESSAGE);
        this.lottoNumbers = Arrays.stream(input.split(DELIMITER))
                .map(this::parseLottoNumber)
                .sorted()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private void requireNonEmpty(String input, String message) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public List<LottoNumber> generateNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    private LottoNumber parseLottoNumber(String inputString) {
        return new LottoNumber(parseInt(inputString));
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(NUMBER_FORMAT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
        }
    }
}
