package domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {
    private static final String DELIMITER = ", ";

    public static LottoTicket createLottoTicket(String input) {
        validateNullOrBlank(input);

        List<LottoNumber> lottoTicket = new ArrayList<>();

        for (String inputNumber : splitInputNumber(input)) {
            lottoTicket.add(LottoNumber.from(inputNumber));
        }

        return new LottoTicket(lottoTicket);
    }

    private static String[] splitInputNumber(String input) {
        return input.split(DELIMITER);
    }

    private static void validateNullOrBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(String.format("input값이 공백 또는 null입니다. 현재 input값은 %s 입니다.", input));
        }
    }
}
