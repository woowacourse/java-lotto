package model;

import java.util.ArrayList;
import java.util.List;

public record LottoTicket(
        List<Integer> numbers
) {

    @Override
    public List<Integer> numbers() {
        return new ArrayList<>(numbers);
    }
}
