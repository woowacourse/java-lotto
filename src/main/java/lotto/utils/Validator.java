package lotto.utils;

import lotto.exception.IllegalLottoTicketSizeError;

import java.util.List;

public class Validator {
    public static final int LOTTO_TICKET_SIZE = 6;

    private Validator() {

    }

    public static void validateLottoNumbersSize(List<Integer> numbers) {
        if (numbers.size() < LOTTO_TICKET_SIZE) {
            throw new IllegalLottoTicketSizeError();
        }
    }
}
