package lotto.domain;

import java.util.HashSet;
import java.util.Set;

public class IssuedLottoTicket {

    private Set<LottoTicket> issuedLottoTicket;

    public IssuedLottoTicket(int autoLottoTicketCounts, int manualLottoTicketCounts) {
        this.issuedLottoTicket = new HashSet<>();
        this.issuedLottoTicket = LottoFactory.createLottoNumbers(autoLottoTicketCounts, manualLottoTicketCounts);
    }

    public Set<LottoTicket> getIssuedLottoTicket() {
        return issuedLottoTicket;
    }
}
