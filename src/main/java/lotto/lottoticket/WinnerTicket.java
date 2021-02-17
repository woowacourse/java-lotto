package lotto.lottoticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.lottoticket.RandomNumbersGenerator.*;

public class WinnerTicket {
    private final List<Integer> winnerTicket;

    public WinnerTicket(String numbers){
        this.winnerTicket = splitNumbers(numbers);
    }

    private List<Integer> splitNumbers(String numbers){
        List<Integer> value = new ArrayList<>();
        for (String number : numbers.split(",")){
            number = number.replace(" ", "");
            value.add(validateNumbers(number));
        }
        validateSize(value);
        validateDuplicated(value);
        return value;
    }

    private void validateDuplicated(List<Integer> value) {
        boolean duplicated = value.stream()
                .distinct()
                .count() != value.size();

        if (duplicated) {
            throw new IllegalArgumentException("중복된 입력입니다.");
        }
    }

    private void validateSize(List<Integer> value) {
        if (value.size() != NUMBER_COUNT_IN_LOTTO) {
            throw new IllegalArgumentException("숫자는 여섯개여야 합니다.");
        }
    }

    private Integer validateNumbers(String number) {
        try {
            return validateNumberInRange(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private Integer validateNumberInRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("숫자는 1부터 45사이여야 합니다.");
        }
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinnerTicket that = (WinnerTicket) o;
        return Objects.equals(winnerTicket, that.winnerTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnerTicket);
    }
}
