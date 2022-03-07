package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> value = new ArrayList<>();

    public LottoTickets(List<List<Integer>> manualLottoNumberInput, int autoCount) {
        addManualTickets(manualLottoNumberInput);
        addAutoLottoTickets(autoCount);
    }

    private void addManualTickets(List<List<Integer>> manualLottoNumberInput) {
        manualLottoNumberInput.stream()
            .map(LottoTicket::of)
            .collect(Collectors.toCollection(() -> value));
    }

    private void addAutoLottoTickets(int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            value.add(LottoTicket.ofAuto());
        }
    }

    public List<LottoTicket> get() {
        return Collections.unmodifiableList(value);
    }

}
