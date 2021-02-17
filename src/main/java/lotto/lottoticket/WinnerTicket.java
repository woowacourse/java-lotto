package lotto.lottoticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return value;
    }

    private Integer validateNumbers(String number) {
        try {
            return Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
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
