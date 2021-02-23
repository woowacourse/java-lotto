package lotto.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static lotto.game.LottoCount.ONE_COUNT;
import static lotto.game.LottoCount.ZERO;

public class Ticket {
    public static final int PRICE = 1000;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int NUMBER_COUNT = 6;
    public static final String ERROR_MESSAGE_INVALID_SIZE = "숫자는 " + NUMBER_COUNT + "개여야 합니다.";
    public static final String ERROR_MESSAGE_DUPLICATED = "중복되는 숫자가 존재합니다.";

    private final List<Number> numbers;

    public Ticket(List<Number> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Number> values) {
        validateSize(values);
        validateDuplicated(values);
    }

    private void validateSize(List<Number> value) {
        if (value.size() != Ticket.NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_SIZE);
        }
    }

    private void validateDuplicated(List<Number> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public int sameNumberCountOne(Number number) {
        if (numbers.contains(number)) {
            return ONE_COUNT;
        }
        return ZERO;
    }

    public boolean hasContainBonus(BonusBall bonusBall) {
        return numbers.stream()
                .anyMatch(bonusBall::isSameThan);
    }

    public boolean contains(Number value) {
        return numbers.stream()
                .anyMatch(value::equals);
    }

    public int sameNumberCount(Ticket winnerTicket) {
        int matchCount = ZERO;
        for (Number number : numbers) {
            matchCount += winnerTicket.sameNumberCountOne(number);
        }
        return matchCount;
    }

    public List<Number> getTicket() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(numbers, ticket.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
