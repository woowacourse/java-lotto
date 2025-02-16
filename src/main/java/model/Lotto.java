package model;

import static constant.message.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.message.ExceptionMessage.INVALID_LOTTO_RANGE;
import static constant.message.ExceptionMessage.INVALID_LOTTO_SIZE;
import static constant.message.ExceptionMessage.INVALID_LOTTO_FORMAT;
import static constant.message.ExceptionMessage.DUPLICATE_LOTTO_NUMBER;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;
import static constant.LottoConstant.LOTTO_SEPARATOR;
import static constant.LottoConstant.LOTTO_TICKET_SIZE;

import java.util.List;
import java.util.Objects;
import util.Parser;
import util.Validator;

public class Lotto {

    private final List<Integer> numbers;

    public static Lotto of(final String input) {
        Validator.validateNullOrBlank(input, INVALID_INPUT_NULL_OR_BLANK.getMessage());
        List<Integer> numbers = Parser.separateBySeparator(input, LOTTO_SEPARATOR).stream()
                .peek(string -> Validator.validateInteger(string, INVALID_LOTTO_FORMAT.getMessage()))
                .map(Parser::convertStringToInteger)
                .toList();

        return new Lotto(numbers);
    }

    private Lotto(final List<Integer> numbers) {
        Validator.validateSize(numbers, LOTTO_TICKET_SIZE, INVALID_LOTTO_SIZE.getMessage(LOTTO_TICKET_SIZE));
        numbers.forEach(number -> Validator.validateRange(number, LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE,
                INVALID_LOTTO_RANGE.getMessage(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE)));
        Validator.validateDuplicate(numbers, DUPLICATE_LOTTO_NUMBER.getMessage());

        this.numbers = numbers;
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int matchCount(final Lotto issuedTicket) {
        return (int) issuedTicket.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public String toString() {
        return numbers.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Lotto other)) {
            return false;
        }
        return Objects.equals(numbers, other.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
