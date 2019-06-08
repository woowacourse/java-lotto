package lotto.domain.TicketModel;


import java.util.List;
import java.util.Objects;

class Lotto implements Ticket {
    private final List<Integer> numbers;

    Lotto(LottoNumbers lottoNumbers) {
        this.numbers = lottoNumbers.numbers();
    }

    @Override
    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public List<Integer> numbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return numbers.equals(lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
