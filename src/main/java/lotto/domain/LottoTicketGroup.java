package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketGroup {

    private final List<LottoTicket> totalTickets = new ArrayList<>();

    public LottoTicketGroup(LottoTickets manualLottoTickets,
        LottoTickets autoLottoTickets) {
        totalTickets.addAll(manualLottoTickets.getTickets());
        totalTickets.addAll(autoLottoTickets.getTickets());
    }

    public LottoStatistics findLottoWinners(WinningTicket winningTicket) {
        return new LottoStatistics(totalTickets.stream()
            .map(winningTicket::compare)
            .collect(Collectors.toList()));
    }

    public List<LottoTicket> getTotalTickets() {
        return Collections.unmodifiableList(totalTickets);
    }
}
