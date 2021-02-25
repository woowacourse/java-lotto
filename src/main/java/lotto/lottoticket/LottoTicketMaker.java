package lotto.lottoticket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketMaker {
    private static final String COMMA_DELIMITER = ",";

    public static List<LottoNumber> makeLottoNumbers(String value) {
        return Arrays.stream(value.split(COMMA_DELIMITER))
                .map(LottoTicketMaker::makeValidatedNumber)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicketMaker::makeValidatedNumbers));
    }

    private static LottoNumber makeValidatedNumber(String value) {
        return LottoNumber.valueOf(value);
    }

    private static List<LottoNumber> makeValidatedNumbers(List<LottoNumber> numbers) {
        LottoTicketValidation.validateSize(numbers);
        LottoTicketValidation.validateDuplicated(numbers);
        return numbers;
    }
}
