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
        validateManualNumbersLength(manualNumbers);
        return new ManualNumbers(manualNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    private static void validateManualNumbersLength(List<Integer> manualNumbers) {
        if (manualNumbers.size() != 6) {
            throw new IllegalArgumentException("수동 번호의 갯수가 맞지 않습니다.");
        }
    }

    public List<LottoNumber> getManualNumbers() {
        return manualNumbers;
    }
}
