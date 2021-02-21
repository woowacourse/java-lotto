package domain.ticket;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public abstract class Ticket {
    public static final int LOTTO_TICKET_SIZE = 6;

    protected final List<LottoNumber> lottoNumbers;

    public Ticket() {
        this.lottoNumbers = new ArrayList<>();
    }

    protected void validate(final List<Integer> numbers) {
        validateIncorrectSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    protected void validateDuplicateNumbers(final List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("중복 숫자가 존재합니다.");
        }
    }

    protected void validateIncorrectSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(
                    String.format("로또 숫자의 개수가 %d이 아닙니다: %d", LOTTO_TICKET_SIZE, numbers.size()));
        }
    }

    protected abstract List<Integer> generateNumbers(List<Integer> selectedNumbers);

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Ticket ticket = (Ticket) o;
        return Objects.equals(lottoNumbers, ticket.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
