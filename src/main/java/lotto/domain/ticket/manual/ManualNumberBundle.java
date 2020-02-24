package lotto.domain.ticket.manual;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualNumberBundle {
    private final List<ManualNumber> manualNumbers;

    public ManualNumberBundle(List<String> numberLines) {
        this.manualNumbers = numberLines.stream()
                .map(ManualNumber::new)
                .collect(Collectors.toList());
    }

    public List<ManualNumber> getManualNumbers() {
        return Collections.unmodifiableList(manualNumbers);
    }
}
