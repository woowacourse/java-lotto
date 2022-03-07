package lotto.dto;

import lotto.domain.LottoTicket;

public class PurchaseResult {

    private final LottoTicket manualTicket;
    private final LottoTicket autoTicket;

    public PurchaseResult(LottoTicket manualTicket, LottoTicket autoTicket) {
        this.manualTicket = manualTicket;
        this.autoTicket = autoTicket;
    }

    public LottoTicket getManualTicket() {
        return manualTicket;
    }

    public LottoTicket getAutoTicket() {
        return autoTicket;
    }

}
