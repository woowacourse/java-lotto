package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lotto.lottogame.LottoCount.ONE_COUNT;
import static lotto.lottogame.LottoCount.ZERO;

public class LottoTicket {
    private final List<LottoNumber> numbers;

    public LottoTicket(NumbersGenerator numbersGenerator) {
        this.numbers = validate(numbersGenerator.generate());
    }

    private List<LottoNumber> validate(List<LottoNumber> values) {
        TicketValidation.validateSize(values);
        TicketValidation.validateDuplicated(values);
        return values;
    }

    public int countWithContaining(LottoNumber number) {
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
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
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
