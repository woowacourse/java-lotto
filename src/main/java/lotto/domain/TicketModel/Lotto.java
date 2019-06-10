package lotto.domain.TicketModel;


import java.util.Objects;

class Lotto implements Ticket {
    private static final int LOTTO_PRICE = 1000;
    private final LottoNumbers numbers;

    Lotto(LottoNumbers lottoNumbers) {
        this.numbers = lottoNumbers;
    }

    @Override
    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public LottoNumbers numbers() {
        return numbers;
    }

    @Override
    public int unitPrice() {
        return LOTTO_PRICE;
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
