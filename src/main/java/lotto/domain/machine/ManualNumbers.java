package lotto.domain.machine;

import lotto.domain.ticket.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class ManualNumbers {
    private final List<LottoNumber> manualNumbers;

    public ManualNumbers(final List<LottoNumber> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }

    public static ManualNumbers of(final List<Integer> manualNumbers) {
        return new ManualNumbers(manualNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    public List<LottoNumber> getManualNumbers() {
        return manualNumbers;
    }
}
