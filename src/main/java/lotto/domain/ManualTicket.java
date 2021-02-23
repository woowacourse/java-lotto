package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualTicket {

    private final List<Integer> lottoNumbers;

    private ManualTicket(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static ManualTicket from(String[] inputNumbers) {
        List<Integer> lottoNumbers = Arrays.stream(inputNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new ManualTicket(lottoNumbers);
    }

    public List<Integer> getManualTicketNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
