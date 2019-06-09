package lotto.domain.machine;

import lotto.domain.ticket.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class ManualNumbers {
    private final List<LottoNumber> manualNumbers;

    public ManualNumbers(final List<Integer> manualNumbers) {
        this.manualNumbers = manualNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
