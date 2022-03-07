package lotto.model.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.NumberGenerator;

public class LottoTicket {

    private static final int COUNT = 6;
    public static final String LOTTO_NUMBER_NOT_DUPLICATED_MESSAGE = "로또 번호는 중복될 수 없습니다.";
    public static final String LOTTO_NUMBER_COUNT_MESSAGE = "로또 번호의 개수는 %d 이어야 합니다.";

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static LottoTicket createSortedTicket(NumberGenerator generator) {
        return new LottoTicket(
                generator.generate(COUNT)
                        .stream()
                        .map(LottoNumber::from)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    public static LottoTicket convertIntegersToLottoTicket(List<Integer> integers) {
        return new LottoTicket(integers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList()));
    }

    private void validate(List<LottoNumber> numbers) {
        validateLength(numbers);
        validateDistinct(numbers);
    }

    private void validateDistinct(List<LottoNumber> numbers) {
        if (!isDistinct(numbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_DUPLICATED_MESSAGE);
        }
    }

    private boolean isDistinct(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() == COUNT;
    }

    private void validateLength(List<LottoNumber> numbers) {
        if (numbers.size() != COUNT) {
            throw new IllegalArgumentException(String.format(LOTTO_NUMBER_COUNT_MESSAGE, COUNT));
        }
    }

    public int countMatch(LottoTicket other) {
        return Math.toIntExact(
                this.numbers.stream()
                        .filter(other::containsNumber)
                        .count()
        );
    }

    public boolean containsNumber(LottoNumber number) {
        return this.numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
