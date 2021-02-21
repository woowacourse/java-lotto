package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualTicketNumbers {

    private final List<Integer> manualTicketNumbers;

    private ManualTicketNumbers(List<Integer> manualTicketNumbers) {
        this.manualTicketNumbers = manualTicketNumbers;
    }

    public static ManualTicketNumbers from(String[] inputNumbers) {
        List<Integer> manualTicketNumbers = Arrays.stream(inputNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new ManualTicketNumbers(manualTicketNumbers);
    }

    public List<Integer> getManualTicketNumbers() {
        return Collections.unmodifiableList(manualTicketNumbers);
    }
}
