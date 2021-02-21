package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;

import java.util.List;
import java.util.Objects;

import static lotto.lottogame.LottoCount.ONE_COUNT;
import static lotto.lottogame.LottoCount.ZERO;

public class LottoTicket {
    private final List<Integer> numbers;

    public LottoTicket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        TicketValidation.validateSize(values);
        TicketValidation.validateDuplicated(values);
        for (Integer number : values) {
            TicketValidation.validateNumberInRange(number);
        }
        return values;
    }

    public int countWithContaining(Integer number) {
        if (numbers.contains(number)) {
            return ONE_COUNT;
        }
        return ZERO;
    }

    public boolean containsBonusBall(BonusBall bonusBall) {
        return numbers.stream()
                .anyMatch(bonusBall::isSameThan);
    }

    public List<Integer> getTicket() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket lottoTicket = (LottoTicket) o;
        return Objects.equals(numbers, lottoTicket.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
