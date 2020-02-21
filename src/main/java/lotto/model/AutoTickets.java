package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class AutoTickets {

    private List<AutoTicket> autoTickets = new ArrayList<>();

    public AutoTickets(int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            autoTickets.add(new AutoTicket());
        }
    }

    public List<AutoTicket> getAutoTickets() {
        return autoTickets;
    }

    public void matchNumberResult(WinNumbers winNumbers, BonusBallNo bonusBallNo) {
        for (AutoTicket autoTicket : autoTickets) {
            autoTicket.setMatchCountResult(winNumbers, bonusBallNo);
            autoTicket.setLottoResultCount();
        }
    }
}
