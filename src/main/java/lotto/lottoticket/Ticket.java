package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;

import java.util.List;
import java.util.Objects;

import static lotto.lottogame.LottoCount.ONE_COUNT;
import static lotto.lottogame.LottoCount.ZERO;

public class Ticket {
    private final List<Integer> numbers;

    public Ticket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<Integer> validate(List<Integer> values) {
        TicketValidation.validateSize(values);
        for (Integer number : values) {
            TicketValidation.validateNumberInRange(number);
        }
        TicketValidation.validateDuplicated(values);
        return values;
    }

    public int hasSameNumber(Integer number) {
        if(numbers.contains(number)){
            return ONE_COUNT;
        }
        return ZERO;
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

    public boolean hasContainBonus(BonusBall bonusBall) {
        for(Integer number : numbers) {
            if(bonusBall.isSameThan(number)){
                return true;
            }
        }
        return false;
    }
}
