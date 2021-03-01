package lotto.domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.lotto.LottoTicket;

public final class UsersLottoTickets {

    private final List<LottoTicket> manualLottoTickets;
    private final List<LottoTicket> autoLottoTickets;

    private List<LottoTicket> totalTickets;

    public UsersLottoTickets(List<LottoTicket> manualLottoTickets, List<LottoTicket> autoLottoTickets) {
        this.manualLottoTickets = manualLottoTickets;
        this.autoLottoTickets = autoLottoTickets;
    }

    public int getAutoTicketsSize() {
        return autoLottoTickets.size();
    }

    public int getManualTicketsSize() {
        return manualLottoTickets.size();
    }

    public List<LottoTicket> getTotalTickets() {
        if (totalTickets != null) {
            return totalTickets;
        }

        totalTickets = new ArrayList<>();
        totalTickets.addAll(manualLottoTickets);
        totalTickets.addAll(autoLottoTickets);

        return Collections.unmodifiableList(totalTickets);
    }
}
