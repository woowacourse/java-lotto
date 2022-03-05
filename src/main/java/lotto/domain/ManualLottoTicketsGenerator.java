package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoTicketsGenerator {

    public static List<LottoTicket> createLottoNumbers(List<List<Integer>> inputLottoNumbers) {
        return inputLottoNumbers.stream()
                .map(number -> new LottoTicket(toLottoNumbers(number)))
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> number) {
        return number.stream()
                .map(LottoNumber::valueOf)
                .sorted(LottoNumber::compareTo)
                .collect(Collectors.toList());
    }
}
