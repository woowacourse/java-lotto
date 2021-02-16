package lotto.utils;

import lotto.exception.IllegalLottoTicketDuplicateNumber;
import lotto.exception.IllegalLottoTicketSizeError;

import java.util.HashSet;
import java.util.List;

public class Validator {
    public static final int LOTTO_TICKET_SIZE = 6;

    private Validator() {

    }

    public static void validateLottoNumbers(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicateNumber(numbers);
    }

    private static void validateDuplicateNumber(List<Integer> numbers) {
        if(new HashSet<>(numbers).size() != numbers.size()){
            throw new IllegalLottoTicketDuplicateNumber();
        }
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() < LOTTO_TICKET_SIZE) {
            throw new IllegalLottoTicketSizeError();
        }
    }
}
