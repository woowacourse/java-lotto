package lotto.lottoticket;

import java.util.List;

import static lotto.lottoticket.ticketnumber.RandomNumbersGenerator.NUMBER_COUNT_IN_LOTTO;

public class LottoTicketValidation {
    public static final String ERROR_MESSAGE_DUPLICATED = "중복되는 숫자가 존재합니다.";
    public static final String ERROR_MESSAGE_INVALID_INPUT = "잘못된 입력입니다.";
    public static final String ERROR_MESSAGE_INVALID_SIZE = "숫자는 " + NUMBER_COUNT_IN_LOTTO + "개여야 합니다.";

    private LottoTicketValidation() {
    }

    public static void validateDuplicated(List<LottoNumber> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public static void validateSize(List<LottoNumber> value) {
        if (value.size() != NUMBER_COUNT_IN_LOTTO) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_SIZE);
        }
    }
}