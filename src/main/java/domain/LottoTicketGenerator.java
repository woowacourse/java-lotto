package domain;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketGenerator {
    private static final String DELIMITER = ", ";

    public static LottoTicket createLottoTicket(String input) {
        List<LottoNumber> lottoTicket = new ArrayList<>();
        for (String number : splitInputNumber(input)) {
            validateBlank(number);
            lottoTicket.add(LottoNumber.getLottoNumber(validateNumber(number)));
        }
        return new LottoTicket(lottoTicket);
    }

    private static String[] splitInputNumber(String input) {
        return input.split(DELIMITER);
    }

    private static void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input값이 공백 또는 null입니다.");
        }
    }

    private static int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input값이 숫자가 아닙니다.");
        }
    }
}
