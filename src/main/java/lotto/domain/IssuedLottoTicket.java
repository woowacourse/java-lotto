package lotto.domain;

import lotto.view.InputView;

import java.util.HashSet;
import java.util.Set;

public class IssuedLottoTicket {

    private Set<LottoTicket> issuedLottoTicket;

    public IssuedLottoTicket(int autoLottoTicketCounts, int manualLottoTicketCounts) {
        this.issuedLottoTicket = new HashSet<>();
        issueManualLottoTicket(manualLottoTicketCounts);
        issueAutoLottoTicket(autoLottoTicketCounts);
    }

    private void issueAutoLottoTicket(int autoLottoTicketCounts) {
        for (int i = 0; i < autoLottoTicketCounts; i++) {
            this.issuedLottoTicket.add(new LottoTicket(LottoFactory.createRandomLottoNumbers()));
        }
    }

    private void issueManualLottoTicket(int manualLottoTicketCounts) {
        for (int i = 0; i < manualLottoTicketCounts; i++) {
            Set<LottoNumber> manualLottoNumbers = LottoFactory.createManualLottoNumbers(InputView.inputManualLottoNumbers());
            this.issuedLottoTicket.add(new LottoTicket(manualLottoNumbers));
        }

    }

    public Set<LottoTicket> getIssuedLottoTicket() {
        return issuedLottoTicket;
    }
}
